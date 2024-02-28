package com.decrypto.tradehub.controllers;

import com.decrypto.tradehub.models.CountryStatsDTO;
import com.decrypto.tradehub.models.Market;
import com.decrypto.tradehub.services.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatController {

    private final StatService statService;

    @Autowired
    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping()
    public ResponseEntity<List<CountryStatsDTO>> getClientDistributionByMarketAndCountryStats() {
        return ResponseEntity.ok(statService.calculateDistributionByCountryAndMarket());
    }
}
