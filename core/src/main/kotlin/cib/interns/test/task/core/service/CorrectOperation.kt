package cib.interns.test.task.core.service

import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CorrectOperationValidator::class])
@MustBeDocumented
annotation class CorrectOperation(
    val message: String = "Operation is incorrect",
    val payload: Array<KClass<out Any>> = [],
    val groups: Array<KClass<out Any>> = [],
)
