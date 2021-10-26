package cib.interns.test.task

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan(basePackages = ["cib.interns.test.task.database.entity"])
@EnableJpaRepositories(basePackages = ["cib.interns.test.task.database.repository"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}