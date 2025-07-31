/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.department.Department
import team.flex.module.sample.corehr.company.department.DepartmentIdentity
import team.flex.module.sample.corehr.company.department.DepartmentModel
import java.time.Instant

interface DepartmentJdbcRepository : CrudRepository<DepartmentEntity, Long> {
    fun findByIdAndCompanyId(
        departmentId: Long,
        companyId: Long,
    ): DepartmentEntity?

    fun findAllByCompanyId(companyId: Long): List<DepartmentEntity>
}

class DepartmentRepositoryImpl(
    private val departmentJdbcRepository: DepartmentJdbcRepository,
) : DepartmentRepository {
    override fun findByDepartmentIdentity(
        companyIdentity: CompanyIdentity,
        departmentIdentity: DepartmentIdentity,
    ): DepartmentModel? {
        return departmentJdbcRepository.findByIdAndCompanyId(
            departmentId = departmentIdentity.departmentId,
            companyId = companyIdentity.companyId,
        )?.toModel()
    }

    override fun findAllByCompanyIdentity(companyIdentity: CompanyIdentity): List<DepartmentModel> {
        return departmentJdbcRepository.findAllByCompanyId(
            companyId = companyIdentity.companyId,
        ).map { it.toModel() }
    }

    override fun add(companyId: Long,
                     parentDepartmentId: Long,
                     departmentName: String,
    ): DepartmentModel? {
        val now = Instant.now()
        val entity = DepartmentEntity(
            companyId = companyId,
            parentDepartmentId = parentDepartmentId,
            name = departmentName,
            createdAt = now,
            updatedAt = now,
        )
        val saved = departmentJdbcRepository.save(entity)
        return saved
    }

    override fun delete(departmentIdentity: DepartmentIdentity): Long? {
        departmentJdbcRepository.deleteById(departmentIdentity.departmentId)
        return departmentIdentity.departmentId
    }

    override fun modify(departmentId: Long,
                        parentDepartmentId: Long,
                        departmentName: String,
    ): DepartmentModel? {
        val now = Instant.now()
        val entity = departmentJdbcRepository.findByIdOrNull(id = departmentId)
            ?: throw IllegalArgumentException("Invalid departmentId: $departmentId")

        entity.parentDepartmentId = parentDepartmentId
        entity.name = departmentName
        entity.updatedAt = now

        val saved = departmentJdbcRepository.save(entity)
        return saved.toModel()
    }

    private fun DepartmentEntity.toModel() =
        Department(
            departmentId = departmentId,
            companyId = companyId,
            parentDepartmentId = parentDepartmentId,
            name = name,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
}
