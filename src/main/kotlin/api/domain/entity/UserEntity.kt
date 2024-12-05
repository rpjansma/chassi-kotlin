package api.domain.entity

import api.domain.dto.UserDTO
import api.domain.enums.Role
import java.time.LocalDateTime
import java.util.UUID
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val active: Boolean = true,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role = Role.USER,

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column(nullable = false)
    val updatedAt: LocalDateTime? = LocalDateTime.now()
)

fun UserEntity.toUserDTO(): UserDTO {
    return UserDTO(
        name = this.name,
        password = this.password,
        email = this.email,
        active = this.active,
        role = this.role
    )
}