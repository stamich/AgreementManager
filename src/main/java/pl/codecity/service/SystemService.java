package pl.codecity.service;

import pl.codecity.model.System;

import java.util.List;

public interface SystemService {

    void save(System system);
    void update(System system);
    void delete(Long id);
    System findById(Long id);
    List<System> findAll();
}
