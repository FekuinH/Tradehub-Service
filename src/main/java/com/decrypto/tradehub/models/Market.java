package com.decrypto.tradehub.models;

import com.decrypto.tradehub.entities.MarketEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record Market(
        Long id,
        String code,
        String description,
        String country,
        List<ShortClient> clients
) {

    public static Market fromMarketEntity(MarketEntity marketEntity) {
        List<ShortClient> clientList = marketEntity.getClients() != null ?
                marketEntity.getClients().stream().map(ShortClient::fromClientEntity).toList() :
                Collections.emptyList();

        return new Market(marketEntity.getId(),
                marketEntity.getCode(),
                marketEntity.getDescription(),
                marketEntity.getCountry(),
                clientList
        );
    }
}
