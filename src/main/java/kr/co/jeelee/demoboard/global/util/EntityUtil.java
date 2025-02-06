package kr.co.jeelee.demoboard.global.util;

import kr.co.jeelee.demoboard.global.entity.BaseTimeEntity;

import java.util.UUID;

public interface EntityUtil<T extends BaseTimeEntity> {

    T getById(UUID id);
    boolean hasById(UUID id);

}
