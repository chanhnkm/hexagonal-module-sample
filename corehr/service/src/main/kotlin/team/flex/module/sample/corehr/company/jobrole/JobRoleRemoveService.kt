/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.jobrole

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.jobrole.repository.JobRoleRepository
import team.flex.module.sample.corehr.exception.JobRoleNotDeletedException

interface JobRoleRemoveService {
    fun delete(
        companyIdentity: CompanyIdentity,
        jobRoleIdentity: JobRoleIdentity,
    ): Long
}

internal class JobRoleRemoveServiceImpl(
    private val jobRoleRepository: JobRoleRepository,
) : JobRoleRemoveService {
    override fun delete(
        companyIdentity: CompanyIdentity,
        jobRoleIdentity: JobRoleIdentity,
    ): Long =
        jobRoleRepository.delete(
            companyIdentity = companyIdentity,
            jobRoleIdentity = jobRoleIdentity,
        ) ?: throw JobRoleNotDeletedException()
}
