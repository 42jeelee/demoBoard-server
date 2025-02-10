package kr.co.jeelee.demoboard.global.service;

import java.util.UUID;

public interface UpdatableEntityService<QUpdate, SDetail> {

    SDetail update(UUID id, QUpdate update);

}
