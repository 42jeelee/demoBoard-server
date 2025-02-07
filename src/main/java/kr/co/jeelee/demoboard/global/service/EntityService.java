package kr.co.jeelee.demoboard.global.service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EntityService<QCreate, QUpdate, SSimple, SDetail> {

    List<SSimple> findAll(Pageable pageable);

    SDetail findById(UUID id);

    SDetail create(QCreate request);

    SDetail update(UUID id, QUpdate request);

    void delete(UUID id);
}
