package pl.codecity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.codecity.model.System;

@Repository
public interface SystemRepository extends JpaRepository<System, Long>, SystemRepositoryCustom {

    @Query("SELECT s FROM System s WHERE s.id = :id")
    System findOneById(Long id);

    @Query("SELECT s FROM System s WHERE s.technology = :technology")
    System findOneByTechnology(String technology);
}
