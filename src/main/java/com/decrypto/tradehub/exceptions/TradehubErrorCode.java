package com.decrypto.tradehub.exceptions;

import com.decrypto.tradehub.enums.Pais;
import lombok.Getter;

@Getter
public enum TradehubErrorCode {
    INVALID_CLIENT_ID(400, "No existe comitente con el ID proporcionado."),
    INVALID_MARKET_ID(400, "No existe mercado con el ID proporcionado."),
    NULL_CLIENT_DESCRIPTION(400, "La descripción del comitente no puede ser nula / vacía."),
    CLIENT_EXISTS(400, "El comitente ya existe con la descripción proporcionada."),
    EMPTY_CLIENT_MARKETS(400, "Un comitente debe tener al menos un mercado asignado."),
    NULL_MARKET_CODE(400, "El código del mercado no puede ser nulo / vacío."),
    NULL_MARKET_DESCRIPTION(400, "La descripción del mercado no puede ser nula / vacía."),
    NULL_COUNTRY_MARKET(400, "El mercado debe tener un País"),
    INVALID_COUNTRY_NAME(400, "Nombre de país inválido. Los Paises permitidos son: ".concat(Pais.getNames())),
    MARKET_ALREADY_ASSOCIATED(400, "El mercado ya se encuentra asociado al comitente."),


    UNKNOWN_ERROR_PERSISTING_CLIENT(500, "Se produjo un error desconocido al momento de persistir el comitente."),
    UNKNOWN_ERROR_PERSISTING_MARKET(500, "Se produjo un error desconocido al momento de persistir el mercado.");
    private final int code;
    private final String reason;

    TradehubErrorCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

}
