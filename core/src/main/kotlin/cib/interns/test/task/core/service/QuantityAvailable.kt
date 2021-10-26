package cib.interns.test.task.core.service

import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [QuantityValidator::class])
@MustBeDocumented
annotation class QuantityAvailable(
    val message: String = "You can't remove more socks than in stock",
    val payload: Array<KClass<out Any>> = [],
    val groups: Array<KClass<out Any>> = [],
)
