package ru.danilarassokhin.raiffeisensocks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.danilarassokhin.raiffeisensocks.dto.HomePageDto;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;

@RestController
public class HomeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<HomePageDto> displayHomePage() {
        HomePageDto homePageDto = new HomePageDto();
        homePageDto.setAuthor("Rassokhin Danila");
        homePageDto.setRepo("https://github.com/CrissNamon/raiffeisen-socks-test");
        return new ResponseDto<>("Это тестовое задание для стажировки Raiffeisen Java Bootcamp", homePageDto);
    }

}
