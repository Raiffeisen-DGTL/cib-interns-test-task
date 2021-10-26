package cib.interns.test.task.core.service

import cib.interns.test.task.api.SocksFindRequest
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SocksValidationMapper {

    fun transform(obj: Socks): SocksValidationDto
    fun transformOut(obj: Socks): SocksOutValidationDto
    fun transformFind(obj: SocksFind): SocksFindValidationDto
}