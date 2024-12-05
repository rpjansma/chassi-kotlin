package api.app.entrypoint.http

import api.app.entrypoint.handler.model.ErrorDetails
import api.domain.dto.UserDTO
import api.domain.service.users.UsersService
import api.utils.Constants.PASSWORD_HEADER
import api.utils.Constants.USERNAME_HEADER
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
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
        log.info("Retrieving list of all users")
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
        log.info("m=authenticateUser i=auth_user msg=Authenticating user...")
        return ResponseEntity.ok().build()
    }
}