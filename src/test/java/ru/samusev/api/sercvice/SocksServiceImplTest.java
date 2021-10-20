package ru.samusev.api.sercvice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.samusev.api.AbstractTest;
import ru.samusev.api.constant.Operation;
import ru.samusev.api.constant.SocksColor;
import ru.samusev.api.dto.SocksRequest;
import ru.samusev.api.dto.SocksResponse;

import static org.junit.jupiter.api.Assertions.*;

@Sql("classpath:sql/SocksServiceImpl.sql")
class SocksServiceImplTest extends AbstractTest {
    @Autowired
    private SocksService socksService;

    @Test
    void getQuantityByCriterion_equals() {
        Long actual = socksService.getQuantityByCriterion(SocksColor.RED.name(), 99, Operation.EQUALS.getTitle());

        assertEquals(50, actual);
    }

    @Test
    void getQuantityByCriterion_lessThan() {
        Long actual = socksService.getQuantityByCriterion(SocksColor.RED.name(), 60, Operation.LESS_THAN.getTitle());

        assertEquals(40, actual);
    }

    @Test
    void getQuantityByCriterion_moreThan() {
        Long actual = socksService.getQuantityByCriterion(SocksColor.RED.name(), 40, Operation.MORE_THAN.getTitle());

        assertEquals(90, actual);
    }

    @Test
    void getQuantityByCriterion_withBadOperation() {
        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.getQuantityByCriterion(SocksColor.RED.name(), 40, "BadOperation")
        );
    }

    @Test
    void getQuantityByCriterion_withBadColor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.getQuantityByCriterion("BadColor", 40, Operation.MORE_THAN.name())
        );
    }

    @Test
    void getQuantityByCriterion_withNegativeCottonPart() {
        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.getQuantityByCriterion(SocksColor.RED.name(), -40, Operation.MORE_THAN.name())
        );
    }

    @Test
    void getQuantityByCriterion_withBigCottonPart() {
        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.getQuantityByCriterion(SocksColor.RED.name(), 89040, Operation.MORE_THAN.name())
        );
    }

    @Test
    void income_withRequestIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.income(null)
        );
    }

    @Test
    void income_withEmptyRequest() {
        SocksRequest request = new SocksRequest();

        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.income(request)
        );
    }

    @Test
    void income_requestWithoutColor() {
        SocksRequest request = new SocksRequest()
                .setQuantity(12L)
                .setCottonPart(44);

        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.income(request)
        );
    }

    @Test
    void income_requestWithoutQuantity() {
        SocksRequest request = new SocksRequest()
                .setColor(SocksColor.RED.name())
                .setCottonPart(44);

        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.income(request)
        );
    }

    @Test
    void income_requestWithoutCottonPart() {
        SocksRequest request = new SocksRequest()
                .setColor(SocksColor.RED.name())
                .setQuantity(144L);

        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.income(request)
        );
    }

    @Test
    void income_requestWithBigCottonPart() {
        SocksRequest request = new SocksRequest()
                .setColor(SocksColor.RED.name())
                .setCottonPart(123)
                .setQuantity(144L);

        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.income(request)
        );
    }

    @Test
    void income_requestWithNegativeCottonPart() {
        SocksRequest request = new SocksRequest()
                .setColor(SocksColor.RED.name())
                .setCottonPart(123)
                .setQuantity(144L);

        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.income(request)
        );
    }

    @Test
    void income_requestWithBadColor() {
        SocksRequest request = new SocksRequest()
                .setColor("BADColor")
                .setCottonPart(55)
                .setQuantity(144L);

        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.income(request)
        );
    }

    @Test
    void income_requestWithBadQuantity() {
        SocksRequest request = new SocksRequest()
                .setColor(SocksColor.RED.name())
                .setCottonPart(123)
                .setQuantity(-144L);

        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.income(request)
        );
    }

    @Test
    void outcome() {
        SocksRequest request = new SocksRequest()
                .setColor(SocksColor.RED.name())
                .setQuantity(10L)
                .setCottonPart(50);

        SocksResponse actual = socksService.outcome(request);

        assertEquals(30L, actual.getQuantity());
        assertEquals(SocksColor.RED.name(), actual.getColor());
        assertEquals(50, actual.getCottonPart());
    }

    @Test
    void outcome_notEnough() {
        SocksRequest request = new SocksRequest()
                .setColor(SocksColor.RED.name())
                .setQuantity(140L)
                .setCottonPart(50);

        assertThrows(
                IllegalArgumentException.class,
                () -> socksService.outcome(request)
        );
    }
}
