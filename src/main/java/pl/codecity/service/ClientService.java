package pl.codecity.service;

import pl.codecity.model.Client;

import java.util.List;

public interface ClientService {

    Client findById(Long id);
    Client findByName(String name);
    void save(Client client);
    void delete(Long id);
    List<Client> findAll();

//    void deleteById(Long id);
//    Client findOneById(Long id);
//    Client findOneByName(String name);
//    List<Client> findAll();
}
