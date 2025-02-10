package kr.co.jeelee.demoboard.global.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PageableEntityService<Q, S> {

    default List<S> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    };

    default List<S> search(Q query, Pageable pageable) {
        throw new UnsupportedOperationException();
    };

}
