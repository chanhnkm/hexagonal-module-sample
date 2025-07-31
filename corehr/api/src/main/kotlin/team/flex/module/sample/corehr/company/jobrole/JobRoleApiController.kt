/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.jobrole

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
import team.flex.module.sample.corehr.company.jobrole.JobRoleIdentity
import team.flex.module.sample.corehr.company.jobrole.dto.JobRoleRequest
import team.flex.module.sample.corehr.company.jobrole.dto.JobRoleResponse
import team.flex.module.sample.corehr.company.jobrole.of

@RestController
@RequestMapping("/api/v2/corehr")
class JobRoleApiController(
    private val lookUpService: JobRoleLookUpService,
    private val registerService: JobRoleRegisterService,
    private val removeService: JobRoleRemoveService,
    private val updateService: JobRoleUpdateService,
) {
    @GetMapping("/companies/{companyId}/jobRoles/{jobRoleId}")
    @Operation(
        summary = "직무 조회 API",
        operationId = "getJobRole",
    )
    fun getJobRole(
        @PathVariable companyId: Long,
        @PathVariable jobRoleId: Long,
    ): JobRoleResponse {
        return lookUpService.get(
            CompanyIdentity.of(companyId),
            JobRoleIdentity.of(jobRoleId),
        ).let {
            JobRoleResponse(
                jobRoleId = it.jobRoleId,
                jobRoleName = it.name,
            )
        }
    }

    @PostMapping("/companies/{companyId}/jobRoles")
    @Operation(
        summary = "직무 등록 API",
        operationId = "addJobRole",
    )
    fun addJobRole(
        @PathVariable companyId: Long,
        @RequestBody request: JobRoleRequest,
    ): JobRoleResponse {
        return registerService.add(
            companyId,
            request.jobRoleName,
        ).let {
            JobRoleResponse(
                jobRoleId = it.jobRoleId,
                jobRoleName = it.name,
            )
        }
    }

    @DeleteMapping("/jobRoles/{jobRoleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "직무 삭제 API",
        operationId = "removeJobRole",
    )
    fun removeJobRole(
        @PathVariable jobRoleId: Long,
    ) {
        removeService.delete(
            JobRoleIdentity.of(jobRoleId),
        )
    }

    @PutMapping("/jobRoles/{jobRoleId}")
    @Operation(
        summary = "직무 수정 API",
        operationId = "updateJobRole",
    )
    fun updateJobRole(
        @PathVariable jobRoleId: Long,
        @RequestBody request: JobRoleRequest,
    ): JobRoleResponse {
        return updateService.modify(
            jobRoleId,
            request.jobRoleName,
        ).let {
            JobRoleResponse(
                jobRoleId = it.jobRoleId,
                jobRoleName = it.name,
            )
        }
    }
}
