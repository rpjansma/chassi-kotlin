package api.app.entrypoint.http

import api.app.entrypoint.http.UserController.Companion.USER_TAG
import api.domain.dto.UserDTO
import api.domain.service.users.UsersService
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ps.investments.stockmarket.position.customer.app.entrypoint.http.handler.model.ErrorDetails
import api.utils.Constants.PASSWORD_HEADER
import api.utils.Constants.USERNAME_HEADER

@RestController
@RequestMapping("api/user")
@OpenAPIDefinition(
    info = Info(
        title = "User API",
        version = "0.0.1",
        description = "Api de utilidades para usuários",
        contact = Contact(
            url = "https://www.linkedin.com/in/raul-paes/",
            name = "Raul Paes"
        )
    ),
    tags = [
        Tag(
            name = USER_TAG,
        )]
)
class UserController(private val usersService: UsersService) {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        const val USER_TAG = "Users"
    }

    @GetMapping("/list")
    @Operation(
        tags = [USER_TAG],
        summary = "Find users list",
        description = "Consult all users on database",
        responses = [
            ApiResponse(
                description = "Default answer on success",
                responseCode = "200",
                content = [Content(
                    schema = Schema(implementation = Array<UserDTO>::class),
                    mediaType = "application/json"
                )]
            ),
            ApiResponse(
                description = "Error consulting user list",
                responseCode = "400",
                content = [Content(
                    schema = Schema(implementation = ErrorDetails::class)
                )]
            )]
    )
    fun findUserList(): List<UserDTO> {
        log.info("Retriving list of all users")
        return usersService.findAllUsers()
    }

    @PostMapping("/auth")
    @Operation(
        tags = [USER_TAG],
        summary = "Authenticate user",
        description = "Authenticate user with Auth0",
        responses = [
            ApiResponse(
                description = "Default answer on success",
                responseCode = "200",
                content = [Content(
                    schema = Schema(implementation = UserDTO::class),
                    mediaType = "application/json"
                )]
            ),
            ApiResponse(
                description = "Error authenticating user",
                responseCode = "400",
                content = [Content(
                    schema = Schema(implementation = ErrorDetails::class)
                )]
            )]
    )
    fun autenticateUser(
        @RequestHeader(name = USERNAME_HEADER) username: String,
        @RequestHeader(name = PASSWORD_HEADER) password: String,
    ): ResponseEntity<Any> {
        log.info("m=autenticateUser i=auth_user msg=Authenticating user...")
        return ResponseEntity.ok().build()
    }
}