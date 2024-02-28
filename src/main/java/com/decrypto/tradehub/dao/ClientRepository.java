package com.decrypto.tradehub.dao;

import com.decrypto.tradehub.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    boolean existsClientEntitiesByDescription(String description);
}

