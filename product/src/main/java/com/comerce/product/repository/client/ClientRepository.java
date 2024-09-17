package com.comerce.product.repository.client;

import com.comerce.product.entity.client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

    @Query("select c from ClientEntity c where c.email = ?1")
    Optional<ClientEntity> findByEmail(String email);
}