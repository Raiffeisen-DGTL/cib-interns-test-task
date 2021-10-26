package cib.interns.test.task.controller

import cib.interns.test.task.api.SocksApi
import cib.interns.test.task.api.SocksIncomeRequest
import cib.interns.test.task.api.SocksResponse
import cib.interns.test.task.service.SocksService
import org.springframework.web.bind.annotation.RestController

@RestController
class SocksController(
    private val service: SocksService,
    private val mapper: SocksMapper,
) : SocksApi {

    override fun postIncome(request: SocksIncomeRequest): SocksResponse {
        val response = service.addSocks(mapper.transform(request))

        return mapper.transform(response)
    }

    override fun postOutcome() {
        TODO("Not yet implemented")
    }

    override fun getSocks(): SocksResponse {
        TODO("Not yet implemented")
    }

}