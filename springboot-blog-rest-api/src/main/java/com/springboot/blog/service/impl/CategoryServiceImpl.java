package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CategoryDTO;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import com.springboot.blog.utils.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category categorySaved = categoryRepository.save(category);
        return categoryMapper.toDto(categorySaved);
    }

    @Override
    public CategoryDTO getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryMapper.toDto(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        Category categoryUpdated = categoryRepository.save(category);

        return categoryMapper.toDto(categoryUpdated);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(category);
    }
}
