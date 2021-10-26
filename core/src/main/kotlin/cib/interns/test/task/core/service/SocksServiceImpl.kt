package cib.interns.test.task.core.service

import cib.interns.test.task.database.repository.SocksRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.validation.ConstraintViolationException
import javax.validation.Validator

@Service
class SocksServiceImpl(
    private val socksRepository: SocksRepository,
    private val validationMapper: SocksValidationMapper,
    private val serviceMapper: SocksServiceMapper,
    private val validator: Validator,
    private val operationsMap: Map<String, String>,
    private val em: EntityManager,
) : SocksService {
    override fun getSocks(request: SocksFind): Int {
        validate(request)

        val operation = operationsMap[request.operation]!!
        val sql = "SELECT COALESCE(SUM(quantity), 0) as quantity from socks s WHERE s.color = '${request.color}' AND s.cotton_part $operation ${request.cottonPart};"
        val result = em.createNativeQuery(sql).resultList.first() as BigDecimal

        return result.toInt()
    }

    override fun addSocks(request: Socks): Socks {
        validate(request)

        val foundSocks = socksRepository.findSocksByColorAndCottonPart(request.color, request.cottonPart)?.let {
            it.quantity = it.quantity?.plus(request.quantity)
            socksRepository.save(it)
        } ?: socksRepository.save(serviceMapper.transform(request))

        return serviceMapper.transform(foundSocks)
    }

    override fun removeSocks(request: Socks): Socks {
        validate(request)

        val foundSocks = socksRepository.findSocksByColorAndCottonPart(request.color, request.cottonPart)!!.let {
            it.quantity = it.quantity?.minus(request.quantity)
            socksRepository.save(it)
        }

        return serviceMapper.transform(foundSocks)
    }

    private fun validate(request: Socks){
        val validationDto = validationMapper.transformOut(request)
        val constraintViolations = validator.validate(validationDto)
        if (constraintViolations.size != 0) {
            throw ConstraintViolationException(constraintViolations)
        }
    }

    private fun validate(request: SocksFind){
        val validationDto = validationMapper.transformFind(request)
        val constraintViolations =validator.validate(validationDto)

        if (constraintViolations.size != 0) {
            throw ConstraintViolationException(constraintViolations)
        }
    }

}