package com.lazarev.socksapi.repository;

import com.lazarev.socksapi.entity.CottonPart;
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
class CottonPartRepositoryTest {

    @Autowired
    private CottonPartRepository repository;

    @BeforeEach
    private void init(){
        repository.deleteAll();

        repository.saveAll(List.of(
                new CottonPart().setCottonPart(40),
                new CottonPart().setCottonPart(50),
                new CottonPart().setCottonPart(20),
                new CottonPart().setCottonPart(5),
                new CottonPart().setCottonPart(15)
        ));
    }

    @Test
    public void canSaveCottonPart_success(){
        CottonPart cottonPart = new CottonPart().setCottonPart(90);

        CottonPart saved = repository.save(cottonPart);

        assertThat(saved.getCottonPart()).isEqualTo(cottonPart.getCottonPart());
    }

    @Test
    public void canSaveCottonPart_unique_constraint_fail(){
        CottonPart cottonPart = new CottonPart().setCottonPart(40);

        assertThatThrownBy(() -> repository.save(cottonPart))
                .isInstanceOf(DataIntegrityViolationException.class);
    }


    @Test
    public void canFindByCottonPart_success(){
        Optional<CottonPart> cottonPartOptional = repository.findByCottonPart(40);
        assertThat(cottonPartOptional.isPresent()).isTrue();
        assertThat(cottonPartOptional.get().getCottonPart()).isEqualTo(40);
    }

    @Test
    public void canFindByCottonPart_fail(){
        Optional<CottonPart> cottonPartOptional = repository.findByCottonPart(100);
        assertThat(cottonPartOptional.isEmpty()).isTrue();
        assertThatThrownBy(cottonPartOptional::get)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("No value present");
    }
}