package pl.codecity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.dao.AgreementDao;
import pl.codecity.dao.ClientDao;
import pl.codecity.model.Agreement;
import pl.codecity.model.Client;
import pl.codecity.repository.AgreementRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service("agreementService")
@Transactional(rollbackFor = Exception.class)
public class AgreementServiceImpl implements AgreementService {

    private AgreementDao agreementDao;
    private ClientDao clientDao;

    @Autowired
    public AgreementServiceImpl(AgreementDao agreementDao, ClientDao clientDao) {
        this.agreementDao = agreementDao;
        this.clientDao = clientDao;
    }

    @Override
    public void save(Agreement agreement) {
        agreementDao.save(agreement);
    }

    @Override
    public void update(Agreement agreement) {
        Client client = new Client();
        String updatedBy = client.getName();
        Agreement entity = agreementDao.findById(agreement.getId());
        if(entity != null){
            entity.setValidFrom(agreement.getValidFrom());
            entity.setValidTo(agreement.getValidTo());
            entity.setAmount(agreement.getAmount());
            entity.setActive(agreement.isActive());
            entity.setComments(agreement.getComments());

            entity.setUpdatedAt(LocalDateTime.now());
            entity.setUpdatedBy(updatedBy);
        }
    }

    @Override
    public void delete(Long id) {
        agreementDao.delete(id);
    }

    @Override
    public Agreement findById(Long id) {
        return agreementDao.findById(id);
    }

    @Override
    public Agreement findByNumber(String number) {
        return agreementDao.findByNumber(number);
    }

    @Override
    public List<Agreement> findAll() {
        return agreementDao.findAll();
    }

    @Override
    public Boolean isAgreementNumberUnique(Long id, String number) {
        Agreement agreement = findByNumber(number);
        return (agreement == null || ((id != null) && (agreement.getId() == id)));
    }

    //    @Autowired
//    public AgreementRepository repository;
//
//    @Override
//    public void save(Agreement agreement) {
//        repository.save(agreement);
//    }
//
//    @Override
//    public void update(Long id) {
//        Agreement agreement = new Agreement();
//        // TO DO
//        repository.save(agreement);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        repository.deleteById(id);
//    }
//
//    @Override
//    public Agreement findOneById(Long id) {
//        return repository.findOneById(id);
//    }
//
//    @Override
//    public Agreement findByNumber(String number) {
//        return findByNumber(number);
//    }
//
//    @Override
//    public List<Agreement> findAllByValidFrom(LocalDate validFrom) {
//        return findAllByValidFrom(validFrom);
//    }
//
//    @Override
//    public List<Agreement> findAllByValidTo(LocalDate validTo) {
//        return findAllByValidTo(validTo);
//    }
//
//    @Override
//    public Long countByActive(Boolean active) {
//        return null;
//    }
//
//    @Override
//    public List<Agreement> findAll() {
//        return repository.findAll();
//    }
}
