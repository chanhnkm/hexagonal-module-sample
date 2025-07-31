/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import team.flex.module.sample.corehr.company.dto.CompanyResponse
import team.flex.module.sample.corehr.company.dto.CompanyRequest

@RestController
@RequestMapping("/api/v2/corehr")
class CompanyApiController(
    private val lookUpService: CompanyLookUpService,
    private val registerService: CompanyRegisterService,
    private val removeService: CompanyRemoveService,
    private val updateService: CompanyUpdateService,
) {
    @GetMapping("/companies/{companyId}")
    @Operation(
        summary = "회사 조회 API",
        operationId = "getCompany",
    )
    fun getCompany(
        @PathVariable companyId: Long,
    ): CompanyResponse {
        return lookUpService.get(
            CompanyIdentity.of(companyId),
        ).let {
            CompanyResponse(
                companyId = it.companyId,
                companyName = it.name,
            )
        }
    }

    @GetMapping("/companies")
    @Operation(
        summary = "회사 전체 조회 API",
        operationId = "getAllCompany",
    )
    fun getAllCompany(): List<CompanyResponse> {
        return lookUpService.getAll().map { it ->
            CompanyResponse(
                companyId = it.companyId,
                companyName = it.name,
            )
        }
    }

    @PostMapping("/companies")
    @Operation(
        summary = "회사 등록 API",
        operationId = "addCompany",
    )
    fun addCompany(
        @RequestBody request: CompanyRequest,
    ): CompanyResponse {
        return registerService.add(
            request.companyName,
        ).let {
            CompanyResponse(
                companyId = it.companyId,
                companyName = it.name,
            )
        }
    }

    @DeleteMapping("/companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "회사 삭제 API",
        operationId = "removeCompany",
    )
    fun removeCompany(
        @PathVariable companyId: Long,
    ) {
        removeService.delete(
            CompanyIdentity.of(companyId),
        )
    }

    @PutMapping("/companies/{companyId}")
    @Operation(
        summary = "회사 수정 API",
        operationId = "updateCompany",
    )
    fun updateCompany(
        @PathVariable companyId: Long,
        @RequestBody request: CompanyRequest,
    ): CompanyResponse {
        return updateService.modify(
            CompanyIdentity.of(companyId),
            request.companyName,
        ).let {
            CompanyResponse(
                companyId = it.companyId,
                companyName = it.name,
            )
        }
    }
}
