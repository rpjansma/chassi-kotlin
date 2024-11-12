package api.domain.entity

import api.domain.dto.UserDTO
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("users")
class EmailEntity(
    @Id
    val id: UUID? = null,
    val name: String,
    val email: String,
    val message: String,
    val createdAt: LocalDateTime? = null,
    val updateAt: LocalDateTime? = null
)