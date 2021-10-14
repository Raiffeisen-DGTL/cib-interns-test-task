package com.example.socksspring.controller;

import com.example.socksspring.Compare;
import com.example.socksspring.Socks;
import com.example.socksspring.exception.BadRequestException;
import com.example.socksspring.exception.ResourceNotFoundException;
import com.example.socksspring.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SocksController {

    private final SocksService service;
    private final Logger logger;

    public SocksController(SocksService service) {
        this.service = service;
        this.logger = LoggerFactory.getLogger(SocksController.class);
    }

    @Operation(summary = "Add socks to database or add quantity " +
            "to the already existing entry ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the socks",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Request exception or bad format",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content),
    })
    @PostMapping(value = "api/socks/income")
    @ResponseBody
    public String addSocks(@RequestBody Socks socks) {
        if(socks.getCottonPart() < 0 || socks.getCottonPart() > 100 || socks.getQuantity() <= 0) {
            throw new BadRequestException("cotton part or quantity out of bounds");
        }

        service.addSocks(socks);
        logger.info("added socks with these parameters : " + socks);
        return "success adding socks";
    }

    @Operation(summary = "Remove quantity from existing entry of socks by color + cotton part " +
            "or return 404 if none socks are found")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "api/socks/outcome")
    @ResponseBody
    public String removeSocks(@RequestBody Socks socks) {
        if(socks.getCottonPart() < 0 || socks.getCottonPart() > 100 || socks.getQuantity() <= 0) {
            throw new BadRequestException("cotton part or quantity out of bounds");
        }

        List<Socks> removedSocks = service.removeSocks(socks);
        if (removedSocks.size() == 0) {
            logger.warn("none socks were found with these parameters: " + socks);
            throw new ResourceNotFoundException("No socks were found with given parameters");
        } else {
            logger.info("removed socks with these parameters : " + socks);
            return "success removing socks";
        }
    }

    @Operation(summary = "Get the number of socks with cotton containing " +
            "equals amount / less than / more than the specified amount ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the socks",
                    content = {@Content(mediaType = "string of int, describing the amount of socks left",
                            schema = @Schema(implementation = String.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Request exception or bad format",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Socks not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content),

    })
    @GetMapping("api/socks")
    Integer getSocks(@Parameter(description = "color of socks to be found")
                            @RequestParam(value = "color") final String color,
                            @Parameter(description = "cotton part contained in socks compare operation")
                            @RequestParam(value = "operation") final Compare operation,
                            @Parameter(description = "cotton part amount to be compared to")
                            @RequestParam(value = "cottonPart") final Integer cottonPart) {
        if(cottonPart < 0 || cottonPart > 100 ) {
            throw new BadRequestException("cotton part out of bounds");
        }

        List<Socks> queriedSocks = service.getSocks(color, operation, cottonPart);
        if (queriedSocks.size() == 0) {
            logger.info("no socks were found with these parameters : color - " + color + ", operation - " + operation + ", cotton part " + cottonPart);
            throw new ResourceNotFoundException("No socks were found with given parameters");
        } else {
            int sum = 0;
            for (Socks socks : queriedSocks) {
                sum += socks.getQuantity();
            }
            logger.info("gotten socks with these parameters : color - " + color + ", operation - " + operation + ", cotton part " + cottonPart);
            return sum;
        }
    }


}
