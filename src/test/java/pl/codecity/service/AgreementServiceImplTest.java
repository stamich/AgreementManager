package pl.codecity.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import pl.codecity.dao.AgreementDao;
import pl.codecity.model.Agreement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class AgreementServiceImplTest {

    @Mock
    private AgreementDao agreementDao;

    @InjectMocks
    private AgreementServiceImpl agreementService;

    @Spy
    List<Agreement> agreements = new ArrayList<>();

    public List<Agreement> getAgreements(){

        Agreement a = new Agreement();
        a.setId(1L);
        a.setNumber("100/2019");
        a.setValidFrom(LocalDate.now());
        a.setValidTo(LocalDate.now());
        a.setAmount(5000.50);
        a.setActive(true);
        a.setComments("Something");
        //a.setSystem();

        agreements.add(a);
        return agreements;
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        agreements = getAgreements();
    }

    @Test
    public void findById(){
        Agreement a = new Agreement();
        when(agreementDao.findById(anyLong())).thenReturn(a);
        assertEquals(agreementService.findById(a.getId()), a);
    }

    @After
    public void tearDown(){
        agreements.remove(agreements);
    }
}
