package com.decrypto.tradehub.models;

import com.decrypto.tradehub.entities.ClientEntity;


public record ShortClient(
        Long id,
        String description
) {


    public static ShortClient fromClientEntity(ClientEntity clientEntity) {
        return new ShortClient(clientEntity.getId(), clientEntity.getDescription());
    }
}
