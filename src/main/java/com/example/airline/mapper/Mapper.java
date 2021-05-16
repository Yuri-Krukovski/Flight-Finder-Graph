package com.example.airline.mapper;

import com.example.airline.dto.AbstractDto;
import com.example.airline.model.AbstractEntity;

public interface Mapper <E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);
    D toDto(E entity);
}
