package com.decrypto.tradehub.models;

import java.util.List;

public record CountryStatsDTO(String country, List<MarketStatsDTO> market) {}
