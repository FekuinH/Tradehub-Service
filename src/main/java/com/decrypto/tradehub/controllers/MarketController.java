package com.decrypto.tradehub.controllers;

import com.decrypto.tradehub.models.ErrorModel;
import com.decrypto.tradehub.models.Market;
import com.decrypto.tradehub.services.MarketService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/markets")
public class MarketController {

    private final MarketService marketService;

    @Autowired
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Market.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorModel.class)))
    })
    public ResponseEntity<Market> createMarket(@RequestBody Market market) {
        return ResponseEntity.ok((marketService.saveMarket(market)));
    }

    @GetMapping("/{id}")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Market.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorModel.class)))
    })
    public ResponseEntity<Market> getMarketById(@PathVariable Long id) {
        return ResponseEntity.ok(marketService.getMarketById(id));
    }

    @GetMapping
    public ResponseEntity<List<Market>> getAllMarkets() {
        return ResponseEntity.ok(marketService.findAllMarkets());
    }


    @PutMapping("/{id}")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Market.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorModel.class)))
    })
    public ResponseEntity<Market> updateMarket(@PathVariable Long id, @RequestBody Market market) {
        return ResponseEntity.ok(marketService.updateMarket(id, market));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        marketService.deleteMarket(id);
    }
}
