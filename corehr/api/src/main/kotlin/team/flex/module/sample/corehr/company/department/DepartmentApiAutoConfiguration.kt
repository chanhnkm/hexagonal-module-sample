/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Import

@Import(
    DepartmentApiController::class,
)
@AutoConfiguration
class DepartmentApiAutoConfiguration
