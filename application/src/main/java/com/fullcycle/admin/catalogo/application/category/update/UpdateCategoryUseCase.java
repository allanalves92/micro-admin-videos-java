package com.fullcycle.admin.catalogo.application.category.update;

import com.fullcycle.admin.catalogo.application.*;
import com.fullcycle.admin.catalogo.domain.validation.handler.*;
import io.vavr.control.*;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification,
        UpdateCategoryOutput>> {

}
