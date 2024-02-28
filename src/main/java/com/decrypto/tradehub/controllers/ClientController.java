package com.decrypto.tradehub.controllers;

import com.decrypto.tradehub.models.Client;
import com.decrypto.tradehub.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok((clientService.saveClient(client)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.findAllClients());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.updateClient(id, client));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @PostMapping("/{clientId}/agregar-mercado/{marketId}")
    public ResponseEntity<Client> addMarket(@PathVariable Long clientId, @PathVariable Long marketId) {
        return ResponseEntity.ok((clientService.addMarket(clientId, marketId)));
    }

    @DeleteMapping("/{clientId}/remover-mercado/{marketId}")
    public ResponseEntity<Client> removeMarket(@PathVariable Long clientId, @PathVariable Long marketId) {
        return ResponseEntity.ok(clientService.removeMarket(clientId, marketId));
    }
}