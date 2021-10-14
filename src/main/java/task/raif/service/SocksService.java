package task.raif.service;

import org.springframework.stereotype.Service;
import task.raif.exception.NotEnoughSocksException;
import task.raif.model.SocksFilter;
import task.raif.model.SocksLot;
import task.raif.model.SocksStorage;
import task.raif.repository.SocksMySQLRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocksService {

    private final SocksMySQLRepository repository;

    public SocksService(SocksMySQLRepository repository) {
        this.repository = repository;
    }

    public List<SocksLot> getAll() {
        return repository.findAll().stream()
                         .map(o -> new SocksLot(o.getColor(), o.getCottonPart(), o.getQuantity()))
                         .collect(Collectors.toList());
    }

    public long get(SocksFilter socksFilter) {
        switch (socksFilter.getOperation()) {
            case MORE_THAN:
                return repository
                        .getQuantityByColorAndCottonPartMoreThan(socksFilter.getColor(), socksFilter.getCottonPart())
                        .orElse(0);
            case LESS_THAN:
                return repository
                        .getQuantityByColorAndCottonPartLessThan(socksFilter.getColor(), socksFilter.getCottonPart())
                        .orElse(0);
            case EQUAL:
                var socksStorage = repository
                        .getByColorAndCottonPart(socksFilter.getColor(), socksFilter.getCottonPart());
                return socksStorage.map(SocksStorage::getQuantity).orElse(0L);
            default:
                throw new IllegalStateException("Unknown Enum type" + socksFilter.getOperation().name());
        }
    }

    public SocksLot put(SocksLot lot) {
        var socksInStorage = repository.getByColorAndCottonPart(lot.getColor(), lot.getCottonPart());

        if (socksInStorage.isPresent()) {
            var socks = socksInStorage.get();
            socks.setQuantity(socks.getQuantity() + lot.getQuantity());
            repository.save(socks);
            lot.setQuantity(socks.getQuantity());
        } else {
            repository.save(new SocksStorage(lot.getColor(), lot.getCottonPart(), lot.getQuantity()));
        }
        return lot;

    }

    public SocksLot take(SocksLot lot) {
        var socksInStorage = repository.getByColorAndCottonPart(lot.getColor(), lot.getCottonPart());
        if (!socksInStorage.isPresent()) {
            throw new NotEnoughSocksException("Socks with " + lot.getColor() + " color and " + lot.getCottonPart()
                                                      + " Cotton Part not found");
        }
        var socks = socksInStorage.get();

        if (socks.getQuantity() > lot.getQuantity()) {
            socks.setQuantity(socks.getQuantity() - lot.getQuantity());
            repository.save(socks);
            return lot;
        } else if (socks.getQuantity() == lot.getQuantity()) {
            repository.deleteById(socks.getId());
            return lot;
        } else {
            throw new NotEnoughSocksException("Not enough socks with " + lot.getColor() + " color and "
                                                      + lot.getCottonPart() + " Cotton Part not found");
        }

    }

}


