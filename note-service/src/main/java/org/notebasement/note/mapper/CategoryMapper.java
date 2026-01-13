package org.notebasement.note.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.notebasement.note.dto.request.CategoryCreationRequest;
import org.notebasement.note.dto.request.CategoryUpdateRequest;
import org.notebasement.note.dto.response.CategoryResponse;
import org.notebasement.note.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest req);

    //    @Mapping(source = "")
    CategoryResponse toCategoryResponse(Category category);

    void updateCategory(Category category, @MappingTarget CategoryUpdateRequest categoryUpdateReq);
}
