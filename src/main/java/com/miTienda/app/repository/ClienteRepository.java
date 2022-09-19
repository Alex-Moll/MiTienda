package com.miTienda.app.repository;

import com.miTienda.app.model.entity.ClienteEntity;
import com.miTienda.app.model.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query(value =  "SELECT * from Clientes a WHERE a.codigo = ?", nativeQuery = true)
    ClienteEntity findByCodigo(String codigo);
}
