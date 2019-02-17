package pl.codecity.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.codecity.model.Client;

import java.util.List;

@Repository("clientDao")
public class ClientDaoImpl extends AbstractDao<Long, Client> implements ClientDao {

    @Override
    public Client findById(Long id) {
        return getByKey(id);
    }

    @Override
    public Client findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Client) criteria.uniqueResult();
    }

    @Override
    public void save(Client client) {
        persistEntity(client);
    }

    @Override
    public void delete(Long id) {
        Query query = getSession().createQuery("DELETE FROM Client WHERE id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Client> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<Client>) criteria;
    }
}
