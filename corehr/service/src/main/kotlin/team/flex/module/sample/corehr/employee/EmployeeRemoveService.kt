/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.employee

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.employee.repository.EmployeeRepository
import team.flex.module.sample.corehr.exception.EmployeeNotDeletedException

interface EmployeeRemoveService {
    fun delete(
        companyIdentity: CompanyIdentity,
        employeeIdentity: EmployeeIdentity,
    ): Long
}

internal class EmployeeRemoveServiceImpl(
    private val employeeRepository: EmployeeRepository,
) : EmployeeRemoveService {
    override fun delete(
        companyIdentity: CompanyIdentity,
        employeeIdentity: EmployeeIdentity,
    ): Long =
        employeeRepository.delete(
            companyIdentity = companyIdentity,
            employeeIdentity = employeeIdentity,
        )
            ?: throw EmployeeNotDeletedException()
}
