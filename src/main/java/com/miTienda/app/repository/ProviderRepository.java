package com.miTienda.app.repository;

import com.miTienda.app.model.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {

}
