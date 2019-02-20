package pl.codecity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.dao.SystemDictionaryDao;
import pl.codecity.model.SystemDictionary;

import java.util.List;

@Transactional
@Service("systemDictionaryService")
public class SystemDictionaryServiceImpl implements SystemDictionaryService {

    private SystemDictionaryDao dao;

    @Autowired
    public SystemDictionaryServiceImpl(SystemDictionaryDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(SystemDictionary dictionary) {
        dao.save(dictionary);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public SystemDictionary findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<SystemDictionary> findAll() {
        return dao.findAll();
    }
}
