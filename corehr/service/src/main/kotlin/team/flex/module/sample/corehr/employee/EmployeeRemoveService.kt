/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.employee

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.employee.repository.EmployeeRepository
import team.flex.module.sample.corehr.exception.EmployeeNotRemovedException

interface EmployeeRemoveService {
    fun delete(
        employeeIdentity: EmployeeIdentity,
    ): Long
}

internal class EmployeeRemoveServiceImpl(
    private val employeeRepository: EmployeeRepository,
) : EmployeeRemoveService {
    override fun delete(
        employeeIdentity: EmployeeIdentity,
    ): Long =
        employeeRepository.delete(
            employeeIdentity = employeeIdentity,
        )
            ?: throw EmployeeNotRemovedException()
}
