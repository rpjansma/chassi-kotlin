package app.`interface`.http

import app.domain.dto.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping
    fun index(): List<User> = listOf(
        User("Raul", "Senha"),
        User("Raul", "Senha"),
        User("Raul", "Senha"),
    )
}