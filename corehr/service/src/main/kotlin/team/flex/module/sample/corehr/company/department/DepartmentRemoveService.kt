/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.department.repository.DepartmentRepository
import team.flex.module.sample.corehr.exception.DepartmentNotDeletedException

interface DepartmentRemoveService {
    fun delete(
        companyIdentity: CompanyIdentity,
        departmentIdentity: DepartmentIdentity,
    ): Long
}

internal class DepartmentRemoveServiceImpl(
    private val departmentRepository: DepartmentRepository,
) : DepartmentRemoveService {
    override fun delete(
        companyIdentity: CompanyIdentity,
        departmentIdentity: DepartmentIdentity,
    ): Long =
        departmentRepository.delete(
            companyIdentity = companyIdentity,
            departmentIdentity = departmentIdentity,
        )
            ?: throw DepartmentNotDeletedException()
}
