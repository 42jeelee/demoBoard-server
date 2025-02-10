package kr.co.jeelee.demoboard.global.service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PageableChildEntityService<Q, S> {

    default List<S> findAll(UUID parentId, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    default List<S> search(UUID parentId, Q query, Pageable pageable) {
        throw new UnsupportedOperationException();
    }
}
