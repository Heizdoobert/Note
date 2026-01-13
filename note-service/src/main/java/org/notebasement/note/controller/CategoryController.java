package org.notebasement.note.controller;

import jakarta.validation.Valid;
import org.notebasement.common.dto.ApiResponse;
import org.notebasement.note.dto.request.CategoryCreationRequest;
import org.notebasement.note.dto.response.CategoryResponse;
import org.notebasement.note.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryResponse> addCategory(@RequestBody @Valid CategoryCreationRequest req) {
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(categoryService.createCategory(req));
        return apiResponse;
    }

    @GetMapping
    List<CategoryResponse> getAllCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    CategoryResponse getCategory(@PathVariable String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/{categoryId}")
    CategoryResponse updateCategory(@PathVariable String categoryId, @RequestBody @Valid CategoryCreationRequest req) {
        return categoryService.updateCategory(categoryId, req);
    }

    @DeleteMapping("/{categoryId}")
    String deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return "category deleted";
    }
}
