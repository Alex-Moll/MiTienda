package com.miTienda.app.repository;

import com.miTienda.app.model.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity, Long> {

}
