package kr.co.jeelee.demoboard.global.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipEntityId implements Serializable {

    private UUID aId;
    private UUID bId;

    public static RelationshipEntityId of(UUID aId, UUID bId) {
        return new RelationshipEntityId(aId, bId);
    }

}
