/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.jobrole

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.of
import team.flex.module.sample.corehr.company.jobrole.dto.JobRoleResponse

@RestController
@RequestMapping("/api/v2/corehr")
class JobRoleApiController(
    private val service: JobRoleLookUpService,
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
        return service.get(
            CompanyIdentity.of(companyId),
            JobRoleIdentity.of(jobRoleId),
        ).let {
            JobRoleResponse(
                jobRoleId = it.jobRoleId,
                jobRoleName = it.name,
            )
        }
    }
}
