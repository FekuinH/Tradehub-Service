package com.decrypto.tradehub.controllers;

import com.decrypto.tradehub.models.Market;
import com.decrypto.tradehub.services.MarketService;
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
    public ResponseEntity<Market> createMarket(@RequestBody Market market) {
        return ResponseEntity.ok((marketService.saveMarket(market)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Market> getMarketById(@PathVariable Long id) {
        return ResponseEntity.ok(marketService.getMarketById(id));
    }

    @GetMapping
    public ResponseEntity<List<Market>> getAllMarkets() {
        return ResponseEntity.ok(marketService.findAllMarkets());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Market> updateMarket(@PathVariable Long id, @RequestBody Market market) {
        return ResponseEntity.ok(marketService.updateMarket(id, market));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        marketService.deleteMarket(id);
    }
}
