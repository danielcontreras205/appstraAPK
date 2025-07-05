package com.example.login.domain.mapper

import com.example.login.data.remote.dto.UsuarioDTO.LoginResponse
import com.example.login.data.remote.dto.UsuarioDTO.State
import com.example.login.data.remote.dto.UsuarioDTO.StateType
import com.example.login.data.remote.dto.UsuarioDTO.User
import com.example.login.data.remote.dto.UsuarioDTO.City
import com.example.login.data.remote.dto.UsuarioDTO.Country
import com.example.login.data.remote.dto.UsuarioDTO.Department
import com.example.login.data.remote.dto.UsuarioDTO.DocumentType
import com.example.login.data.remote.dto.UsuarioDTO.PersonResponse
import com.example.login.data.remote.dto.UsuarioDTO.Province
import com.example.login.domain.model.user.EntityCity
import com.example.login.domain.model.user.ModelCompany
import com.example.login.domain.model.user.EntityCountry
import com.example.login.domain.model.user.EntityDepartment
import com.example.login.domain.model.user.EntityDocumentType
import com.example.login.domain.model.user.EntityProvince
import com.example.login.domain.model.user.EntityUser
import com.example.login.domain.model.user.EntityState
import com.example.login.domain.model.user.EntityStateType
import com.example.login.domain.response.PersonaResponse
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

fun PersonResponse.toDomainPersonaResponse(): PersonaResponse {
    return PersonaResponse(
        personId = this.personId,
        personFirstName = this.personFirstName,
        personLastName = this.personLastName,
        personEmail = this.personEmail,
        personMobilePhone = this.personMobilePhone,
        personLandlinePhone = this.personLandlinePhone,
        personAddress = this.personAddress,
        city = this.city.toDomainCity(),
        personRH = this.personRH,
        personBloodType = this.personBloodType,
        personCreationDate = this.personCreationDate,
        personBirthdayDate = this.personBirthdayDate,
        personChildren = this.personChildren,
        personNumberIdentification = this.personNumberIdentification,
        personDateIssueIdentification = this.personDateIssueIdentification,
        personEditDate = this.personEditDate,
        personEditUserID = this.personEditUserID,
        user = this.user.toDomainUser(),
        documentType = this.documentType.toDomainDocumentType()
    )
}

fun City.toDomainCity(): EntityCity {
    return EntityCity(
        cityId = this.cityId,
        cityName = this.cityName,
        cityCode = this.cityCode,
        cityCreationDate = this.cityCreationDate,
        cityEditDate = this.cityEditDate,
        cityEditUserID = this.cityEditUserID,
        province = province.toDomainProvince()
    )
}


fun Province.toDomainProvince(): EntityProvince {
    return EntityProvince(
        provinceId = this.provinceId,
        provinceName = this.provinceName,
        provinceCreationDate = this.provinceCreationDate,
        provinceEditDate = this.provinceEditDate,
        provinceEditUserID = this.provinceEditUserID,
        department = this.department.toDomainDepartment()
    )
}

fun Department.toDomainDepartment(): EntityDepartment {
    return EntityDepartment(
        departmentId = this.departmentId,
        departmentName = this.departmentName,
        departmentCreationDate =this.departmentCreationDate,
        departmentEditDate =this.departmentEditDate,
        departmentEditUserID =this.departmentEditUserID,
        country = this.country.toDomainCountry()
    )
}

fun Country.toDomainCountry(): EntityCountry {
    return EntityCountry(
        countryId = this.countryId,
        countryName = this.countryName,
        countryCreationDate = this.countryEditDate,
        countryEditDate = this.countryEditDate,
    )
}

fun User.toDomainUser(): EntityUser{
    return EntityUser(
        userId = this.userId,
        userUser = this.userUser,
        roleId = this.roleId,
        state = this.state.toDomainState(),
        userEditUserID = this.userEditUserID,
        userEditDate = this.userEditDate,
        userCreationDate = this.userCreationDate
    )
}
fun State.toDomainState(): EntityState {
    return EntityState(
        stateId = this.stateId,
        stateName = this.stateName,
        stateDescription = this.stateDescription,
        stateCreationDate = this.stateCreationDate,
        stateEditDate = this.stateEditDate,
        stateEditUserID = this.stateEditUserID,
        stateType = this.stateType.toDomainStateType()
    )
}
fun StateType.toDomainStateType(): EntityStateType {
    return EntityStateType(
        stateTypeId = this.stateTypeId,
        stateTypeName = this.stateTypeName,
        stateTypeDescription = this.stateTypeDescription,
        stateTypeCreationDate = this.stateTypeCreationDate,
        stateTypeEditDate = this.stateTypeEditDate,
        stateTypeEditUserID = this.stateTypeEditUserID
    )
}
fun DocumentType.toDomainDocumentType(): EntityDocumentType {
    return EntityDocumentType(
        documentTypeId = this.documentTypeId,
        documentTypeName = this.documentTypeName,
        documentTypeInitial = this.documentTypeInitial,
        documentDescription = this.documentDescription,
        documentTypeCreationDate = this.documentTypeCreationDate,
        documentTypeEditDate = this.documentTypeEditDate,
        documentTypeEditUserID = this.documentTypeEditUserID
    )
}
