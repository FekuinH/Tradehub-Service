package com.decrypto.tradehub.models;

import com.decrypto.tradehub.entities.MarketEntity;

import java.util.List;
import java.util.stream.Collectors;

public record ShortMarket(
        Long id,
        String code,
        String description,
        String country
) {

    public static ShortMarket fromMarketEntity(MarketEntity marketEntity) {
        return new ShortMarket(marketEntity.getId(),
                marketEntity.getCode(),
                marketEntity.getDescription(),
                marketEntity.getCountry());
    }
}
