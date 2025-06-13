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
import com.example.login.domain.model.user.ModelCity
import com.example.login.domain.model.user.ModelCompany
import com.example.login.domain.model.user.ModelCountry
import com.example.login.domain.model.user.ModelDepartment
import com.example.login.domain.model.user.ModelDocumentType
import com.example.login.domain.model.user.ModelProvince
import com.example.login.domain.model.user.ModelUser
import com.example.login.domain.model.user.ModelState
import com.example.login.domain.model.user.ModelStateType
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

fun City.toDomainCity(): ModelCity {
    return ModelCity(
        cityId = this.cityId,
        cityName = this.cityName,
        cityCode = this.cityCode,
        cityCreationDate = this.cityCreationDate,
        cityEditDate = this.cityEditDate,
        cityEditUserID = this.cityEditUserID,
        province = province.toDomainProvince()
    )
}


fun Province.toDomainProvince(): ModelProvince {
    return ModelProvince(
        provinceId = this.provinceId,
        provinceName = this.provinceName,
        provinceCreationDate = this.provinceCreationDate,
        provinceEditDate = this.provinceEditDate,
        provinceEditUserID = this.provinceEditUserID,
        department = this.department.toDomainDepartment()
    )
}

fun Department.toDomainDepartment(): ModelDepartment {
    return ModelDepartment(
        departmentId = this.departmentId,
        departmentName = this.departmentName,
        departmentCreationDate =this.departmentCreationDate,
        departmentEditDate =this.departmentEditDate,
        departmentEditUserID =this.departmentEditUserID,
        country = this.country.toDomainCountry()
    )
}

fun Country.toDomainCountry(): ModelCountry {
    return ModelCountry(
        countryId = this.countryId,
        countryName = this.countryName,
        countryCreationDate = this.countryEditDate,
        countryEditDate = this.countryEditDate,
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
fun DocumentType.toDomainDocumentType(): ModelDocumentType {
    return ModelDocumentType(
        documentTypeId = this.documentTypeId,
        documentTypeName = this.documentTypeName,
        documentTypeInitial = this.documentTypeInitial,
        documentDescription = this.documentDescription,
        documentTypeCreationDate = this.documentTypeCreationDate,
        documentTypeEditDate = this.documentTypeEditDate,
        documentTypeEditUserID = this.documentTypeEditUserID
    )
}
