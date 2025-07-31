/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.jobrole

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.jobrole.repository.JobRoleRepository
import team.flex.module.sample.corehr.exception.JobRoleNotModifiedException

interface JobRoleUpdateService {
    fun modify(
        companyIdentity: CompanyIdentity,
        jobRoleIdentity: JobRoleIdentity,
        jobRoleName: String,
    ): JobRoleModel
}

internal class JobRoleUpdateServiceImpl(
    private val jobRoleRepository: JobRoleRepository,
) : JobRoleUpdateService {
    override fun modify(
        companyIdentity: CompanyIdentity,
        jobRoleIdentity: JobRoleIdentity,
        jobRoleName: String,
    ): JobRoleModel =
        jobRoleRepository.modify(
            companyIdentity = companyIdentity,
            jobRoleIdentity = jobRoleIdentity,
            jobRoleName = jobRoleName,
        ) ?: throw JobRoleNotModifiedException()
}
