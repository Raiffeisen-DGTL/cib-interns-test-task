package com.lazarev.socksapi.service;

import com.lazarev.socksapi.dto.SockDto;
import com.lazarev.socksapi.entity.CottonPart;
import com.lazarev.socksapi.entity.Sock;
import com.lazarev.socksapi.entity.SockColor;
import com.lazarev.socksapi.exception.NotEnoughSocksOnStorehouseException;
import com.lazarev.socksapi.exception.OperationNotFoundException;
import com.lazarev.socksapi.exception.SockNotFoundException;
import com.lazarev.socksapi.repository.CottonPartRepository;
import com.lazarev.socksapi.repository.SockColorRepository;
import com.lazarev.socksapi.repository.SockRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class SockServiceTest {

    @MockBean
    private SockRepository sockRepository;

    @MockBean
    private CottonPartRepository cottonPartRepository;

    @MockBean
    private SockColorRepository sockColorRepository;

    @Captor
    private ArgumentCaptor<Sock> captor;

    @Autowired
    private SockService sockService;

    @Test
    public void canAddSocks_with_sockColor_and_cottonPart_saveCalled() {
        Sock sock = new Sock()
                .setId(1L)
                .setQuantity(40)
                .setSockColor(new SockColor().setColor("red"))
                .setCottonPart(new CottonPart().setCottonPart(40));

        SockColor sockColor = new SockColor()
                .setId(1L)
                .setColor("red");

        CottonPart cottonPart = new CottonPart()
                .setId(1L)
                .setCottonPart(40);

        SockDto dto = new SockDto("red", 40, 40);

        given(sockColorRepository.save(any(SockColor.class)))
                .willReturn(sockColor);

        given(cottonPartRepository.save(any(CottonPart.class)))
                .willReturn(cottonPart);

        given(sockRepository.save(any(Sock.class)))
                .willReturn(sock);

        Sock capturedSock = sockService.addSocks(dto);
        assertThat(capturedSock).isEqualTo(sock);
    }

    @Test
    public void canAddSocks_with_sockColor_and_cottonPart_saveNotCalled() {
        Sock sock = new Sock()
                .setId(1L)
                .setQuantity(40)
                .setSockColor(new SockColor().setColor("red"))
                .setCottonPart(new CottonPart().setCottonPart(40));

        SockColor sockColor = new SockColor()
                .setId(1L)
                .setColor("red");

        CottonPart cottonPart = new CottonPart()
                .setId(1L)
                .setCottonPart(40);

        SockDto dto = new SockDto("red", 40, 40);

        given(sockColorRepository.findByColor("red"))
                .willReturn(Optional.of(sockColor));

        given(cottonPartRepository.findByCottonPart(40))
                .willReturn(Optional.of(cottonPart));

        given(sockRepository.save(any(Sock.class)))
                .willReturn(sock);

        Sock capturedSock = sockService.addSocks(dto);
        assertThat(capturedSock).isEqualTo(sock);
    }

    @Test
    public void canAddSocks_withSave() {
        Sock sock = new Sock()
                .setId(1L)
                .setQuantity(40)
                .setSockColor(new SockColor().setColor("red"))
                .setCottonPart(new CottonPart().setCottonPart(40));

        SockColor sockColor = new SockColor()
                .setId(1L)
                .setColor("red");

        CottonPart cottonPart = new CottonPart()
                .setId(1L)
                .setCottonPart(40);

        SockDto dto = new SockDto("red", 40, 40);

        given(sockColorRepository.findByColor("red"))
                .willReturn(Optional.of(sockColor));

        given(cottonPartRepository.findByCottonPart(40))
                .willReturn(Optional.of(cottonPart));

        given(sockRepository.findBySockColorAndCottonPart(any(SockColor.class), any(CottonPart.class)))
                .willReturn(Optional.empty());

        given(sockRepository.save(any(Sock.class)))
                .willReturn(sock);

        Sock capturedSock = sockService.addSocks(dto);
        assertThat(capturedSock).isEqualTo(sock);
    }


    @Test
    public void canAddSocks_withUpdate() {
        Sock sock = new Sock()
                .setId(1L)
                .setQuantity(40)
                .setSockColor(new SockColor().setColor("red"))
                .setCottonPart(new CottonPart().setCottonPart(40));

        Sock expected = new Sock()
                .setId(1L)
                .setQuantity(80)
                .setSockColor(new SockColor().setColor("red"))
                .setCottonPart(new CottonPart().setCottonPart(40));

        SockColor sockColor = new SockColor()
                .setId(1L)
                .setColor("red");

        CottonPart cottonPart = new CottonPart()
                .setId(1L)
                .setCottonPart(40);

        SockDto dto = new SockDto("red", 40, 40);

        given(sockColorRepository.findByColor("red"))
                .willReturn(Optional.of(sockColor));

        given(cottonPartRepository.findByCottonPart(40))
                .willReturn(Optional.of(cottonPart));

        given(sockRepository.findBySockColorAndCottonPart(any(SockColor.class), any(CottonPart.class)))
                .willReturn(Optional.of(sock));

        given(sockRepository.save(any(Sock.class)))
                .willReturn(expected);

        Sock capturedSock = sockService.addSocks(dto);
        assertThat(capturedSock).isEqualTo(expected);
    }

    @Test
    public void canSubSocks_with_no_sockColor() {
        CottonPart cottonPart = new CottonPart()
                .setId(1L)
                .setCottonPart(40);

        SockDto dto = new SockDto("red", 40, 40);

        given(sockColorRepository.findByColor("red"))
                .willReturn(Optional.empty());

        given(cottonPartRepository.findByCottonPart(40))
                .willReturn(Optional.of(cottonPart));

        assertThatThrownBy(() -> sockService.subSocks(dto))
                .hasMessage(String.format("Sock with color = '%s' not found", dto.getColor()))
                .isInstanceOf(SockNotFoundException.class);
    }


    @Test
    public void canSubSocks_with_no_cottonPart() {
        SockColor sockColor = new SockColor()
                .setId(1L)
                .setColor("red");

        SockDto dto = new SockDto("red", 40, 40);

        given(sockColorRepository.findByColor("red"))
                .willReturn(Optional.of(sockColor));

        given(cottonPartRepository.findByCottonPart(40))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> sockService.subSocks(dto))
                .hasMessage(String.format("Sock with cotton_part = '%d' not found", dto.getCottonPart()))
                .isInstanceOf(SockNotFoundException.class);
    }


    @Test
    public void canSubSocks_with_no_sock() {
        SockColor sockColor = new SockColor()
                .setId(1L)
                .setColor("red");

        CottonPart cottonPart = new CottonPart()
                .setId(1L)
                .setCottonPart(40);

        SockDto dto = new SockDto("red", 40, 40);

        given(sockColorRepository.findByColor("red"))
                .willReturn(Optional.of(sockColor));

        given(cottonPartRepository.findByCottonPart(40))
                .willReturn(Optional.of(cottonPart));

        given(sockRepository.findBySockColorAndCottonPart(any(SockColor.class), any(CottonPart.class)))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> sockService.subSocks(dto))
                .hasMessage(String.format(
                        "Sock with color = '%s' and cotton_part = '%d' not found",
                        dto.getColor(), dto.getCottonPart()))
                .isInstanceOf(SockNotFoundException.class);
    }


    @Test
    public void canSubSocks_success() {
        Sock sock = new Sock()
                .setId(1L)
                .setQuantity(40)
                .setSockColor(new SockColor().setColor("red"))
                .setCottonPart(new CottonPart().setCottonPart(40));

        Sock expected = new Sock()
                .setId(1L)
                .setQuantity(25)
                .setSockColor(new SockColor().setColor("red"))
                .setCottonPart(new CottonPart().setCottonPart(40));

        SockColor sockColor = new SockColor()
                .setId(1L)
                .setColor("red");

        CottonPart cottonPart = new CottonPart()
                .setId(1L)
                .setCottonPart(40);

        SockDto dto = new SockDto("red", 40, 15);

        given(sockColorRepository.findByColor("red"))
                .willReturn(Optional.of(sockColor));

        given(cottonPartRepository.findByCottonPart(40))
                .willReturn(Optional.of(cottonPart));

        given(sockRepository.findBySockColorAndCottonPart(any(SockColor.class), any(CottonPart.class)))
                .willReturn(Optional.of(sock));

        given(sockRepository.save(any(Sock.class)))
                .willReturn(expected);

        Sock capturedSock = sockService.subSocks(dto);
        assertThat(capturedSock).isEqualTo(expected);
    }

    @Test
    public void canSubSocks_not_enough_socks() {
        Sock sock = new Sock()
                .setId(1L)
                .setQuantity(40)
                .setSockColor(new SockColor().setColor("red"))
                .setCottonPart(new CottonPart().setCottonPart(40));

        Sock expected = new Sock()
                .setId(1L)
                .setQuantity(25)
                .setSockColor(new SockColor().setColor("red"))
                .setCottonPart(new CottonPart().setCottonPart(40));

        SockColor sockColor = new SockColor()
                .setId(1L)
                .setColor("red");

        CottonPart cottonPart = new CottonPart()
                .setId(1L)
                .setCottonPart(40);

        SockDto dto = new SockDto("red", 40, 55);

        given(sockColorRepository.findByColor("red"))
                .willReturn(Optional.of(sockColor));

        given(cottonPartRepository.findByCottonPart(40))
                .willReturn(Optional.of(cottonPart));

        given(sockRepository.findBySockColorAndCottonPart(any(SockColor.class), any(CottonPart.class)))
                .willReturn(Optional.of(sock));

        assertThatThrownBy(() -> sockService.subSocks(dto))
                .hasMessage(String.format(
                        "Not enough socks on storehouse: required=%d, in stock=%d",
                        dto.getQuantity(), sock.getQuantity()))
                .isInstanceOf(NotEnoughSocksOnStorehouseException.class);
    }

    @Test
    public void canGetSumMatchingSocksQuantity_success() {
        String operation = "moreThan";
        String color = "red";
        Integer cottonPart = 30;

        given(sockRepository.sumBySockColorAndCottonPartMoreThan(eq(color), eq(cottonPart)))
                .willReturn(100);

        Integer res = sockService.countRequestMatchingSocks(color, operation, cottonPart);
        assertThat(res).isEqualTo(100);
    }

    @Test
    public void canGetSumMatchingSocksQuantity_incorrectOperation() {
        String operation = "moreThanEqual";
        String color = "red";
        Integer cottonPart = 30;

        assertThatThrownBy(() -> sockService.countRequestMatchingSocks(color, operation, cottonPart))
                .hasMessage(String.format(
                        "Operation '%s' is not allowed. " +
                        "You can use only the following: 'lessThan', 'moreThan', 'equal'", operation))
                .isInstanceOf(OperationNotFoundException.class);
    }

}