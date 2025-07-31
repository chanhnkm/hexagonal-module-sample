/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.department.repository.DepartmentRepository
import team.flex.module.sample.corehr.exception.DepartmentNotAddedException

interface DepartmentRegisterService {
    fun add(
        companyId: Long,
        parentDepartmentId: Long,
        departmentName: String,
    ): DepartmentModel
}

internal class DepartmentRegisterServiceImpl(
    private val departmentRepository: DepartmentRepository,
) : DepartmentRegisterService {
    override fun add(
        companyId: Long,
        parentDepartmentId: Long,
        departmentName: String,
    ): DepartmentModel =
        departmentRepository.add(
            companyId = companyId,
            parentDepartmentId = parentDepartmentId,
            departmentName = departmentName,
        )
            ?: throw DepartmentNotAddedException()
}
