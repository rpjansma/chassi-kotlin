package ps.investments.stockmarket.dividends.events.app.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RestTemplateConfig {
    @Bean
    fun restTemplate(restTemplate: RestTemplateBuilder): RestTemplate = restTemplate.build()
}
