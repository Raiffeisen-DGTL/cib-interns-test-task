package cib.interns.test.task.service

import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class SocksValidationDto(
    val color: String,

    @field:Min(0)
    @field:Max(100)
    val cottonPart: Int,

    @field:Min(1)
    val quantity: Long,
)
