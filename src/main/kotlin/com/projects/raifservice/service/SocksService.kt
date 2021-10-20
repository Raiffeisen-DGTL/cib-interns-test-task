package com.projects.raifservice.service

import com.projects.raifservice.model.Socks

interface SocksService {

    fun addSocks(socks: Socks)

    fun removeSocks(socks: Socks)

    fun findQuantitySocks(color: String, operation: String, cottonPart: Int): String
}