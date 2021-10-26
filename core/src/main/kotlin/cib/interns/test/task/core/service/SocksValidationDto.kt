package cib.interns.test.task.core.service

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class SocksValidationDto(
    val color: String,

    @field:Min(0)
    @field:Max(100)
    val cottonPart: Int,

    @field:Min(1)
    val quantity: Long,
)

@ColorAvailable
@QuantityAvailable
data class SocksOutValidationDto(

    val color: String,

    @field:Min(0)
    @field:Max(100)
    val cottonPart: Int,

    @field:Min(1)
    val quantity: Long,
)

data class SocksFindValidationDto (

    @field:NotNull
    val color: String?,

    @field:CorrectOperation
    @field:NotNull
    val operation: String?,

    @field:Min(0)
    @field:Max(100)
    @field:NotNull
    val cottonPart: Int?,
)
