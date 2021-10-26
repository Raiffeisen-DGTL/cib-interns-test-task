package cib.interns.test.task.core.service

import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ColorValidator::class])
@MustBeDocumented
annotation class OperationAndCottonPartAreCorrect(
    val message: String = "Socks with color or cottonPart is unavailable",
    val payload: Array<KClass<out Any>> = [],
    val groups: Array<KClass<out Any>> = [],
)
