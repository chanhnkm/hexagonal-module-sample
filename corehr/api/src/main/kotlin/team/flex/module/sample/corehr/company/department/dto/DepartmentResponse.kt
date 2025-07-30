/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department.dto

class DepartmentResponse(
    val departmentId: Long,
    val parentDepartmentId: Long?,
    val departmentName: String,
)
