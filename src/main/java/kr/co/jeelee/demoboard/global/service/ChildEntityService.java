package kr.co.jeelee.demoboard.global.service;

import java.util.UUID;

public interface ChildEntityService<QCreate, SDetail> {

    SDetail findById(UUID parentId, UUID id);

    SDetail createByParentId(UUID parentId, QCreate request);

    void delete(UUID parentId, UUID id);
}