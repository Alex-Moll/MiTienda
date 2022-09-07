package com.miTienda.app.repository;

import com.miTienda.app.model.entity.GrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, Long> {
}
