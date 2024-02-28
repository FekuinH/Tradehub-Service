package com.decrypto.tradehub.services;

import com.decrypto.tradehub.dao.MarketRepository;
import com.decrypto.tradehub.entities.MarketEntity;
import com.decrypto.tradehub.enums.Pais;
import com.decrypto.tradehub.exceptions.TradehubException;
import com.decrypto.tradehub.models.Market;
import jakarta.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.decrypto.tradehub.exceptions.TradehubErrorCode.*;
import static org.apache.logging.log4j.util.Strings.isBlank;


@Service
public class MarketService {

    private static final Logger log = LogManager.getLogger(MarketService.class);
    private final MarketRepository marketRepository;
    private final EntityManager entityManager;

    @Autowired
    public MarketService(MarketRepository marketRepository, EntityManager entityManager) {
        this.marketRepository = marketRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public Market saveMarket(Market market) {
        validate(market);
        MarketEntity marketPersisted = save(MarketEntity.fromMarket(market));
        return Market.fromMarketEntity(marketPersisted);
    }

    public Market getMarketById(Long id) {
        return Market.fromMarketEntity(findMarketById(id));
    }

    public List<Market> findAllMarkets() {
        return marketRepository.findAll().stream().map(Market::fromMarketEntity).collect(Collectors.toList());
    }

    @Transactional
    public Market updateMarket(Long id, Market market) {
        validate(market);
        MarketEntity marketPersisted = findMarketById(id);
        marketPersisted.setCode(market.code());
        marketPersisted.setDescription(market.description());
        marketPersisted.setCountry(market.country().toUpperCase());
        marketPersisted = save(marketPersisted);
        return Market.fromMarketEntity(marketPersisted);
    }

    public void deleteMarket(Long id) {
        marketRepository.deleteById(id);
    }

    public MarketEntity findMarketById(Long id) {
        return marketRepository.findById(id).orElseThrow(() -> (new TradehubException(INVALID_MARKET_ID)));
    }


    private MarketEntity save(MarketEntity market) {
        try {
            return entityManager.merge(market);
        } catch (RuntimeException e) {
            log.error("Error persisting market", e);
            throw new TradehubException(UNKNOWN_ERROR_PERSISTING_MARKET);
        }
    }

    private void validate(Market market) {
        if (isBlank(market.code())) {
            throw new TradehubException(NULL_MARKET_CODE);
        }
        if (isBlank(market.description())) {
            throw new TradehubException(NULL_MARKET_DESCRIPTION);
        }
        if (isBlank(market.country())) {
            throw new TradehubException(NULL_COUNTRY_MARKET);
        }
        Pais.fromName(market.country()).orElseThrow(() -> (new TradehubException(INVALID_COUNTRY_NAME)));
    }


}
