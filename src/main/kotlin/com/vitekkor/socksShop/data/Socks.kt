package com.vitekkor.socksShop.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException

@Serializable
data class Socks(val color: String, val cottonPart: Int, val quantity: Long) {
    init {
        if (cottonPart !in 0..100 || quantity < 0) throw IllegalArgumentException()
    }
}
