/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company

import team.flex.module.sample.corehr.company.repository.CompanyRepository
import team.flex.module.sample.corehr.exception.CompanyNotRemovedException

interface CompanyRemoveService {
    fun delete(companyIdentity: CompanyIdentity): Long
}

internal class CompanyRemoveServiceImpl(
    private val companyRepository: CompanyRepository,
) : CompanyRemoveService {
    override fun delete(companyIdentity: CompanyIdentity): Long =
        companyRepository.delete(companyIdentity = companyIdentity) ?: throw CompanyNotRemovedException()
}
