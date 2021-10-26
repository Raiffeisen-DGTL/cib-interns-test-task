package cib.interns.test.task.core.service

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CorrectOperationValidator(
    private val operationsMap: Map<String, String>
) : ConstraintValidator<CorrectOperation, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        var isValid = true

        value?.let {
            isValid = operationsMap.keys.contains(it)
        }

        return isValid
    }
}