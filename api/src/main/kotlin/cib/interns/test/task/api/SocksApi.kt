package cib.interns.test.task.api

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(Constant.SOCKS_BASE_URL)
interface SocksApi {

    @PostMapping("/income")
    fun postIncome(@RequestBody request: SocksIncomeRequest): SocksResponse

    @PostMapping("/outcome")
    fun postOutcome()

    @GetMapping()
    fun getSocks(): SocksResponse
}