package com.miTienda.app.repository;

import com.miTienda.app.model.entity.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticuloRepository extends JpaRepository<ArticuloEntity, Long> {

    @Query(value =  "SELECT * from articulos a WHERE a.codigo = ?", nativeQuery = true)
    ArticuloEntity findByCodigo(String codigo);

}
