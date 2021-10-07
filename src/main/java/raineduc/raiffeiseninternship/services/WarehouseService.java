package raineduc.raiffeiseninternship.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import raineduc.raiffeiseninternship.entities.Socks;
import raineduc.raiffeiseninternship.services.dto.SocksBatch;
import raineduc.raiffeiseninternship.services.dto.SocksRequest;
import raineduc.raiffeiseninternship.services.exceptions.NotEnoughSocksException;
import raineduc.raiffeiseninternship.services.interfaces.SocksPairRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class WarehouseService {
    private static final String EQUAL_OP = "equal";
    private static final String MORE_THAN_OP = "moreThan";
    private static final String LESS_THAN_OP = "lessThan";

    private SocksPairRepository socksPairRepository;

    @Autowired
    public WarehouseService(SocksPairRepository socksPairRepository) {
        this.socksPairRepository = socksPairRepository;
    }

    public void registerSocksIncome(@Valid SocksBatch socksBatch) {
        Socks socks = socksPairRepository.findSocksByColorAndCottonPart(socksBatch.getColor(), socksBatch.getCottonPart());

        if (socks != null) {
            socks.setQuantity(socks.getQuantity() + socksBatch.getQuantity());
        } else {
            socks = new Socks(socksBatch.getColor(), socksBatch.getCottonPart(), socksBatch.getQuantity());
        }
        socksPairRepository.save(socks);
    }

    public void registerSocksOutcome(@Valid SocksBatch socksBatch) {
        Socks socks = socksPairRepository.findSocksByColorAndCottonPart(socksBatch.getColor(), socksBatch.getCottonPart());

        if (socks == null || socks.getQuantity() < socksBatch.getQuantity())
            throw new NotEnoughSocksException();

        socks.setQuantity(socks.getQuantity() - socksBatch.getQuantity());
        socksPairRepository.save(socks);
    }

    public int getSocksCount(@Valid SocksRequest socksRequest) {
        String op = socksRequest.getOperation();
        String color = socksRequest.getColor();
        byte cottonPart = socksRequest.getCottonPart();
        List<Socks> socks = new ArrayList<>();
        if (op.equals(EQUAL_OP)) {
            socks = socksPairRepository.findAllByColorAndCottonPartEquals(color, cottonPart);
        }
        if (op.equals(MORE_THAN_OP)) {
            socks = socksPairRepository.findAllByColorAndCottonPartGreaterThan(color, cottonPart);
        }
        if (op.equals(LESS_THAN_OP)) {
            socks = socksPairRepository.findAllByColorAndCottonPartLessThan(color, cottonPart);
        }
        return socks.stream().reduce(0, (x, y) -> x + y.getQuantity(), Integer::sum);
    }
}
