/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.employee

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.employee.repository.EmployeeRepository
import team.flex.module.sample.corehr.exception.EmployeeNotUpdatedException

interface EmployeeUpdateService {
    fun modify(
        employeeId: Long,
        employeeNumber: String,
        employeeName: String,
    ): EmployeeModel
}

internal class EmployeeUpdateServiceImpl(
    private val employeeRepository: EmployeeRepository,
) : EmployeeUpdateService {
    override fun modify(
        employeeId: Long,
        employeeNumber: String,
        employeeName: String,
    ): EmployeeModel =
        employeeRepository.modify(
            employeeId = employeeId,
            employeeNumber = employeeNumber,
            employeeName = employeeName,
        )
            ?: throw EmployeeNotUpdatedException()
}
