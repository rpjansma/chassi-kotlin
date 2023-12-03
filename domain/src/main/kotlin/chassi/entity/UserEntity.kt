package api.domain.entity

import api.domain.dto.UserDTO
import java.time.LocalDateTime
import java.util.*

class UserEntity(
    val id: UUID? = null,
    val name: String,
    val password: String,
    val email: String,
    val createdAt: LocalDateTime? = null,
    val updateAt: LocalDateTime? = null
)

fun UserEntity.toUserDTO(): UserDTO {
    return UserDTO(
        name = this.name,
        password = this.password,
        email = this.email
    )
}