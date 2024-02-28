package com.decrypto.tradehub.models;


import com.decrypto.tradehub.entities.ClientEntity;

import java.util.List;
import java.util.stream.Collectors;

public record Client(Long id, String description, List<ShortMarket> markets) {
    public static Client fromClientEntity(ClientEntity clientEntity) {
        return new Client(clientEntity.getId(),
                clientEntity.getDescription(),
                clientEntity.getMarkets().stream().map(ShortMarket::fromMarketEntity).collect(Collectors.toList()));
    }
}

