package api.domain.dto

import api.domain.entity.UserEntity
import api.domain.enums.Role

class UserDTO(
    val name: String,
    val password: String,
    val email: String,
    val active: Boolean,
    val role: Role
)

fun UserDTO.toUserEntity(): UserEntity {
    return UserEntity(
        name = this.name,
        password = this.password,
        email = this.email,
        active = this.active,
        role = this.role
    )
}