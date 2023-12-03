package api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["api"])
@EnableJpaRepositories
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
