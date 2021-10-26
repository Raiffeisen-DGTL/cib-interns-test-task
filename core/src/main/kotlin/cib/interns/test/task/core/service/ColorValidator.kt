package cib.interns.test.task.core.service

import cib.interns.test.task.database.repository.SocksRepository
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ColorValidator(
    private val socksRepository: SocksRepository
) : ConstraintValidator<ColorAvailable, SocksOutValidationDto> {

    override fun isValid(value: SocksOutValidationDto, context: ConstraintValidatorContext?): Boolean {

        return socksRepository.findSocksByColorAndCottonPart(value.color, value.cottonPart) != null
    }
}