package pl.codecity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.codecity.helper.AgreementSearchHelper;
import pl.codecity.model.Agreement;

public interface AgreementRepositoryCustom {

    Page<Agreement> search(AgreementSearchHelper helper, Pageable pageable);
}
