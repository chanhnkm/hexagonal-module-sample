/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.jobrole

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean
import team.flex.module.sample.corehr.company.jobrole.repository.JobRoleRepository

@AutoConfiguration
class JobRoleAutoConfiguration {
    @Bean
    fun jobRoleLookUpServiceImpl(jobRoleRepository: JobRoleRepository): JobRoleLookUpService =
        JobRoleLookUpServiceImpl(
            jobRoleRepository,
        )

    @Bean
    fun jobRoleRegisterServiceImpl(jobRoleRepository: JobRoleRepository): JobRoleRegisterService =
        JobRoleRegisterServiceImpl(
            jobRoleRepository,
        )

    @Bean
    fun jobRoleRemoveServiceImpl(jobRoleRepository: JobRoleRepository): JobRoleRemoveService =
        JobRoleRemoveServiceImpl(
            jobRoleRepository,
        )

    @Bean
    fun jobRoleUpdateServiceImpl(jobRoleRepository: JobRoleRepository): JobRoleUpdateService =
        JobRoleUpdateServiceImpl(
            jobRoleRepository,
        )
}
