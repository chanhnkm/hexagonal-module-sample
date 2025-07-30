/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company

import team.flex.module.sample.corehr.company.repository.CompanyRepository
import team.flex.module.sample.corehr.exception.CompanyNotAddedException

interface CompanyRegisterService {
    fun add(companyName: String): CompanyModel
}

internal class CompanyRegisterServiceImpl(
    private val companyRepository: CompanyRepository,
) : CompanyRegisterService {
    override fun add(companyName: String): CompanyModel =
        companyRepository.add(companyName = companyName) ?: throw CompanyNotAddedException()
}
