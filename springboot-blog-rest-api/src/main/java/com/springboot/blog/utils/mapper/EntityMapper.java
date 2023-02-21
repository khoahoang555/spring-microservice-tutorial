package com.springboot.blog.utils.mapper;

import java.util.List;

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> listDto);

    List<D> toDto(List<E> listEntity);
}
