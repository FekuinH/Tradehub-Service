package com.decrypto.tradehub.exceptions;

import lombok.Getter;

@Getter
public class TradehubException extends RuntimeException {

    private final int statusCode;
    private final String errorMessage;

    public TradehubException(int statusCode, String errorMessage) {
        super(errorMessage);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public TradehubException(TradehubErrorCode errorCode) {
        super(errorCode.getReason());
        this.statusCode = errorCode.getCode();
        this.errorMessage = errorCode.getReason();
    }

}


