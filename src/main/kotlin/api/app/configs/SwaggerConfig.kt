package api.app.configs

import api.app.entrypoint.http.UserController.Companion.USER_TAG
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
    info = Info(
        title = "User API",
        version = "0.0.1",
        description = "Utilities API for users managements",
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
@Configuration
open class SwaggerConfig {

    @Bean
    open fun customOpenAPI(): OpenAPI {
        return OpenAPI()
    }
}