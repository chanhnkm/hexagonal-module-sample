/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.department.repository.DepartmentRepository
import team.flex.module.sample.corehr.exception.DepartmentNotModifiedException

interface DepartmentUpdateService {
    fun modify(
        companyIdentity: CompanyIdentity,
        departmentIdentity: DepartmentIdentity,
        parentDepartmentId: Long,
        departmentName: String,
    ): DepartmentModel
}

internal class DepartmentUpdateServiceImpl(
    private val departmentRepository: DepartmentRepository,
) : DepartmentUpdateService {
    override fun modify(
        companyIdentity: CompanyIdentity,
        departmentIdentity: DepartmentIdentity,
        parentDepartmentId: Long,
        departmentName: String,
    ): DepartmentModel =
        departmentRepository.modify(
            companyIdentity = companyIdentity,
            departmentIdentity = departmentIdentity,
            parentDepartmentId = parentDepartmentId,
            departmentName = departmentName,
        )
            ?: throw DepartmentNotModifiedException()
}
