package pl.codecity.dao;

import pl.codecity.model.Agreement;

import java.util.List;

/**
 * @author Michal Stawarski
 */
public interface AgreementDao {

    void save(Agreement agreement);
    void delete(Long id);
    Agreement findById(Long id);
    Agreement findByNumber(String number);
    List<Agreement> findAll();
}
