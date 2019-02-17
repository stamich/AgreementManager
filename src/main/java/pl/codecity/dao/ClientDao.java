package pl.codecity.dao;

import pl.codecity.model.Client;

import java.util.List;

public interface ClientDao {

    Client findById(Long id);
    Client findByName(String name);
    void save(Client client);
    void delete(Long id);
    List<Client> findAll();
}
