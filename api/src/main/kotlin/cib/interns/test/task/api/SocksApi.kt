package cib.interns.test.task.api

import org.springframework.web.bind.annotation.*

@RequestMapping(Constant.SOCKS_BASE_URL)
interface SocksApi {

    @PostMapping("/income")
    fun postIncome(@RequestBody request: SocksRequest): SocksResponse

    @PostMapping("/outcome")
    fun postOutcome(@RequestBody request: SocksRequest): SocksResponse

    @GetMapping()
    fun getSocks(@RequestParam color: String,
                 @RequestParam operation: String?,
                 @RequestParam cottonPart: Int?): SocksGetResponse
}