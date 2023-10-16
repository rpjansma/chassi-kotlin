package api.domain.dto

import api.domain.entity.UserEntity
import javax.validation.constraints.Email

class UserDTO(
    val name: String,
    val password: String,
    @Email
    val email: String
)

fun UserDTO.toUserEntity(): UserEntity {
    return UserEntity(
        name = this.name,
        password = this.password,
        email = this.email
    )
}