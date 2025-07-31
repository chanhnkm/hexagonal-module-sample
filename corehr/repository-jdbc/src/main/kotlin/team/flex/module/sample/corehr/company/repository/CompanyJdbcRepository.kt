/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import java.time.Instant
import team.flex.module.sample.corehr.company.Company
import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.CompanyModel

interface CompanyJdbcRepository : CrudRepository<CompanyEntity, Long>

class CompanyRepositoryImpl(
    private val companyJdbcRepository: CompanyJdbcRepository,
) : CompanyRepository {
    override fun findByCompanyIdentity(companyIdentity: CompanyIdentity): CompanyModel? {
        return companyJdbcRepository.findByIdOrNull(id = companyIdentity.companyId)?.toModel()
    }

    override fun findAll(): List<CompanyModel> {
        return companyJdbcRepository.findAll().map { it.toModel() }
    }

    override fun add(companyName: String): CompanyModel? {
        val now = Instant.now()
        val entity = CompanyEntity(
            name = companyName,
            createdAt = now,
            updatedAt = now
        )
        val saved = companyJdbcRepository.save(entity)
        return saved
    }

    override fun delete(companyIdentity: CompanyIdentity): Long? {
        companyJdbcRepository.deleteById(companyIdentity.companyId)
        return companyIdentity.companyId
    }

    override fun modify(companyIdentity: CompanyIdentity, companyName: String): CompanyModel? {
        val now = Instant.now()
        val entity = companyJdbcRepository.findByIdOrNull(id = companyIdentity.companyId)
            ?: throw IllegalArgumentException("Invalid companyId: ${companyIdentity.companyId}")

        entity.name = companyName
        entity.updatedAt = now

        val saved = companyJdbcRepository.save(entity)
        return saved.toModel()
    }

    private fun CompanyEntity.toModel() =
        Company(
            companyId = companyId,
            name = name,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
}
