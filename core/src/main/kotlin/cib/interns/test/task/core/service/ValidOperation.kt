package cib.interns.test.task.core.service

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ValidOperation {

    @Bean("operationsMap")
    fun getValidOperationMap(): Map<String, String> {
        return mapOf(
            Pair("equals", "="),
            Pair("moreThan", ">="),
            Pair("lessThan","<=")
        )
    }
}