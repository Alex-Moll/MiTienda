package com.miTienda.app.repository;

import com.miTienda.app.model.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    @Query(value =  "SELECT * FROM articles a WHERE a.code = ?", nativeQuery = true)
    ArticleEntity findByCode(String code);

}
