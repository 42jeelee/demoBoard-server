package kr.co.jeelee.demoboard.global.service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EntityService<Q, S> {

    List<S> findAll(Pageable pageable);

    S findById(UUID id);

    S create(Q entity);

    S update(Q entity);

    void delete(UUID id);
}
