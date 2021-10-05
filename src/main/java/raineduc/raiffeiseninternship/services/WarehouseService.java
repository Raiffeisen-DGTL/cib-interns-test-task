package raineduc.raiffeiseninternship.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import raineduc.raiffeiseninternship.services.dto.SocksBatch;
import raineduc.raiffeiseninternship.services.dto.SocksRequest;

import javax.validation.Valid;

@Service
@Validated
public class WarehouseService {
    public void registerSocksIncome(@Valid SocksBatch socksBatch) {

    }

    public void registerSocksOutcome(@Valid SocksBatch socksBatch) {

    }

    public int getSocksCount(@Valid SocksRequest socksRequest) {
        return 0;
    }
}
