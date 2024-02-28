package com.decrypto.tradehub.services;

import com.decrypto.tradehub.dao.ClientRepository;
import com.decrypto.tradehub.entities.ClientEntity;
import com.decrypto.tradehub.entities.MarketEntity;
import com.decrypto.tradehub.exceptions.TradehubException;
import com.decrypto.tradehub.models.Client;
import jakarta.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.decrypto.tradehub.exceptions.TradehubErrorCode.*;
import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;


@Service
public class ClientService {

    private static final Logger log = LogManager.getLogger(ClientService.class);
    private final ClientRepository clientRepository;
    private final MarketService marketService;
    private final EntityManager entityManager;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         MarketService marketService, EntityManager entityManager) {
        this.clientRepository = clientRepository;
        this.marketService = marketService;
        this.entityManager = entityManager;
    }


    @Transactional
    public Client saveClient(Client client) {
        validate(client, false);
        ClientEntity clientPersisted = save(ClientEntity.fromClientModel(client));
        return Client.fromClientEntity(clientPersisted);
    }

    public Client getClientById(Long id) {
        return Client.fromClientEntity(findClientById(id));
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll().stream().map(Client::fromClientEntity).collect(Collectors.toList());
    }

    @Transactional
    public Client updateClient(Long id, Client client) {
        validate(client, true);
        ClientEntity clientPersisted = findClientById(id);
        clientPersisted.setDescription(client.description());
        clientPersisted = save(clientPersisted);
        return Client.fromClientEntity(clientPersisted);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    public Client addMarket(Long clientId, Long marketId) {
        ClientEntity clientPersisted = findClientById(clientId);
        MarketEntity marketEntity = marketService.findMarketById(marketId);

        boolean marketAlreadyAssociated = clientPersisted.getMarkets().stream()
                .anyMatch(market -> market.getId().equals(marketId));
        if (marketAlreadyAssociated) {
            throw new TradehubException(MARKET_ALREADY_ASSOCIATED);
        }

        clientPersisted.getMarkets().add(marketEntity);
        clientPersisted = save(clientPersisted);
        return Client.fromClientEntity(clientPersisted);
    }

    @Transactional
    public Client removeMarket(Long clientId, Long marketId) {
        ClientEntity clientPersisted = findClientById(clientId);


        Optional<MarketEntity> marketToRemove = clientPersisted.getMarkets().stream()
                .filter(market -> market.getId().equals(marketId))
                .findFirst();

        if (marketToRemove.isPresent()) {
            clientPersisted.getMarkets().remove(marketToRemove.get());
            clientPersisted = save(clientPersisted);
        }

        return Client.fromClientEntity(clientPersisted);
    }

    private ClientEntity findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> (new TradehubException(INVALID_CLIENT_ID)));
    }

    private void validate(Client client, boolean isUpdate) {
        if (isBlank(client.description()) && !isUpdate) {
            throw new TradehubException(NULL_CLIENT_DESCRIPTION);
        }
        if (clientRepository.existsClientEntitiesByDescription(client.description())) {
            throw new TradehubException(CLIENT_EXISTS);
        }
        if (!isUpdate) {
            if (isEmpty(client.markets())) {
                throw new TradehubException(EMPTY_CLIENT_MARKETS);
            }

            client.markets().forEach(market -> marketService.getMarketById(market.id()));
        }
    }

    private ClientEntity save(ClientEntity client) {
        try {
            return entityManager.merge(client);
        } catch (RuntimeException e) {
            log.error("Error persisting client", e);
            throw new TradehubException(UNKNOWN_ERROR_PERSISTING_CLIENT);
        }
    }


}
