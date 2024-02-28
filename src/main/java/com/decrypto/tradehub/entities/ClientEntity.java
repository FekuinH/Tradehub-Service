package com.decrypto.tradehub.entities;

import com.decrypto.tradehub.models.Client;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "clients", uniqueConstraints = {@UniqueConstraint(columnNames = {"description"})})
@NoArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "clients_markets",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "market_id"))
    private List<MarketEntity> markets;

    public ClientEntity(Long id, String description, List<MarketEntity> markets) {
        this.id = id;
        this.description = description;
        this.markets = markets;
    }

    public static ClientEntity fromClientModel(Client client) {
        return new ClientEntity(client.id(),
                client.description(),
                client.markets().stream().map(MarketEntity::fromMarketModelShort).collect(Collectors.toList()));
    }

}

