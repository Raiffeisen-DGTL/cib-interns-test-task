package raineduc.raiffeiseninternship.services;

import net.bytebuddy.matcher.EqualityMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import raineduc.raiffeiseninternship.entities.SocksPair;
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
        ArrayList<SocksPair> socksPairs = new ArrayList<>();

        for (int i = 0; i < socksBatch.getQuantity(); i++) {
            socksPairs.add(new SocksPair(socksBatch.getColor(), socksBatch.getCottonPart()));
        }

        socksPairRepository.saveAll(socksPairs);
    }

    public void registerSocksOutcome(@Valid SocksBatch socksBatch) {
        List<SocksPair> socksPairs = socksPairRepository.findAllByColorAndCottonPart(
                socksBatch.getColor(), socksBatch.getCottonPart());

        if (socksPairs.size() < socksBatch.getQuantity())
            throw new NotEnoughSocksException();

        List<SocksPair> socksToBeRemoved = socksPairs.subList(0, socksBatch.getQuantity());
        socksPairRepository.deleteAll(socksToBeRemoved);
    }

    public int getSocksCount(@Valid SocksRequest socksRequest) {
        String op = socksRequest.getOperation();
        String color = socksRequest.getColor();
        byte cottonPart = socksRequest.getCottonPart();
        if (op.equals(EQUAL_OP)) {
            return socksPairRepository.countByColorAndCottonPartEquals(color, cottonPart);
        }
        if (op.equals(MORE_THAN_OP)) {
            return socksPairRepository.countByColorAndCottonPartGreaterThan(color, cottonPart);
        }
        if (op.equals(LESS_THAN_OP)) {
            return socksPairRepository.countByColorAndCottonPartLessThan(color, cottonPart);
        }
        return 0;
    }
}
