package services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.jdbc.BadSqlGrammarException;
import raiffeisen.models.socks.Socks;
import raiffeisen.repository.SocksRepository;
import raiffeisen.services.SocksService;
import raiffeisen.utils.Operator;

import java.util.Optional;

/**
 * @author voroningg
 */
public class SocksServiceTests {
    private static final Socks NEW_ITEM = new Socks("red", 10, 10);
    private static final Socks OLD_ITEM_WITH_0_QUANTITY = new Socks("black", 20, 0);
    private static final Socks OLD_ITEM_WITH_10_QUANTITY = new Socks("black", 20, 10);
    private static final Socks OLD_ITEM_WITH_100_QUANTITY = new Socks("black", 20, 100);

    @InjectMocks
    private SocksService socksService;

    @Mock
    private SocksRepository socksRepository;

    @Mock
    private JdbcAggregateOperations aggregateOperations;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        Mockito.doNothing().doThrow(BadSqlGrammarException.class)
                .when(socksRepository).createTable();
        Mockito.when(socksRepository.findSocks(NEW_ITEM.getColor(), NEW_ITEM.getCottonPart()))
                .thenReturn(Optional.empty());
        Mockito.when(socksRepository.findSocks(
                        OLD_ITEM_WITH_10_QUANTITY.getColor(), OLD_ITEM_WITH_10_QUANTITY.getCottonPart()))
                .thenReturn(Optional.of(OLD_ITEM_WITH_10_QUANTITY));
    }

    @Test
    public void createTableShouldWorkCorrectOnFirstExecute() {
        Assertions.assertDoesNotThrow(() -> socksService.createTable());
    }

    @Test
    public void createTableShouldThrowExceptionOnSecondExecution() {
        Assertions.assertDoesNotThrow(() -> socksService.createTable());
        Assertions.assertThrows(BadSqlGrammarException.class, () -> socksService.createTable());
    }

    @Test
    public void incomeShouldExecuteInsertOnNewItem() throws Exception {
        socksService.income(NEW_ITEM);
        Mockito.verify(aggregateOperations).insert(NEW_ITEM);
        Mockito.verify(socksRepository, Mockito.times(0))
                .save(NEW_ITEM);
    }

    @Test
    public void incomeShouldExecuteSaveOnAlreadyHavenItem() throws Exception {
        socksService.income(OLD_ITEM_WITH_10_QUANTITY);
        Mockito.verify(aggregateOperations, Mockito.times(0))
                .insert(OLD_ITEM_WITH_10_QUANTITY);
        Mockito.verify(socksRepository).save(OLD_ITEM_WITH_10_QUANTITY);
    }

    @Test
    public void outcomeShouldThrowExceptionWhenQuantityGreaterThanExisting() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> socksService.outcome(OLD_ITEM_WITH_100_QUANTITY));
    }

    @Test
    public void outcomeShouldExecuteSaveWhenQuantityLessOrEqualThanExisting() throws Exception {
        socksService.outcome(OLD_ITEM_WITH_10_QUANTITY);
        Mockito.verify(socksRepository).save(OLD_ITEM_WITH_0_QUANTITY);
    }

    @Test
    public void countFilteredShouldExecuteMatchOperation() throws Exception {
        socksService.countFiltered("red", Operator.MoreThan, 0);
        Mockito.verify(socksRepository).countMoreThan("red", 0);
        socksService.countFiltered("red", Operator.LessThan, 0);
        Mockito.verify(socksRepository).countLessThan("red", 0);
        socksService.countFiltered("red", Operator.Equal, 0);
        Mockito.verify(socksRepository).countEquals("red", 0);
    }
}
