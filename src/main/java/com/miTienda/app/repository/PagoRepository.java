package com.miTienda.app.repository;

import com.miTienda.app.model.entity.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, Long> {

}
