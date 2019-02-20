package pl.codecity.service;

import org.springframework.data.repository.query.Param;
import pl.codecity.model.Agreement;

import java.time.LocalDate;
import java.util.List;

public interface AgreementService {

    void save(Agreement agreement);
    void update(Agreement agreement);
    void delete(Long id);
    Agreement findById(Long id);
    Agreement findByNumber(String number);
    List<Agreement> findAll();
    Boolean isAgreementNumberUnique(Long id, String number);

//    void save(Agreement agreement);
//    void update(Long id);
//    void deleteById(Long id);
//    Agreement findOneById(Long id);
//    Agreement findByNumber(String number);
//    List<Agreement> findAllByValidFrom(LocalDate validFrom);
//    List<Agreement> findAllByValidTo(LocalDate validTo);
//    Long countByActive(@Param("active") Boolean active);
//    List<Agreement> findAll();
}
