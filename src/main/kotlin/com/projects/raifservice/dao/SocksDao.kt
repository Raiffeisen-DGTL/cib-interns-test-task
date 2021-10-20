package com.projects.raifservice.dao

import com.projects.raifservice.model.Socks
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SocksDao : CrudRepository<Socks, Long> {

    fun findSocksByColorAndCottonPart (color: String, cottonPart: Int): Socks?

    fun findSocksByColorAndCottonPartIsGreaterThan (color: String, cottonPart: Int): List<Socks>

    fun findSocksByColorAndCottonPartIsLessThan (color: String, cottonPart: Int): List<Socks>
}