package com.example.login.domain.mapper

import com.example.login.data.remote.api.dto.loginDtos.LoginResponse
import com.example.login.data.remote.api.dto.loginDtos.State
import com.example.login.data.remote.api.dto.loginDtos.StateType
import com.example.login.data.remote.api.dto.loginDtos.User
import com.example.login.domain.model.user.ModelCompany
import com.example.login.domain.model.user.ModelUser
import com.example.login.domain.model.user.ModelState
import com.example.login.domain.model.user.ModelStateType
import com.example.login.domain.response.TokenResponse


fun LoginResponse.toDomainTokenResponse(): TokenResponse {
    return TokenResponse(
        token = this.token,
        user = this.user.toDomainUser(),
        messageDTO = this.message,
        listCompany = this.listCompany.map {
            ModelCompany(
                companyId = it.companyId,
                employeeRoleId = it.employeeRoleId,
                employeeId = it.employeeId,
                personId = it.personId
            )
        }
    )
}

fun User.toDomainUser(): ModelUser{
    return ModelUser(
        userId = this.userId,
        userUser = this.userUser,
        roleId = this.roleId,
        state = this.state.toDomainState(),
        userEditUserID = this.userEditUserID,
        userEditDate = this.userEditDate,
        userCreationDate = this.userCreationDate
    )
}
fun State.toDomainState(): ModelState {
    return ModelState(
        stateId = this.stateId,
        stateName = this.stateName,
        stateDescription = this.stateDescription,
        stateCreationDate = this.stateCreationDate,
        stateEditDate = this.stateEditDate,
        stateEditUserID = this.stateEditUserID,
        stateType = this.stateType.toDomainStateType()
    )
}
fun StateType.toDomainStateType(): ModelStateType {
    return ModelStateType(
        stateTypeId = this.stateTypeId,
        stateTypeName = this.stateTypeName,
        stateTypeDescription = this.stateTypeDescription,
        stateTypeCreationDate = this.stateTypeCreationDate,
        stateTypeEditDate = this.stateTypeEditDate,
        stateTypeEditUserID = this.stateTypeEditUserID
    )
}
