package com.decrypto.tradehub.services;

import com.decrypto.tradehub.dao.ClientRepository;
import com.decrypto.tradehub.entities.ClientEntity;
import com.decrypto.tradehub.entities.MarketEntity;
import com.decrypto.tradehub.models.CountryStatsDTO;
import com.decrypto.tradehub.models.MarketStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatService {

    private final ClientRepository clientRepository;

    @Autowired
    public StatService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<CountryStatsDTO> calculateDistributionByCountryAndMarket() {
        List<ClientEntity> clients = clientRepository.findAll();
        long totalClients = clients.stream()
                .mapToLong(client -> client.getMarkets().size())
                .sum();
        Map<String, Map<String, Long>> countryMarketCounts = new HashMap<>();

        for (ClientEntity client : clients) {
            for (MarketEntity market : client.getMarkets()) {
                countryMarketCounts
                        .computeIfAbsent(market.getCountry(), k -> new HashMap<>())
                        .merge(market.getCode(), 1L, Long::sum);
            }
        }

        return countryMarketCounts.entrySet().stream().map(countryEntry -> {
            String country = countryEntry.getKey();
            List<MarketStatsDTO> marketStats = countryEntry.getValue().entrySet().stream().map(marketEntry -> {
                String marketName = marketEntry.getKey();
                Long count = marketEntry.getValue();
                String percentage = String.format("%.2f%%", (double) count / totalClients * 100);
                return new MarketStatsDTO(marketName, percentage);
            }).collect(Collectors.toList());

            return new CountryStatsDTO(country, marketStats);
        }).collect(Collectors.toList());
    }

}
