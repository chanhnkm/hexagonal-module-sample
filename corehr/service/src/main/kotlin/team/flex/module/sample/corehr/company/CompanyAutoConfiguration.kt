/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean
import team.flex.module.sample.corehr.company.repository.CompanyRepository

@AutoConfiguration
class CompanyAutoConfiguration {
    @Bean
    fun companyLookUpServiceImpl(companyRepository: CompanyRepository): CompanyLookUpService =
        CompanyLookUpServiceImpl(
            companyRepository = companyRepository,
        )

    @Bean
    fun companyRegisterServiceImpl(companyRepository: CompanyRepository): CompanyRegisterService =
        CompanyRegisterServiceImpl(
            companyRepository = companyRepository,
        )

    @Bean
    fun companyRemoveServiceImpl(companyRepository: CompanyRepository): CompanyRemoveService =
        CompanyRemoveServiceImpl(
            companyRepository = companyRepository,
        )

    @Bean
    fun companyUpdateServiceImpl(companyRepository: CompanyRepository): CompanyUpdateService =
        CompanyUpdateServiceImpl(
            companyRepository = companyRepository,
        )
}
