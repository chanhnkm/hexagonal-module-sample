/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.jobrole.repository

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.jobrole.JobRoleIdentity
import team.flex.module.sample.corehr.company.jobrole.JobRoleModel

interface JobRoleRepository {
    fun findByJobRoleIdentity(
        companyIdentity: CompanyIdentity,
        jobRoleIdentity: JobRoleIdentity,
    ): JobRoleModel?

    fun findAllByCompanyIdentity(companyIdentity: CompanyIdentity): List<JobRoleModel>

    fun add(
        companyIdentity: CompanyIdentity,
        jobRoleName: String,
    ): JobRoleModel?

    fun delete(
        companyIdentity: CompanyIdentity,
        jobRoleIdentity: JobRoleIdentity,
    ): Long?

    fun modify(
        companyIdentity: CompanyIdentity,
        jobRoleIdentity: JobRoleIdentity,
        jobRoleName: String,
    ): JobRoleModel?
}
