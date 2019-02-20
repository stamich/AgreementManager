package pl.codecity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.dao.SystemDao;
import pl.codecity.model.System;

import java.util.List;

@Transactional
@Service("systemService")
public class SystemServiceImpl implements SystemService {

    private SystemDao dao;

    @Autowired
    public SystemServiceImpl(SystemDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(System system) {
        dao.save(system);
    }

    @Override
    public void update(System system) {
        System entity = dao.findById(system.getId());
        if(entity != null){
            entity.setId(system.getId());
            entity.setDescription(system.getDescription());
            entity.setTechnology(system.getTechnology());
            entity.setComments(system.getComments());
            entity.setSystemDictionaries(system.getSystemDictionaries());
        }
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public System findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<System> findAll() {
        return dao.findAll();
    }
}
