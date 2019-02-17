package pl.codecity.dao;

import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.codecity.model.SystemDictionary;

import java.util.List;

@Repository("systemDictionaryDao")
public class SystemDictionaryDaoImpl extends AbstractDao<Long, SystemDictionary> implements SystemDictionaryDao {

    @Override
    public void save(SystemDictionary dictionary) {
        persistEntity(dictionary);
    }

    @Override
    public void delete(Long id) {
        Query query = getSession().createQuery("DELETE FROM System_Dictionary WHERE id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public SystemDictionary findById(Long id) {
        return getByKey(id);
    }

    @Override
    public List<SystemDictionary> findAll() {
        Criteria criteria = createEntityCriteria();
        return criteria.list();
    }
}
