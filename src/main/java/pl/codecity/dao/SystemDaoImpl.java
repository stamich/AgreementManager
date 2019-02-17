package pl.codecity.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.codecity.model.System;

import java.util.List;

@Repository("systemDao")
public class SystemDaoImpl extends AbstractDao<Long, System> implements SystemDao{

    @Override
    public void save(System system) {
        persistEntity(system);
    }

    @Override
    public void delete(Long id) {
        Query query = getSession().createQuery("DELETE FROM System WHERE id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public System findById(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (System) criteria.uniqueResult();
    }

    @Override
    public List<System> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<System>) criteria.list();
    }
}
