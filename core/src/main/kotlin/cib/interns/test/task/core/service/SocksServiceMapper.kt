package cib.interns.test.task.core.service

import org.mapstruct.Mapper
import cib.interns.test.task.database.entity.Socks as SocksEntity

@Mapper(componentModel = "spring")
interface SocksServiceMapper {

    fun transform(obj: SocksEntity): Socks

    fun transform(obj: Socks): SocksEntity
}