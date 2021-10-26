package cib.interns.test.task.api

import io.swagger.v3.oas.annotations.media.Schema

data class SocksIncomeRequest(
    val color: String,
    val cottonPart: Int,
    val quantity: Long,
)
