package com.miTienda.app.repository;

import com.miTienda.app.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query(value =  "SELECT * from Clientes a WHERE a.codigo = ?", nativeQuery = true)
    ClientEntity findByCodigo(String codigo);
}
