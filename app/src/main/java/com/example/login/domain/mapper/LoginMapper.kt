package com.example.login.domain.mapper

import com.example.login.data.remote.api.dto.loginDtos.LoginResponse
import com.example.login.domain.model.Company
import com.example.login.domain.model.User

fun LoginResponse.toDomainUser(): User {
    return User(
        id = this.user.userId,
        username = this.user.userUser,
        roleId = this.user.roleId,
        stateName = this.user.state.stateName,
        companies = this.listCompany.map {
            Company(
                companyId = it.companyId,
                employeeRoleId = it.employeeRoleId,
                employeeId = it.employeeId,
                personId = it.personId
            )
        }
    )
}
