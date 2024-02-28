package com.decrypto.tradehub.entities;


import com.decrypto.tradehub.models.Market;
import com.decrypto.tradehub.models.ShortMarket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "markets")
@NoArgsConstructor
public class MarketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String description;

    @Column
    private String country;

    @ManyToMany(mappedBy = "markets", fetch = FetchType.LAZY)
    private List<ClientEntity> clients;


    public MarketEntity(Long id, String code, String description, String country) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.country = country;
    }


    public static MarketEntity fromMarketModelShort(ShortMarket market) {
        return new MarketEntity(market.id(),
                market.code(),
                market.description(),
                market.country());
    }

    public static MarketEntity fromMarket(Market market) {
        return new MarketEntity(market.id(),
                market.code(),
                market.description(),
                market.country().toUpperCase());
    }
}
