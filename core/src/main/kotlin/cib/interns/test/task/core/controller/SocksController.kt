package cib.interns.test.task.core.controller

import cib.interns.test.task.api.*
import cib.interns.test.task.core.service.SocksFind
import cib.interns.test.task.core.service.SocksService
import org.springframework.web.bind.annotation.RestController

@RestController
class SocksController(
    private val service: SocksService,
    private val mapper: SocksMapper,
) : SocksApi {

    private val operations = setOf<String>("equals", "moreThan", "lessThan")

    override fun postIncome(request: SocksRequest): SocksResponse {
        val response = service.addSocks(mapper.transform(request))

        return mapper.transform(response)
    }

    override fun postOutcome(request: SocksRequest): SocksResponse {
        val response = service.removeSocks(mapper.transform(request))

        return mapper.transform(response)
    }

    override fun getSocks(color: String, operation: String?, cottonPart: Int?): SocksGetResponse {
        val response = service.getSocks(SocksFind(color, operation, cottonPart))
        return SocksGetResponse(response)
    }


}