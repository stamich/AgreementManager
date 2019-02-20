package pl.codecity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.codecity.model.SystemDictionary;

import java.util.List;

public interface SystemDictionaryRepository extends JpaRepository<SystemDictionary, Long> {

    @Query("SELECT s FROM SystemDictionary s WHERE s.id = :id")
    SystemDictionary findOneById(Long id);

    @Query("SELECT s FROM SystemDictionary s WHERE s.name = :name")
    SystemDictionary findOneByName(String name);

    List<SystemDictionary> findAll();
}
