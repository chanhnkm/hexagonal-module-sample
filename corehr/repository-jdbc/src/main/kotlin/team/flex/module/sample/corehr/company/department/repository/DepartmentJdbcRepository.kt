/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department.repository

import org.springframework.data.repository.CrudRepository
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

    fun deleteByIdAndCompanyId(
        departmentId: Long,
        companyId: Long,
    ): Long?
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

    override fun add(
        companyIdentity: CompanyIdentity,
        parentDepartmentId: Long,
        departmentName: String,
    ): DepartmentModel? {
        val now = Instant.now()
        val entity = DepartmentEntity(
            companyId = companyIdentity.companyId,
            parentDepartmentId = parentDepartmentId,
            name = departmentName,
            createdAt = now,
            updatedAt = now,
        )
        val saved = departmentJdbcRepository.save(entity)
        return saved.toModel()
    }

    override fun delete(
        companyIdentity: CompanyIdentity,
        departmentIdentity: DepartmentIdentity
    ): Long? {
        departmentJdbcRepository.deleteByIdAndCompanyId(
            departmentId = departmentIdentity.departmentId,
            companyId = companyIdentity.companyId,
        )
        return departmentIdentity.departmentId
    }

    override fun modify(
        companyIdentity: CompanyIdentity,
        departmentIdentity: DepartmentIdentity,
        parentDepartmentId: Long,
        departmentName: String,
    ): DepartmentModel? {
        val now = Instant.now()
        val model = departmentJdbcRepository.findByIdAndCompanyId(
            departmentId = departmentIdentity.departmentId,
            companyId = companyIdentity.companyId,
        )

        model?.let {
            it.parentDepartmentId = parentDepartmentId
            it.name = departmentName
            it.updatedAt = now
        }!!

        val saved = departmentJdbcRepository.save(model)
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
