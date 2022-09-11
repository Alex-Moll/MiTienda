package com.miTienda.app.repository;

import com.miTienda.app.model.entity.TipoArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoArticuloRepository extends JpaRepository<TipoArticuloEntity, Long> {

}
