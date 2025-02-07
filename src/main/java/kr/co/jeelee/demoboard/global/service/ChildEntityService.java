package kr.co.jeelee.demoboard.global.service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChildEntityService<QCreate, QUpdate, SSimple, SDetail> {

    List<SSimple> findAllByParentId(UUID parentId, Pageable pageable);

    SDetail findById(UUID id);

    SDetail createByParentId(UUID parentId, QCreate request);

    SDetail update(UUID id, QUpdate request);

    void delete(UUID parentId, UUID id);
}