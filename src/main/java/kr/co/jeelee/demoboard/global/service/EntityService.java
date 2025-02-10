package kr.co.jeelee.demoboard.global.service;

import java.util.UUID;

public interface EntityService<QCreate, SDetail> {

    SDetail findById(UUID id);

    SDetail create(QCreate request);

    void delete(UUID id);
}
