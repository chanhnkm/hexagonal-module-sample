/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company

import team.flex.module.sample.corehr.company.repository.CompanyRepository
import team.flex.module.sample.corehr.exception.CompanyNotModifiedException

interface CompanyUpdateService {
    fun modify(companyId: Long, companyName: String): CompanyModel
}

internal class CompanyUpdateServiceImpl(
    private val companyRepository: CompanyRepository,
) : CompanyUpdateService {
    override fun modify(companyId: Long, companyName: String): CompanyModel =
        companyRepository.modify(companyId = companyId, companyName = companyName) ?: throw CompanyNotModifiedException()
}
