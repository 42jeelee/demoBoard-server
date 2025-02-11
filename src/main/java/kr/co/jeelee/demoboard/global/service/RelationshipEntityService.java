package kr.co.jeelee.demoboard.global.service;

import java.util.List;
import java.util.UUID;

public interface RelationshipEntityService
        <ADetail, BDetail, ASimple, BSimple> {

    Boolean existById(UUID aId, UUID bId);

    ADetail create(UUID aId, UUID bId);

    ADetail create(UUID aId, List<UUID> bIds);

    BDetail create(List<UUID> aIds, UUID bId);

    void deleteById(UUID aId, UUID bId);

    List<ASimple> getAsByBId(UUID bId);

    List<BSimple> getBsByAId(UUID aId);

}
