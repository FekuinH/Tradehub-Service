package com.decrypto.tradehub.models;

public record ErrorModel(
        int errorCode,
        String errorMessage
) {

}
