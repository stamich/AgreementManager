package pl.codecity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.codecity.helper.SystemSearchHelper;
import pl.codecity.model.System;

public interface SystemRepositoryCustom {

    Page<System> search(SystemSearchHelper helper, Pageable pageable);
}
