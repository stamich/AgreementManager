package pl.codecity.service;

import pl.codecity.model.SystemDictionary;

import java.util.List;

public interface SystemDictionaryService {

    void save(SystemDictionary dictionary);
    void delete(Long id);
    SystemDictionary findById(Long id);
    List<SystemDictionary> findAll();
}
