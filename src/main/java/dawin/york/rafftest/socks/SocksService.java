package dawin.york.rafftest.socks;

import dawin.york.rafftest.socks.exceptions.InvalidParameterException;
import dawin.york.rafftest.socks.tos.OperationType;
import dawin.york.rafftest.socks.tos.Socks;
import dawin.york.rafftest.socks.tos.SocksId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SocksService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Transactional
    public void incomeSocks(Socks socks) {
        socksRepository.findById(new SocksId(socks.getColor(), socks.getCottonPart()))
                .ifPresentOrElse(socks1 -> {
                    socks1.setQuantity(socks.getQuantity() + socks1.getQuantity());
                    socksRepository.save(socks1);
                }, () -> {
                    socksRepository.save(socks);
                });
    }

    @Transactional
    public void outcomeSocks(Socks socks) {
        socksRepository.findById(new SocksId(socks.getColor(), socks.getCottonPart()))
                .ifPresentOrElse(socks1 -> {
                    int quantity = socks1.getQuantity() - socks.getQuantity();
                    if (quantity < 0)
                        throw new InvalidParameterException("Too much quantity");
                    socks1.setQuantity(quantity);
                    socksRepository.save(socks1);
                }, () -> {
                    throw new InvalidParameterException("Too much quantity");
                });
    }

    public Integer getQuantity(SocksId id, OperationType operation) {
        switch (operation) {
            case LESS_THAN:
                return socksRepository.getLessThan(id.getColor(), id.getCottonPart());
            case MORE_THAN:
                return socksRepository.getMoreThan(id.getColor(), id.getCottonPart());
            default:
            case EQUAL:
                return socksRepository.getEqual(id.getColor(), id.getCottonPart());
        }
    }
}
