package api.domain.entity

import api.domain.dto.UserDTO
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.Email

@Table("users")
class UserEntity(
    @Id
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