package api.infrastruture.db.jdbc

import api.domain.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsersRepository : CrudRepository<UserEntity, UUID> {
    fun findUserByEmail(email: String): UserEntity
}