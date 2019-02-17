package pl.codecity.dao;

import pl.codecity.model.System;

import java.util.List;

public interface SystemDao {

    void save(System system);
    void delete(Long id);
    System findById(Long id);
    List<System> findAll();
}
