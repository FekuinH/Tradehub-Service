package com.decrypto.tradehub.controllers;

import com.decrypto.tradehub.models.CountryStatsDTO;
import com.decrypto.tradehub.models.ErrorModel;
import com.decrypto.tradehub.models.Market;
import com.decrypto.tradehub.services.StatService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CountryStatsDTO.class))),
    })
    public ResponseEntity<List<CountryStatsDTO>> getClientDistributionByMarketAndCountryStats() {
        return ResponseEntity.ok(statService.calculateDistributionByCountryAndMarket());
    }
}
