package kr.co.jeelee.demoboard.global.service;

import kr.co.jeelee.demoboard.global.entity.RelationshipEntity;
import kr.co.jeelee.demoboard.global.entity.RelationshipEntityId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class SimpleRelationshipService<
            R extends JpaRepository<E, RelationshipEntityId>,
            E extends RelationshipEntity
        >
        implements RelationshipEntityService {

    protected final R repository;

    @Override
    public Boolean existById(UUID aId, UUID bId) {
        RelationshipEntityId id = RelationshipEntityId.of(aId, bId);

        return repository.existsById(id);
    }

    @Override
    @Transactional
    public Boolean create(UUID aId, UUID bId) {
        try {
            E entity = createEntity(aId, bId);

            repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public void deleteById(UUID aId, UUID bId) {
        repository.deleteById(RelationshipEntityId.of(aId, bId));
    }

    protected abstract E createEntity(UUID aId, UUID bId);
}
