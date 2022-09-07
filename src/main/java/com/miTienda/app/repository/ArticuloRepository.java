package com.miTienda.app.repository;

import com.miTienda.app.model.entity.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<ArticuloEntity, Long> {

}
