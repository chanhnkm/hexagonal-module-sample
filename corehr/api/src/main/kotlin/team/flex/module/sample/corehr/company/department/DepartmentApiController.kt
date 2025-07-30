/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.of
import team.flex.module.sample.corehr.company.department.dto.DepartmentResponse

@RestController
@RequestMapping("/api/v2/corehr")
class DepartmentApiController(
    private val service: DepartmentLookUpService,
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
        return service.get(
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
}
