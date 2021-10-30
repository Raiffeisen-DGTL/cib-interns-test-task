package com.lazarev.socksapi.repository;

import com.lazarev.socksapi.entity.CottonPart;
import com.lazarev.socksapi.entity.Sock;
import com.lazarev.socksapi.entity.SockColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SockRepositoryTest {

    @Autowired
    private SockRepository sockRepository;

    @Autowired
    private SockColorRepository colorRepository;

    @Autowired
    private CottonPartRepository cottonRepository;


    @BeforeEach
    private void init(){
        List<CottonPart> cottons =  List.of(
                new CottonPart().setCottonPart(50),
                new CottonPart().setCottonPart(80),
                new CottonPart().setCottonPart(20),
                new CottonPart().setCottonPart(5),
                new CottonPart().setCottonPart(35)
        );

        List<SockColor> colors =  List.of(
                new SockColor().setColor("red"),
                new SockColor().setColor("blue"),
                new SockColor().setColor("green"),
                new SockColor().setColor("black"),
                new SockColor().setColor("white")
        );

        List<Sock> socks = List.of(
                new Sock().setQuantity(10).setSockColor(colors.get(0)).setCottonPart(cottons.get(0)),
                new Sock().setQuantity(15).setSockColor(colors.get(1)).setCottonPart(cottons.get(1)),
                new Sock().setQuantity(20).setSockColor(colors.get(2)).setCottonPart(cottons.get(2)),
                new Sock().setQuantity(30).setSockColor(colors.get(3)).setCottonPart(cottons.get(3)),
                new Sock().setQuantity(20).setSockColor(colors.get(4)).setCottonPart(cottons.get(4)),
                new Sock().setQuantity(12).setSockColor(colors.get(0)).setCottonPart(cottons.get(4)),
                new Sock().setQuantity(20).setSockColor(colors.get(1)).setCottonPart(cottons.get(3)),
                new Sock().setQuantity(40).setSockColor(colors.get(2)).setCottonPart(cottons.get(3)),
                new Sock().setQuantity(30).setSockColor(colors.get(3)).setCottonPart(cottons.get(1)),
                new Sock().setQuantity(80).setSockColor(colors.get(4)).setCottonPart(cottons.get(0)),
                new Sock().setQuantity(70).setSockColor(colors.get(0)).setCottonPart(cottons.get(1)),
                new Sock().setQuantity(10).setSockColor(colors.get(1)).setCottonPart(cottons.get(2))
        );


        sockRepository.saveAll(socks);
    }

    @AfterEach
    public void clear(){
        sockRepository.deleteAll();
        colorRepository.deleteAll();
        cottonRepository.deleteAll();
    }

    @Test
    public void canFindBySockColorAndCottonPart_success(){
        Optional<CottonPart> cottonPart =  cottonRepository.findByCottonPart(50);
        Optional<SockColor> sockColor = colorRepository.findByColor("red");

        assertThatNoException().isThrownBy(cottonPart::get);
        assertThatNoException().isThrownBy(sockColor::get);

        Optional<Sock> sockOptional = sockRepository
                .findBySockColorAndCottonPart(sockColor.get(), cottonPart.get());
        assertThat(sockOptional.isPresent()).isTrue();
        assertThat(sockOptional.get().getQuantity()).isEqualTo(10);
        assertThat(sockOptional.get().getSockColor().getColor()).isEqualTo("red");
        assertThat(sockOptional.get().getCottonPart().getCottonPart()).isEqualTo(50);
    }

    @Test
    public void canFindBySockColorAndCottonPart_cottonPart_not_found(){
        Optional<CottonPart> cottonPart =  cottonRepository.findByCottonPart(100);

        assertThat(cottonPart.isEmpty()).isTrue();
        assertThatThrownBy(cottonPart::get)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("No value present");
    }

    @Test
    public void canFindBySockColorAndCottonPart_sockColor_not_found(){
        Optional<SockColor> sockColor = colorRepository.findByColor("some_color");

        assertThat(sockColor.isEmpty()).isTrue();
        assertThatThrownBy(sockColor::get)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("No value present");
    }

    @Test
    public void canSaveSock_success(){
        Sock sock = new Sock()
                .setQuantity(40)
                .setSockColor(new SockColor().setColor("pink"))
                .setCottonPart(new CottonPart().setCottonPart(99));

        Sock saved = sockRepository.save(sock);
        assertThat(saved.getQuantity()).isEqualTo(40);
        assertThat(saved.getSockColor().getColor()).isEqualTo("pink");
        assertThat(saved.getCottonPart().getCottonPart()).isEqualTo(99);
    }

    @Test
    public void canSaveSock_success_unique_constraint_fail(){
        Sock sock = new Sock()
                .setQuantity(40)
                .setSockColor(new SockColor().setColor("red"))
                .setCottonPart(new CottonPart().setCottonPart(50));

        assertThatThrownBy(() -> sockRepository.save(sock))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void canSumMatchingSocks_operation_moreThan_1(){
        Integer sum = sockRepository.sumBySockColorAndCottonPartMoreThan("red", 10);
        assertThat(sum).isEqualTo(92);
    }

    @Test
    public void canSumMatchingSocks_operation_moreThan_2(){
        Integer sum = sockRepository.sumBySockColorAndCottonPartMoreThan("red", 90);
        assertThat(sum).isNull();
    }

    @Test
    public void canSumMatchingSocks_operation_lessThan_1(){
        Integer sum = sockRepository.sumBySockColorAndCottonPartLessThan("white", 50);
        assertThat(sum).isEqualTo(20);
    }

    @Test
    public void canSumMatchingSocks_operation_lessThan_2(){
        Integer sum = sockRepository.sumBySockColorAndCottonPartLessThan("white", 10);
        assertThat(sum).isNull();
    }

    @Test
    public void canSumMatchingSocks_operation_equals_1(){
        Integer sum = sockRepository.sumBySockColorAndCottonPartEquals("black", 5);
        assertThat(sum).isEqualTo(30);
    }

    @Test
    public void canSumMatchingSocks_operation_equals_2(){
        Integer sum = sockRepository.sumBySockColorAndCottonPartEquals("black", 10);
        assertThat(sum).isNull();
    }

    @Test
    public void canSumMatchingSocks_color_not_found(){
        Integer sum = sockRepository.sumBySockColorAndCottonPartEquals("some_color", 10);
        assertThat(sum).isNull();
    }

    @Test
    public void canSumMatchingSocks_cottonPart_not_found(){
        Integer sum = sockRepository.sumBySockColorAndCottonPartEquals("red", 100);
        assertThat(sum).isNull();
    }

}