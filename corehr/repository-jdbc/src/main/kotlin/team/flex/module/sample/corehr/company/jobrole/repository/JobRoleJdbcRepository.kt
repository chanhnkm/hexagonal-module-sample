/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.jobrole.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.department.repository.DepartmentEntity
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
        companyId: Long,
        jobRoleName: String,
    ): JobRoleModel? {
        val now = Instant.now()
        val entity = JobRoleEntity(
            companyId = companyId,
            name = jobRoleName,
            createdAt = now,
            updatedAt = now,
        )
        val saved = jobRoleJdbcRepository.save(entity)
        return saved
    }

    override fun delete(
        jobRoleIdentity: JobRoleIdentity,
    ): Long? {
        jobRoleJdbcRepository.deleteById(jobRoleIdentity.jobRoleId)
        return jobRoleIdentity.jobRoleId
    }

    override fun modify(
        jobRoleId: Long,
        jobRoleName: String,
    ): JobRoleModel? {
        val now = Instant.now()
        val entity = jobRoleJdbcRepository.findByIdOrNull(id = jobRoleId)
            ?: throw IllegalArgumentException("Invalid jobRoleId: $jobRoleId")

        entity.name = jobRoleName
        entity.updatedAt = now

        val saved = jobRoleJdbcRepository.save(entity)
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
