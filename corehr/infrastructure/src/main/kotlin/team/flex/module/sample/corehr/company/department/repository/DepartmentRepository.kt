/*
 * Copyright 2024 flex Inc. - All Rights Reserved.
 */

package team.flex.module.sample.corehr.company.department.repository

import team.flex.module.sample.corehr.company.CompanyIdentity
import team.flex.module.sample.corehr.company.department.DepartmentIdentity
import team.flex.module.sample.corehr.company.department.DepartmentModel

interface DepartmentRepository {
    fun findByDepartmentIdentity(
        companyIdentity: CompanyIdentity,
        departmentIdentity: DepartmentIdentity,
    ): DepartmentModel?

    fun findAllByCompanyIdentity(companyIdentity: CompanyIdentity): List<DepartmentModel>

    fun add(companyId: Long,
            parentDepartmentId: Long,
            departmentName: String
    ): DepartmentModel?

    fun delete(departmentIdentity: DepartmentIdentity): Long?

    fun modify(departmentId: Long,
               parentDepartmentId: Long,
               departmentName: String,
    ): DepartmentModel?
}
