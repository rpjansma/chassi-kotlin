package ps.investments.stockmarket.dividends.events.app.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig(
    @Value("\${application-version}")
    private val version: String
) {
    private fun apiInfo(): Info {
        return Info().apply {
            this.title = "Stockmark Dividends Events"
            this.description = "API de investimentos do time de Renda Variável que será usado para processar os eventos de dividendos"
            this.version = version
            this.contact = Contact().apply {
                this.url = "https://github.com/ps-investments/stockmarket-backoffice"
                this.name = "Saint-Tropez"
                this.email = "l-investimento-sainttropez@pagseguro.com"
            }
            this.license = License().apply {
                this.name = "PagInvest"
            }
        }
    }

    @Bean
    fun openApi(): OpenAPI = OpenAPI().apply { this.info = apiInfo() }
}
