package kr.co.jeelee.demoboard.global.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class RelationshipEntity
        <A extends BaseEntity, B extends BaseEntity> extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "a_id", nullable = false)
    protected A a;

    @ManyToOne
    @JoinColumn(name = "b_id", nullable = false)
    protected B b;

}
