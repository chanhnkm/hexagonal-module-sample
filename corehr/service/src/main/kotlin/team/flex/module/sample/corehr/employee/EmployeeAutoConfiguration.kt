/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.employee

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean
import team.flex.module.sample.corehr.employee.repository.EmployeeRepository

@AutoConfiguration
class EmployeeAutoConfiguration {
    @Bean
    fun employeeLookUpServiceImpl(employeeRepository: EmployeeRepository): EmployeeLookUpService =
        EmployeeLookUpServiceImpl(
            employeeRepository = employeeRepository,
        )

    @Bean
    fun employeeRegisterServiceImpl(employeeRepository: EmployeeRepository): EmployeeRegisterService =
        EmployeeRegisterServiceImpl(
            employeeRepository = employeeRepository,
        )

    @Bean
    fun employeeRemoveServiceImpl(employeeRepository: EmployeeRepository): EmployeeRemoveService =
        EmployeeRemoveServiceImpl(
            employeeRepository = employeeRepository,
        )

    @Bean
    fun employeeUpdateServiceImpl(employeeRepository: EmployeeRepository): EmployeeUpdateService =
        EmployeeUpdateServiceImpl(
            employeeRepository = employeeRepository,
        )
}
