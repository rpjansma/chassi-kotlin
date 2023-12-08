package api.domain.service.users

import api.domain.dto.UserDTO
import api.domain.entity.toUserDTO
import api.infrastruture.db.UsersRepository
import org.springframework.stereotype.Component

@Component
class UsersService(
    private val dataSource: UsersRepository
) {
    fun findAllUsers(): List<UserDTO> {
        return dataSource.findAll().map { it.toUserDTO() }
    }
}


