package pl.codecity.dao;

import pl.codecity.model.SystemDictionary;

import java.util.List;

public interface SystemDictionaryDao {

    void save(SystemDictionary dictionary);
    void delete(Long id);
    SystemDictionary findById(Long id);
    List<SystemDictionary> findAll();
}
