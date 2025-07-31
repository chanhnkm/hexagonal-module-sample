/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.employee.repository

import org.springframework.data.repository.CrudRepository
import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.employee.Employee
import team.flex.module.sample.corehr.employee.EmployeeIdentity
import team.flex.module.sample.corehr.employee.EmployeeModel
import java.time.Instant

interface EmployeeJdbcRepository : CrudRepository<EmployeeEntity, Long> {
    fun findByIdAndCompanyId(
        employeeId: Long,
        companyId: Long,
    ): EmployeeEntity?

    fun findByCompanyId(companyId: Long): List<EmployeeEntity>

    fun deleteByIdAndCompanyId(
        employeeId: Long,
        companyId: Long,
    ): Long?
}

class EmployeeRepositoryImpl(
    private val employeeJdbcRepository: EmployeeJdbcRepository,
) : EmployeeRepository {
    override fun findByEmployeeIdentity(
        companyIdentity: CompanyIdentity,
        employeeIdentity: EmployeeIdentity,
    ): EmployeeModel? {
        return employeeJdbcRepository.findByIdAndCompanyId(
            employeeId = employeeIdentity.employeeId,
            companyId = companyIdentity.companyId,
        )?.toModel()
    }

    override fun findAllByCompanyIdentity(companyIdentity: CompanyIdentity): List<EmployeeModel> {
        return employeeJdbcRepository.findByCompanyId(
            companyId = companyIdentity.companyId,
        ).map { it.toModel() }
    }

    override fun add(
        companyIdentity: CompanyIdentity,
        employeeNumber: String,
        employeeName: String,
    ): EmployeeModel? {
        val now = Instant.now()
        val entity = EmployeeEntity(
            companyId = companyIdentity.companyId,
            employeeNumber = employeeNumber,
            name = employeeName,
            createdAt = now,
            updatedAt = now,
        )
        val saved = employeeJdbcRepository.save(entity)
        return saved.toModel()
    }

    override fun delete(
        companyIdentity: CompanyIdentity,
        employeeIdentity: EmployeeIdentity,
    ): Long? {
        employeeJdbcRepository.deleteByIdAndCompanyId(
            employeeId = employeeIdentity.employeeId,
            companyId = companyIdentity.companyId,
        )
        return employeeIdentity.employeeId
    }

    override fun modify(
        companyIdentity: CompanyIdentity,
        employeeIdentity: EmployeeIdentity,
        employeeNumber: String,
        employeeName: String,
    ): EmployeeModel? {
        val now = Instant.now()
        val model = employeeJdbcRepository.findByIdAndCompanyId(
            employeeId = employeeIdentity.employeeId,
            companyId = companyIdentity.companyId,
        )

        model?.let {
            it.employeeNumber = employeeNumber
            it.name = employeeName
            it.updatedAt = now
        }!!

        val saved = employeeJdbcRepository.save(model)
        return saved.toModel()
    }

    private fun EmployeeEntity.toModel() =
        Employee(
            employeeId = employeeId,
            companyId = companyId,
            employeeNumber = employeeNumber,
            name = name,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
}
