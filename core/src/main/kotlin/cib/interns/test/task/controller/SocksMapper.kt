package cib.interns.test.task.controller

import cib.interns.test.task.api.SocksIncomeRequest
import cib.interns.test.task.api.SocksResponse
import cib.interns.test.task.service.Socks
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SocksMapper {

    fun transform(obj: SocksIncomeRequest):Socks

    fun transform(obj: Socks):SocksResponse
}