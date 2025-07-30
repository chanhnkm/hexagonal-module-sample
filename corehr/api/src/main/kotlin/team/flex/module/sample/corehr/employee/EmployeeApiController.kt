/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.employee

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.dto.CompanyRequest
import team.flex.module.sample.corehr.company.dto.CompanyResponse
import team.flex.module.sample.corehr.company.of
import team.flex.module.sample.corehr.employee.dto.EmployeeResponse
import team.flex.module.sample.corehr.employee.dto.EmployeeRequest

@RestController
@RequestMapping("/api/v2/corehr")
class EmployeeApiController(
    private val lookUpService: EmployeeLookUpService,
    private val registerService: EmployeeRegisterService,
) {
    @GetMapping("/companies/{companyId}/employees/{employeeId}")
    @Operation(
        summary = "구성원 조회 API",
        operationId = "getEmployee",
    )
    fun getEmployee(
        @PathVariable companyId: Long,
        @PathVariable employeeId: Long,
    ): EmployeeResponse {
        return lookUpService.get(
            CompanyIdentity.of(companyId),
            EmployeeIdentity.of(employeeId),
        ).let {
            EmployeeResponse(
                employeeId = it.employeeId,
                employeeNumber = it.employeeNumber,
                employeeName = it.name,
            )
        }
    }

    @PostMapping("/companies/{companyId}/employees")
    @Operation(
        summary = "구성원 등록 API",
        operationId = "addEmployee",
    )
    fun addEmployee(
        @PathVariable companyId: Long,
        @RequestBody request: EmployeeRequest,
    ): EmployeeResponse {
        return registerService.add(
            companyId,
            request.employeeNumber,
            request.employeeName,
        ).let {
            EmployeeResponse(
                employeeId = it.employeeId,
                employeeNumber = it.employeeNumber,
                employeeName = it.name,
            )
        }
    }
}
