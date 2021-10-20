package com.projects.raifservice.controller

import com.projects.raifservice.model.Socks
import com.projects.raifservice.service.SocksService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

@RestController
@Validated
@RequestMapping("/api/socks")
class SocksController(private val socksService: SocksService) {

    @PostMapping("/income")
    fun incomeSocks(@Validated @RequestBody socks: Socks) = socksService.addSocks(socks)

    @PostMapping("/outcome")
    fun outcomeSocks(@Validated @RequestBody socks: Socks) = socksService.removeSocks(socks)

    @GetMapping
    fun getQuantitySocks(
        @RequestParam @NotNull color: String,
        @RequestParam @NotNull operation: String,
        @RequestParam @NotNull cottonPart: Int
    ): String =
        socksService.findQuantitySocks(color, operation, cottonPart)
}

