package com.java.employeeservice.service.mapper;

public interface EntityMapper <D, E> {
    public E toEntity(D dto);
    public D toDTO(E entity);
}
