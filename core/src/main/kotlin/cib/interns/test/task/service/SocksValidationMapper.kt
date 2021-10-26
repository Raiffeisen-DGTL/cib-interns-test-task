package cib.interns.test.task.service

import cib.interns.test.task.api.SocksIncomeRequest
import cib.interns.test.task.api.SocksResponse
import cib.interns.test.task.service.Socks
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SocksValidationMapper {

    fun transform(obj: Socks): SocksValidationDto
}