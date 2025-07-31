/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.jobrole.repository

import org.springframework.data.repository.CrudRepository
import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.jobrole.JobRole
import team.flex.module.sample.corehr.company.jobrole.JobRoleIdentity
import team.flex.module.sample.corehr.company.jobrole.JobRoleModel
import java.time.Instant

interface JobRoleJdbcRepository : CrudRepository<JobRoleEntity, Long> {
    fun findByIdAndCompanyId(
        jobRoleId: Long,
        companyId: Long,
    ): JobRoleEntity?

    fun findAllByCompanyId(companyId: Long): List<JobRoleEntity>

    fun deleteByIdAndCompanyId(
        jobRoleId: Long,
        companyId: Long,
    ): Long?
}

class JobRoleRepositoryImpl(
    private val jobRoleJdbcRepository: JobRoleJdbcRepository,
) : JobRoleRepository {
    override fun findByJobRoleIdentity(
        companyIdentity: CompanyIdentity,
        jobRoleIdentity: JobRoleIdentity,
    ): JobRoleModel? {
        return jobRoleJdbcRepository.findByIdAndCompanyId(
            jobRoleId = jobRoleIdentity.jobRoleId,
            companyId = companyIdentity.companyId,
        )?.toModel()
    }

    override fun findAllByCompanyIdentity(companyIdentity: CompanyIdentity): List<JobRoleModel> {
        return jobRoleJdbcRepository.findAllByCompanyId(
            companyId = companyIdentity.companyId,
        ).map { it.toModel() }
    }

    override fun add(
        companyIdentity: CompanyIdentity,
        jobRoleName: String,
    ): JobRoleModel? {
        val now = Instant.now()
        val entity = JobRoleEntity(
            companyId = companyIdentity.companyId,
            name = jobRoleName,
            createdAt = now,
            updatedAt = now,
        )
        val saved = jobRoleJdbcRepository.save(entity)
        return saved.toModel()
    }

    override fun delete(
        companyIdentity: CompanyIdentity,
        jobRoleIdentity: JobRoleIdentity,
    ): Long? {
        jobRoleJdbcRepository.deleteByIdAndCompanyId(
            jobRoleId = jobRoleIdentity.jobRoleId,
            companyId = companyIdentity.companyId,
        )
        return jobRoleIdentity.jobRoleId
    }

    override fun modify(
        companyIdentity: CompanyIdentity,
        jobRoleIdentity: JobRoleIdentity,
        jobRoleName: String,
    ): JobRoleModel? {
        val now = Instant.now()
        val model = jobRoleJdbcRepository.findByIdAndCompanyId(
            jobRoleId = jobRoleIdentity.jobRoleId,
            companyId = companyIdentity.companyId,
        )

        model?.let {
            it.name = jobRoleName
            it.updatedAt = now
        }!!

        val saved = jobRoleJdbcRepository.save(model)
        return saved.toModel()
    }

    private fun JobRoleEntity.toModel() =
        JobRole(
            jobRoleId = jobRoleId,
            companyId = companyId,
            name = name,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
}
