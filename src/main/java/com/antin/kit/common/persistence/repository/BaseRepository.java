package com.antin.kit.common.persistence.repository;

import com.antin.kit.common.persistence.domain.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository <T extends Base> extends JpaRepository<T, String> {

    @Override
    @Query("SELECT t FROM #{#entityName} t WHERE t.isActive = true")
    Page<T> findAll(Pageable pageable);

    @Override
    @Query("SELECT t FROM #{#entityName} t WHERE t.uuid =:id AND t.isActive = true")
    Optional<T> findById(@Param("id") String id);

    @Query("SELECT t FROM #{#entityName} t WHERE t.uuid =:id ")
    Optional<T> findByIdIgnoreDeletedFlag(@Param("id") String id);
}

