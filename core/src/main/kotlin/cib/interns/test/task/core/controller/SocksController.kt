package cib.interns.test.task.core.controller

import cib.interns.test.task.api.SocksApi
import cib.interns.test.task.api.SocksRequest
import cib.interns.test.task.api.SocksResponse
import cib.interns.test.task.core.service.SocksService
import org.springframework.web.bind.annotation.RestController

@RestController
class SocksController(
    private val service: SocksService,
    private val mapper: SocksMapper,
) : SocksApi {

    override fun postIncome(request: SocksRequest): SocksResponse {
        val response = service.addSocks(mapper.transform(request))

        return mapper.transform(response)
    }

    override fun postOutcome(request: SocksRequest): SocksResponse {
        val response = service.removeSocks(mapper.transform(request))

        return mapper.transform(response)
    }

    override fun getSocks(): SocksResponse {
        TODO("Not yet implemented")
    }

}