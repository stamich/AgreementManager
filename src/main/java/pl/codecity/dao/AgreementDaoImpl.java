package pl.codecity.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.codecity.model.Agreement;

import java.util.List;

@Repository("agreementDao")
public class AgreementDaoImpl extends AbstractDao<Long, Agreement> implements AgreementDao{

    @Override
    public void save(Agreement agreement) {
        persistEntity(agreement);
    }

    @Override
    public void delete(Long id) {
        Query query = getSession().createQuery("DELETE FROM Agreement WHERE id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public Agreement findById(Long id) {
        return getByKey(id);
    }

    @Override
    public Agreement findByNumber(String agreementNumber) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("agreementNumber", agreementNumber));
        return (Agreement) criteria.uniqueResult();
    }

    @Override
    public List<Agreement> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<Agreement>) criteria.list();
    }
}
