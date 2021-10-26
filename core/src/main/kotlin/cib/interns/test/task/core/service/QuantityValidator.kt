package cib.interns.test.task.core.service

import cib.interns.test.task.database.repository.SocksRepository
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class QuantityValidator(
    private val socksRepository: SocksRepository
) : ConstraintValidator<QuantityAvailable, SocksOutValidationDto> {

    override fun isValid(value: SocksOutValidationDto, context: ConstraintValidatorContext?): Boolean {
        val currentQuantity = socksRepository.findSocksByColorAndCottonPart(value.color, value.cottonPart)?.quantity ?: 0

        return value.quantity <= currentQuantity
    }
}