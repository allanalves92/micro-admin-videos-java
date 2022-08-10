package com.fullcycle.admin.catalogo.application.category.retrieve.list;

import com.fullcycle.admin.catalogo.domain.category.*;

import java.time.*;

public record CategoryListOutput(CategoryID id,
                                 String name,
                                 String description,
                                 boolean isActive,
                                 Instant createdAt,
                                 Instant updatedAt,
                                 Instant deletedAt) {

    public static CategoryListOutput from(final Category aCategory) {
        return new CategoryListOutput(
                aCategory.getId(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getUpdatedAt(),
                aCategory.getCreatedAt(),
                aCategory.getDeletedAt());
    }
}
