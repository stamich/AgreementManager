package pl.codecity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.codecity.model.System;

import java.util.List;

public interface SystemRepository extends JpaRepository<System, Long> {

    @Query("SELECT s FROM System s WHERE s.id = :id")
    System findOneById(Long id);

    @Query("SELECT s FROM System s WHERE s.technology = :technology")
    System findOneByTechnology(String technology);

    List<System> findAll();
}
