/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.jobrole

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.jobrole.repository.JobRoleRepository
import team.flex.module.sample.corehr.exception.JobRoleNotAddedException

interface JobRoleRegisterService {
    fun add(
        companyIdentity: CompanyIdentity,
        jobRoleName: String,
    ): JobRoleModel
}

internal class JobRoleRegisterServiceImpl(
    private val jobRoleRepository: JobRoleRepository,
) : JobRoleRegisterService {
    override fun add(
        companyIdentity: CompanyIdentity,
        jobRoleName: String,
    ): JobRoleModel =
        jobRoleRepository.add(
            companyIdentity = companyIdentity,
            jobRoleName = jobRoleName,
        ) ?: throw JobRoleNotAddedException()
}
