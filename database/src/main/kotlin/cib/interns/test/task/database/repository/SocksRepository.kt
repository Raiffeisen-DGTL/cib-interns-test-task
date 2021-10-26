package cib.interns.test.task.database.repository

import cib.interns.test.task.database.entity.Socks
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SocksRepository : JpaRepository<Socks, Long> {

    fun findSocksByColorAndCottonPart(color: String, cottonPart: Int): Socks?


    @Query(value = """
        SELECT COALESCE(SUM(quantity), 0) as quantity from Socks
    """)
    fun sumWithoutParams(): Int

    @Query(value = "?1", nativeQuery = true)
    fun sumWithParams(sql: String): Long
}