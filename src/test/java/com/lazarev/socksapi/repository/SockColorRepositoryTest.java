package com.lazarev.socksapi.repository;

import com.lazarev.socksapi.entity.SockColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class SockColorRepositoryTest {
    @Autowired
    private SockColorRepository repository;

    @BeforeEach
    private void init(){
        repository.deleteAll();

        repository.saveAll(List.of(
                new SockColor().setColor("red"),
                new SockColor().setColor("blue"),
                new SockColor().setColor("green"),
                new SockColor().setColor("black"),
                new SockColor().setColor("white")
        ));
    }

    @Test
    public void canSaveCottonPart_success(){
        SockColor sockColor = new SockColor().setColor("yellow");

        SockColor saved = repository.save(sockColor);

        assertThat(saved.getColor()).isEqualTo(sockColor.getColor());
    }

    @Test
    public void canSaveCottonPart_unique_constraint_fail(){
        SockColor sockColor = new SockColor().setColor("red");

        assertThatThrownBy(() -> repository.save(sockColor))
                .isInstanceOf(DataIntegrityViolationException.class);
    }


    @Test
    public void canFindByCottonPart_success(){
        Optional<SockColor> sockColorOptional = repository.findByColor("red");
        assertThat(sockColorOptional.isPresent()).isTrue();
        assertThat(sockColorOptional.get().getColor()).isEqualTo("red");
    }

    @Test
    public void canFindByCottonPart_fail(){
        Optional<SockColor> sockColorOptional = repository.findByColor("some_color");
        assertThat(sockColorOptional.isEmpty()).isTrue();
        assertThatThrownBy(sockColorOptional::get)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("No value present");
    }
}