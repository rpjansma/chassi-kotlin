package api.app.entrypoint.http

import api.domain.service.users.EmailService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class EmailController(private val emailService: EmailService) {

    @GetMapping("/sendEmail")
    fun sendEmail(
        @RequestParam to: String,
        @RequestParam subject: String,
        @RequestParam text: String
    ): String {
        emailService.sendSimpleEmail(to, subject, text)
        return "Email sent successfully"
    }
}