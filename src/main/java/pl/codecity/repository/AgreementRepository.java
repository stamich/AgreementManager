package pl.codecity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.codecity.model.Agreement;

import java.time.LocalDate;
import java.util.List;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {

    @Query("DELETE FROM Agreement a WHERE a.id = :id")
    void deleteById(Long id);

    @Query("SELECT a FROM Agreement a WHERE a.id = :id")
    Agreement findOneById(Long id);

    @Query("SELECT a FROM Agreement a WHERE a.number = :number")
    Agreement findByNumber(String number);

    List<Agreement> findAllByValidFrom(LocalDate validFrom);

    List<Agreement> findAllByValidTo(LocalDate validTo);

    Long countByActive(@Param("active") Boolean active);

    List<Agreement> findAll();
}
