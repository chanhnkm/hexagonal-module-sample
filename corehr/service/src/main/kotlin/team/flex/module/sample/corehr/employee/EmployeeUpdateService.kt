/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.employee

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.employee.repository.EmployeeRepository
import team.flex.module.sample.corehr.exception.EmployeeNotModifiedException

interface EmployeeUpdateService {
    fun modify(
        companyIdentity: CompanyIdentity,
        employeeIdentity: EmployeeIdentity,
        employeeNumber: String,
        employeeName: String,
    ): EmployeeModel
}

internal class EmployeeUpdateServiceImpl(
    private val employeeRepository: EmployeeRepository,
) : EmployeeUpdateService {
    override fun modify(
        companyIdentity: CompanyIdentity,
        employeeIdentity: EmployeeIdentity,
        employeeNumber: String,
        employeeName: String,
    ): EmployeeModel =
        employeeRepository.modify(
            companyIdentity = companyIdentity,
            employeeIdentity = employeeIdentity,
            employeeNumber = employeeNumber,
            employeeName = employeeName,
        )
            ?: throw EmployeeNotModifiedException()
}
