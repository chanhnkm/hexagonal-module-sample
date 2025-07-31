/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.employee.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
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
        companyId: Long,
        employeeNumber: String,
        employeeName: String,
    ): EmployeeModel? {
        val now = Instant.now()
        val entity = EmployeeEntity(
            companyId = companyId,
            employeeNumber = employeeNumber,
            name = employeeName,
            createdAt = now,
            updatedAt = now,
        )
        val saved = employeeJdbcRepository.save(entity)
        return saved
    }

    override fun delete(
        employeeIdentity: EmployeeIdentity,
    ): Long? {
        employeeJdbcRepository.deleteById(employeeIdentity.employeeId)
        return employeeIdentity.employeeId
    }

    override fun modify(
        employeeId: Long,
        employeeNumber: String,
        employeeName: String,
    ): EmployeeModel? {
        val now = Instant.now()
        val entity = employeeJdbcRepository.findByIdOrNull(id = employeeId)
            ?: throw IllegalArgumentException("Invalid employeeId: $employeeId")

        entity.employeeNumber = employeeNumber
        entity.name = employeeName
        entity.updatedAt = now

        val saved = employeeJdbcRepository.save(entity)
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
