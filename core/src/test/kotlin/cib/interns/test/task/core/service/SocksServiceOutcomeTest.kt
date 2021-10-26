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
import javax.validation.ConstraintViolationException

class SocksServiceOutcomeTest : AbstractDbTest() {

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
    fun `remove exist socks`() {
        socksRepository.save(SocksEntity().apply {
            color = "RED"
            cottonPart = 10
            quantity = 1
        })

        socksService.removeSocks(Socks("RED", 10, 1))

        val result = socksRepository.findAll().first()

        Assertions.assertEquals("RED", result.color)
        Assertions.assertEquals(10, result.cottonPart)
        Assertions.assertEquals(0, result.quantity)
    }

    @Test
    fun `remove empty socks`() {

        Assertions.assertThrows(ConstraintViolationException::class.java) {
            socksService.removeSocks(Socks("RED", 10, 1))
        }

    }

    @Test
    fun `remove more than in stock`() {

        socksRepository.save(SocksEntity().apply {
            color = "RED"
            cottonPart = 10
            quantity = 1
        })

        Assertions.assertThrows(ConstraintViolationException::class.java) {
            socksService.removeSocks(Socks("RED", 10, 5))
        }

    }

}