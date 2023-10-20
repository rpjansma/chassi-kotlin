package api.domain.dto

import api.domain.entity.UserEntity

class UserDTO(
    val name: String,
    val password: String,
    val email: String
)

fun UserDTO.toUserEntity(): UserEntity {
    return UserEntity(
        name = this.name,
        password = this.password,
        email = this.email
    )
}