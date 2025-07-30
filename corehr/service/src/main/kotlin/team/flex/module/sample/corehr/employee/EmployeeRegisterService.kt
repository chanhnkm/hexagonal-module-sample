/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.employee

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.employee.repository.EmployeeRepository
import team.flex.module.sample.corehr.exception.EmployeeNotAddedException

interface EmployeeRegisterService {
    fun add(
        companyId: Long,
        employeeNumber: String,
        employeeName: String,
    ): EmployeeModel
}

internal class EmployeeRegisterServiceImpl(
    private val employeeRepository: EmployeeRepository,
) : EmployeeRegisterService {
    override fun add(
        companyId: Long,
        employeeNumber: String,
        employeeName: String,
    ): EmployeeModel =
        employeeRepository.add(
            companyId = companyId,
            employeeNumber = employeeNumber,
            employeeName = employeeName,
        )
            ?: throw EmployeeNotAddedException()
}
