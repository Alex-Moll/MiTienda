package com.miTienda.app.repository;

import com.miTienda.app.model.entity.VentasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepository extends JpaRepository<VentasEntity, Long> {

}
