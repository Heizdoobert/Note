package org.notebasement.note.service;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.notebasement.common.exception.AppException;
import org.notebasement.common.exception.ErrorCode;
import org.notebasement.note.dto.request.CategoryCreationRequest;
import org.notebasement.note.dto.response.CategoryResponse;
import org.notebasement.note.entity.Category;
import org.notebasement.note.mapper.CategoryMapper;
import org.notebasement.note.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryCreationRequest req) {
        Category category = categoryMapper.toCategory(req);

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(String categoryId) {
        return categoryMapper.toCategoryResponse(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND)));
    }

    public CategoryResponse updateCategory(String categoryId, @Valid CategoryCreationRequest req) {
        Category category = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryMapper.updateCategory(category, req);

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public void deleteCategory(String categoryId) {
        if (!categoryRepository.exitsByCategoryId(categoryId)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        categoryRepository.deleteById(categoryId);
    }
}
