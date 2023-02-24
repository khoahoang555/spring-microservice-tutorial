package com.springboot.blog.utils.mapper;

import com.springboot.blog.entity.Category;
import com.springboot.blog.payload.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryMapper implements EntityMapper<CategoryDTO, Category> {

    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        return modelMapper.map(dto, Category.class);
    }

    @Override
    public CategoryDTO toDto(Category entity) {
        return modelMapper.map(entity, CategoryDTO.class);
    }

    @Override
    public List<Category> toEntity(List<CategoryDTO> listDto) {
        return null;
    }

    @Override
    public List<CategoryDTO> toDto(List<Category> listEntity) {
        return listEntity.stream().map(entity -> this.toDto(entity)).collect(Collectors.toList());
    }
}
