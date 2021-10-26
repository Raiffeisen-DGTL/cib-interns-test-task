package cib.interns.test.task.service

import cib.interns.test.task.database.repository.SocksRepository
import cib.interns.test.task.database.entity.Socks as SocksEntity
import org.springframework.stereotype.Service
import javax.validation.ConstraintViolationException
import javax.validation.Validator

@Service
class SocksServiceImpl(
    private val socksRepository: SocksRepository,
    private val validationMapper: SocksValidationMapper,
    private val serviceMapper: SocksServiceMapper,
    private val validator: Validator,
) : SocksService {
    override fun getSocks(): List<Socks> {
        TODO("Not yet implemented")
    }

    override fun addSocks(request: Socks): Socks {
        val validationDto = validationMapper.transform(request)
        val a = validator.validate(validationDto)
        if (a.size != 0) {
            throw ConstraintViolationException(a)
        }

        val foundSocks = socksRepository.findSocksByColorAndCottonPart(request.color, request.cottonPart)?.let {
            it.quantity = it.quantity?.plus(request.quantity)
            socksRepository.save(it)
        } ?: socksRepository.save(serviceMapper.transform(request))
        
        return serviceMapper.transform(foundSocks)
    }

    override fun removeSocks(request: Socks): Socks {
        TODO("Not yet implemented")
    }
}