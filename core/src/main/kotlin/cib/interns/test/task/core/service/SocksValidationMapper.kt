package cib.interns.test.task.core.service

import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SocksValidationMapper {

    fun transform(obj: Socks): SocksValidationDto
    fun transformOut(obj: Socks): SocksOutValidationDto
}