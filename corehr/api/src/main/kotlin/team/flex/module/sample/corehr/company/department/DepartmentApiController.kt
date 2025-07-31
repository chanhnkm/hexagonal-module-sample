/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.of
import team.flex.module.sample.corehr.company.department.dto.DepartmentResponse
import team.flex.module.sample.corehr.company.department.dto.DepartmentRequest

@RestController
@RequestMapping("/api/v2/corehr")
class DepartmentApiController(
    private val lookUpService: DepartmentLookUpService,
    private val registerService: DepartmentRegisterService,
    private val removeService: DepartmentRemoveService,
    private val updateService: DepartmentUpdateService,
) {
    @GetMapping("/companies/{companyId}/departments/{departmentId}")
    @Operation(
        summary = "부서 조회 API",
        operationId = "getDepartment",
    )
    fun getDepartment(
        @PathVariable companyId: Long,
        @PathVariable departmentId: Long,
    ): DepartmentResponse {
        return lookUpService.get(
            CompanyIdentity.of(companyId),
            DepartmentIdentity.of(departmentId),
        ).let {
            DepartmentResponse(
                departmentId = it.departmentId,
                parentDepartmentId = it.parentDepartmentId,
                departmentName = it.name,
            )
        }
    }

    @PostMapping("/companies/{companyId}/departments")
    @Operation(
        summary = "부서 등록 API",
        operationId = "addDepartment",
    )
    fun addDepartment(
        @PathVariable companyId: Long,
        @RequestBody request: DepartmentRequest,
    ): DepartmentResponse {
        return registerService.add(
            CompanyIdentity.of(companyId),
            request.parentDepartmentId,
            request.departmentName,
        ).let {
            DepartmentResponse(
                departmentId = it.departmentId,
                parentDepartmentId = it.parentDepartmentId,
                departmentName = it.name,
            )
        }
    }

    @DeleteMapping("/companies/{companyId}/departments/{departmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "부서 삭제 API",
        operationId = "removeDepartment",
    )
    fun removeDepartment(
        @PathVariable companyId: Long,
        @PathVariable departmentId: Long,
    ) {
        removeService.delete(
            CompanyIdentity.of(companyId),
            DepartmentIdentity.of(departmentId),
        )
    }

    @PutMapping("/companies/{companyId}/departments/{departmentId}")
    @Operation(
        summary = "부서 수정 API",
        operationId = "updateDepartment",
    )
    fun updateDepartment(
        @PathVariable companyId: Long,
        @PathVariable departmentId: Long,
        @RequestBody request: DepartmentRequest,
    ): DepartmentResponse {
        return updateService.modify(
            CompanyIdentity.of(companyId),
            DepartmentIdentity.of(departmentId),
            request.parentDepartmentId,
            request.departmentName,
        ).let {
            DepartmentResponse(
                departmentId = it.departmentId,
                parentDepartmentId = it.parentDepartmentId,
                departmentName = it.name,
            )
        }
    }
}
