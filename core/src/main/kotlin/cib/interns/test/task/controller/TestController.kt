package cib.interns.test.task.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/socks/")
class TestController(
) {

    @GetMapping
    fun getAll(): String {
        return "OLOLO"
    }
}