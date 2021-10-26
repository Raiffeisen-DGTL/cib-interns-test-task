package cib.interns.test.task.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(Constant.SOCKS_BASE_URL)
interface SocksApi {

    @PostMapping("/income")
    fun postIncome(@RequestBody request: SocksRequest): SocksResponse

    @PostMapping("/outcome")
    fun postOutcome(@RequestBody request: SocksRequest): SocksResponse

    @GetMapping()
    fun getSocks(): SocksResponse
}