package api.domain.service.users

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import javax.mail.internet.MimeMessage

@Service
class EmailService(private val mailSender: JavaMailSender) {

    fun sendSimpleEmail(to: String, subject: String, text: String) {
        val message: MimeMessage = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true) // true indicates multipart message

        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(text, true) // true indicates HTML

        mailSender.send(message)
    }
}