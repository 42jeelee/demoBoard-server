package kr.co.jeelee.demoboard.global.service;

import java.util.UUID;

public interface RelationshipEntityService {

    Boolean existById(UUID aId, UUID bId);

    Boolean create(UUID aId, UUID bId);

    void deleteById(UUID aId, UUID bId);
}
