/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department.dto

data class DepartmentRequest(
    val parentDepartmentId: Long,
    val departmentName: String,
)
