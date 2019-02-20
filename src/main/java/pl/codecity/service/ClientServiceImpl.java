package pl.codecity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.dao.ClientDao;
import pl.codecity.model.Client;
import pl.codecity.repository.ClientRepository;

import java.util.List;

@Transactional
@Service("clientService")
public class ClientServiceImpl implements ClientService{

    private ClientDao dao;

    @Autowired
    public ClientServiceImpl(ClientDao dao) {
        this.dao = dao;
    }

    @Override
    public Client findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Client findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public void save(Client client) {
        dao.save(client);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<Client> findAll() {
        return dao.findAll();
    }

    //    @Autowired
//    private ClientRepository repository;
//
//    @Transactional(readOnly = true)
//    public ResponseEntity findAll(){
//        List<Client> clients = repository.findAll();
//        ResponseEntity<?> response = new ResponseEntity(clients, HttpStatus.OK);
//        return response;
//    }
//
//    @Transactional(readOnly = true)
//    public ResponseEntity findOne(Long id){
//        Client client = repository.findOneById(id);
//        ResponseEntity<?>response = new ResponseEntity<>(client, HttpStatus.OK);
//        return response;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    @Override
//    public Client findOneById(Long id) {
//        return null;
//    }
//
//    @Override
//    public Client findOneByName(String name) {
//        return null;
//    }
}
