package com.miTienda.app.repository;

import com.miTienda.app.model.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {

}
