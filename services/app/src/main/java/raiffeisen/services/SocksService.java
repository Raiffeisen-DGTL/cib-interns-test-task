package raiffeisen.services;

import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Service;
import raiffeisen.models.socks.Socks;
import raiffeisen.repository.SocksRepository;
import raiffeisen.utils.Operator;
import raiffeisen.utils.RetryPolicies;
import raiffeisen.utils.Transactional;

import java.util.Optional;


/**
 * @author voroningg
 */
@Service
public class SocksService {
    private final Object SOCKS_LOCK_OBJECT = new Object();
    private final JdbcAggregateOperations aggregateOperations;
    private final SocksRepository socksRepository;

    public SocksService(JdbcAggregateOperations aggregateOperations, SocksRepository socksRepository) {
        this.aggregateOperations = aggregateOperations;
        this.socksRepository = socksRepository;
    }

    public void createTable() throws Exception {
        RetryPolicies.run(() -> Transactional.run(SOCKS_LOCK_OBJECT, socksRepository::createTable));
    }

    public void income(Socks socks) throws Exception {
        RetryPolicies.run(() ->
                Transactional.run(SOCKS_LOCK_OBJECT, () -> {
                    Optional<Socks> socksInStock = socksRepository
                            .findSocks(socks.getColor(), socks.getCottonPart());
                    if (socksInStock.isEmpty()) {
                        aggregateOperations.insert(socks);
                    } else {
                        int socksCountInStock = socksInStock.get().getQuantity();
                        socks.setQuantity(socksCountInStock + socks.getQuantity());
                        socksRepository.save(socks);
                    }
                }));
    }

    public void outcome(Socks socks) throws Exception {
        RetryPolicies.run(() ->
                Transactional.run(SOCKS_LOCK_OBJECT, () -> {
                    Optional<Socks> socksInStock = socksRepository
                            .findSocks(socks.getColor(), socks.getCottonPart());
                    int socksCountInStock = socksInStock.map(Socks::getQuantity).orElse(0);
                    if (socksCountInStock < socks.getQuantity()) {
                        throw new IllegalArgumentException();
                    }
                    socks.setQuantity(socksCountInStock - socks.getQuantity());
                    socksRepository.save(socks);
                }));
    }

    public Integer countFiltered(String color, Operator operator, int cottonPart) throws Exception {
        return RetryPolicies.get(() ->
                Transactional.get(SOCKS_LOCK_OBJECT,
                        () -> {
                            switch (operator) {
                                case MoreThan:
                                    return socksRepository.countMoreThan(color, cottonPart).orElse(0);
                                case LessThan:
                                    return socksRepository.countLessThan(color, cottonPart).orElse(0);
                                case Equal:
                                    return socksRepository.countEquals(color, cottonPart).orElse(0);
                                default:
                                    throw new IllegalStateException("Unexpected value: " + operator);
                            }
                        }));
    }
}
