package ps.investments.stockmarket.position.customer.app.entrypoint.http.handler

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ps.investments.stockmarket.position.customer.app.entrypoint.http.handler.model.ErrorDetails
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@ControllerAdvice
class RestExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(RuntimeException::class)
    fun handleErrorRuntimeException(exception: RuntimeException): ResponseEntity<*>? {
        log.error("exception={}; msg={}", "RuntimeException", exception.message)
        val error = ErrorDetails(
            message = exception.message, timestamp = LocalDateTime.now().atOffset(ZoneOffset.UTC).format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            )
        )
        return ResponseEntity<Any>(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
