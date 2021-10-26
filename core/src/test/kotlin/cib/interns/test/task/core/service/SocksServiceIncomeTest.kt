package cib.interns.test.task.core.service

import cib.interns.test.task.core.AbstractDbTest
import cib.interns.test.task.database.repository.SocksRepository
import cib.interns.test.task.database.entity.Socks as SocksEntity
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.jdbc.JdbcTestUtils

class SocksServiceIncomeTest : AbstractDbTest() {

    @Autowired
    lateinit var socksService: SocksService

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    lateinit var socksRepository: SocksRepository

    @BeforeEach
    fun before() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "socks")
    }

    @AfterEach
    fun after() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "socks")
    }

    @Test
    fun `add new socks`() {
        socksService.addSocks(Socks("RED", 10, 1))

        val result = socksRepository.findAll().first()

        Assertions.assertEquals("RED", result.color)
        Assertions.assertEquals(10, result.cottonPart)
        Assertions.assertEquals(1, result.quantity)
    }

    @Test
    fun `add exists socks`() {
        socksRepository.save(SocksEntity().apply {
            color = "RED"
            cottonPart = 10
            quantity = 1
        })

        socksService.addSocks(Socks("RED", 10, 1))
        val result = socksRepository.findAll().first()
        Assertions.assertEquals("RED", result.color)
        Assertions.assertEquals(10, result.cottonPart)
        Assertions.assertEquals(2, result.quantity)
    }

    @Test
    fun `add other socks color`() {
        socksRepository.save(SocksEntity().apply {
            color = "RED"
            cottonPart = 10
            quantity = 1
        })

        socksService.addSocks(Socks("blue", 10, 1))
        val result = socksRepository.findAll().first { it.color == "blue" }

        Assertions.assertEquals("blue", result.color)
        Assertions.assertEquals(10, result.cottonPart)
        Assertions.assertEquals(1, result.quantity)
    }

    @Test
    fun `add other socks cotton`() {
        socksRepository.save(SocksEntity().apply {
            color = "RED"
            cottonPart = 10
            quantity = 1
        })

        socksService.addSocks(Socks("RED", 9, 1))
        val result = socksRepository.findSocksByColorAndCottonPart("RED", 9)!!
        Assertions.assertEquals("RED", result.color)
        Assertions.assertEquals(9, result.cottonPart)
        Assertions.assertEquals(1, result.quantity)
    }
}