package com.decrypto.tradehub.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Getter
public enum Pais {

    ARGENTINA("Argentina"),
    URUGUAY("Uruguay");

    private final String name;

    Pais(String name) {
        this.name = name;
    }


    public static Optional<Pais> fromName(String name) {
        if (isNotBlank(name)) {
            for (Pais pais : Pais.values()) {
                if (pais.getName().equalsIgnoreCase(name)) {
                    return Optional.of(pais);
                }
            }
        }
        return Optional.empty();
    }

    public static String getNames() {
        return Arrays.stream(Pais.values())
                .map(Pais::getName)
                .collect(Collectors.joining(", "));
    }

}