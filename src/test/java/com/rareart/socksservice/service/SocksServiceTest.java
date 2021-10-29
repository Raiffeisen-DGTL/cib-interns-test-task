package com.rareart.socksservice.service;

import com.rareart.socksservice.dao.repository.ColorRepository;
import com.rareart.socksservice.dao.repository.SocksRepository;
import com.rareart.socksservice.dto.SocksDto;
import com.rareart.socksservice.dto.request.SocksParamsRequest;
import com.rareart.socksservice.exceptions.InvalidRequestParamsException;
import com.rareart.socksservice.exceptions.NotEnoughItemsException;
import com.rareart.socksservice.exceptions.SocksNotFoundException;
import com.rareart.socksservice.model.Color;
import com.rareart.socksservice.model.Socks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class SocksServiceTest {

    private final ColorRepository colorRepository;

    private final SocksRepository socksRepository;

    private final SocksService socksService;

    private final List<SocksDto> socksList = new ArrayList<>();

    public SocksServiceTest(){
        colorRepository = mock(ColorRepository.class);
        socksRepository = mock(SocksRepository.class);
        socksService = new SocksServiceImpl(colorRepository, socksRepository);
    }

    @BeforeEach
    public void setUpList() {
        //generic tests-isolated random list of DTOs:
        Collections.addAll(socksList,
                new SocksDto("Black", new Byte("55"), 22),
                new SocksDto("Black", new Byte("55"), 20),
                new SocksDto("Black", new Byte("75"), 30),
                new SocksDto("Black", new Byte("75"), 20),
                new SocksDto("Yellow", new Byte("75"), 20),
                new SocksDto("Yellow", new Byte("15"), 10),
                new SocksDto("Yellow", new Byte("15"), 10),
                new SocksDto("Red", new Byte("44"), 90)
        );
    }

    @Test
    public void incomeSocksTest(){
        Color color = new Color(0, socksList.get(0).getColor());
        Socks socks = new Socks(
                0,
                socksList.get(0).getCottonPart(),
                socksList.get(0).getQuantity(),
                color);
        when(colorRepository.findColorByName(color.getName()))
                .thenReturn(color);
        when(socksRepository.findSockByColorAndCottonPart(color, socksList.get(0).getCottonPart()))
                .thenReturn(socks);
        SocksDto outputDTO = socksService.incomeSocks(socksList.get(0));

        verify(colorRepository, times(1)).findColorByName(color.getName());
        verify(socksRepository, times(1)).findSockByColorAndCottonPart(color, socksList.get(0).getCottonPart());
        verify(socksRepository, times(1)).save(socks);

        Assertions.assertEquals(socksList.get(0).getColor(), outputDTO.getColor());
        Assertions.assertEquals(socksList.get(0).getCottonPart(), outputDTO.getCottonPart());
        Assertions.assertEquals(socksList.get(0).getQuantity(), (outputDTO.getQuantity() / 2));
    }

    @Test
    public void outcomeSocksTest() throws NotEnoughItemsException, SocksNotFoundException {
        Color color = new Color(0, socksList.get(0).getColor());
        Socks socks = new Socks(
                0,
                socksList.get(0).getCottonPart(),
                socksList.get(0).getQuantity(),
                color);
        when(colorRepository.findColorByName(color.getName()))
                .thenReturn(color);
        when(socksRepository.findSockByColorAndCottonPart(color, socksList.get(0).getCottonPart()))
                .thenReturn(socks);
        SocksDto socksDto = socksService.outcomeSocks(socksList.get(0));

        verify(colorRepository, times(1)).findColorByName(color.getName());
        verify(socksRepository, times(1)).findSockByColorAndCottonPart(color, socksList.get(0).getCottonPart());
        verify(socksRepository, times(1)).save(socks);

        //zero value check
        socksDto.setQuantity(socksDto.getQuantity() + socksList.get(0).getQuantity());
        Assertions.assertEquals(socksList.get(0), socksDto);
    }

    @Test
    public void getSocksCountByParamsTest() throws InvalidRequestParamsException {
        Color color = new Color(0, socksList.get(7).getColor());

        List<Socks> socksByParams = new ArrayList<>();
        socksByParams.add(new Socks(0, new Byte("65"), 10, color));
        socksByParams.add(new Socks(0, new Byte("70"), 5, color));
        socksByParams.add(new Socks(0, new Byte("80"), 5, color));

        SocksParamsRequest params =
                new SocksParamsRequest("Red", "moreThan", new Byte("60"));

        when(colorRepository.findColorByName(color.getName()))
                .thenReturn(color);
        when(socksRepository.findSocksByColorAndCottonPartGreaterThan(color, params.getCottonPart()))
                .thenReturn(socksByParams);

        long result = socksService.getSocksCountByParams(params);

        verify(colorRepository, times(1))
                .findColorByName(color.getName());
        verify(socksRepository, times(1))
                .findSocksByColorAndCottonPartGreaterThan(color, params.getCottonPart());

        Assertions.assertEquals(20, result);
    }

    @Test
    public void outcomeSocksNotFoundException() {
        Assertions.assertThrows(SocksNotFoundException.class, () ->
                socksService.outcomeSocks(socksList.get(1)));
    }

}
