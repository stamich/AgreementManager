package pl.codecity.service;

import pl.codecity.model.Agreement;

import java.util.List;

public interface AgreementService {

    void save(Agreement agreement);
    void update(Agreement agreement);
    void delete(Long id);
    Agreement findById(Long id);
    Agreement findByNumber(String agreementNumber);
    List<Agreement> findAll();
}
