package com.miTienda.app.repository;

import com.miTienda.app.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

//    @Query("SELECT * FROM users WHERE soft_delete = false")
//    List<UserEntity> findBySoftDelete();

}
