package com.decrypto.tradehub.controllers;

import com.decrypto.tradehub.models.Client;
import com.decrypto.tradehub.models.ErrorModel;
import com.decrypto.tradehub.services.ClientService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorModel.class)))
    })
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok((clientService.saveClient(client)));
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorModel.class)))
    })
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.findAllClients());
    }


    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorModel.class)))
    })
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