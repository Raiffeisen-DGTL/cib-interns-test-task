package com.projects.raifservice.service

import com.projects.raifservice.model.Socks
import com.projects.raifservice.dao.SocksDao
import com.projects.raifservice.exception.RequestParamException
import org.springframework.stereotype.Service

@Service
class SocksServiceImpl(private val socksDao: SocksDao) : SocksService {

    override fun addSocks(socks: Socks) {
        val foundSocks = socksDao.findSocksByColorAndCottonPart(socks.color, socks.cottonPart)
        if (foundSocks != null) {
            socksDao.save(foundSocks.copy(quantity = foundSocks.quantity + socks.quantity))
        } else {
            socksDao.save(socks)
        }
    }

    override fun removeSocks(socks: Socks) {
        val foundSocks = socksDao.findSocksByColorAndCottonPart(socks.color, socks.cottonPart)
        if (foundSocks != null && foundSocks.quantity > socks.quantity) {
            socksDao.save(foundSocks.copy(quantity = foundSocks.quantity - socks.quantity))
        } else if (foundSocks != null && foundSocks.quantity == socks.quantity) {
            socksDao.delete(foundSocks)
        } else {
            throw RequestParamException()
        }
    }

    override fun findQuantitySocks(color: String, operation: String, cottonPart: Int): String =
        if (validateRequestParam(color, operation, cottonPart)) {
            (when (operation) {
                "moreThan" -> socksDao.findSocksByColorAndCottonPartIsGreaterThan(color, cottonPart)
                    .sumOf { it.quantity }
                "lessThan" -> socksDao.findSocksByColorAndCottonPartIsLessThan(color, cottonPart).sumOf { it.quantity }
                else -> socksDao.findSocksByColorAndCottonPart(color, cottonPart)?.quantity ?: 0
            }).toString()
        } else throw RequestParamException()

    fun validateRequestParam(color: String, operation: String, cottonPart: Int): Boolean =
        color != "" && (operation == "moreThan" || operation == "lessThan" || operation == "equal") && cottonPart <= 100 && cottonPart > 0
}
