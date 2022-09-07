package com.miTienda.app.repository;

import com.miTienda.app.model.entity.TalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalleRepository extends JpaRepository<TalleEntity, Long> {

}
