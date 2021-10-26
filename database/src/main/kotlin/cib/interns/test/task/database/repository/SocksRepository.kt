package cib.interns.test.task.database.repository

import cib.interns.test.task.database.entity.Socks
import org.springframework.data.jpa.repository.JpaRepository

interface SocksRepository : JpaRepository<Socks, Long> {

    fun findSocksByColorAndCottonPart(color: String, cottonPart: Int): Socks?
}