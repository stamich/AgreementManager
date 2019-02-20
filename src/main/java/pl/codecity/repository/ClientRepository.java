package pl.codecity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.codecity.model.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    void deleteById(Long id);

    @Query("SELECT c FROM Client c WHERE c.id = :id")
    Client findOneById(Long id);

    @Query("SELECT c FROM Client c WHERE c.name = :name")
    Client findOneByName(String name);

    List<Client> findAll();
}
