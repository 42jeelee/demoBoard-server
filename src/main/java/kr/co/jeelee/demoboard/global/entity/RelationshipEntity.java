package kr.co.jeelee.demoboard.global.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class RelationshipEntity extends BaseTimeEntity {

    @EmbeddedId
    protected RelationshipEntityId id;

}
