package cib.interns.test.task.core.controller

import cib.interns.test.task.api.SocksFindRequest
import cib.interns.test.task.api.SocksGetResponse
import cib.interns.test.task.api.SocksRequest
import cib.interns.test.task.api.SocksResponse
import cib.interns.test.task.core.service.Socks
import cib.interns.test.task.core.service.SocksFind
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SocksMapper {

    fun transform(obj: SocksRequest): Socks

    fun transform(obj: Socks):SocksResponse

    fun transform(obj: SocksFindRequest): SocksFind
}