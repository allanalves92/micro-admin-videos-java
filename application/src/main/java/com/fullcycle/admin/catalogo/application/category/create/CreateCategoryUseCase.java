package com.fullcycle.admin.catalogo.application.category.create;

import com.fullcycle.admin.catalogo.application.*;
import com.fullcycle.admin.catalogo.domain.validation.handler.*;
import io.vavr.control.*;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification,
        CreateCategoryOutput>> {

}
