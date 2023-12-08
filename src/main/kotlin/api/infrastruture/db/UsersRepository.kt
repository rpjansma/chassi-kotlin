package api.infrastruture.db

import api.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsersRepository : JpaRepository<UserEntity, UUID> {
    fun findUserByEmail(email: String): UserEntity
}