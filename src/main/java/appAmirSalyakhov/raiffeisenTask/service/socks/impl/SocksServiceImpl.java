package appAmirSalyakhov.raiffeisenTask.service.socks.impl;

import appAmirSalyakhov.raiffeisenTask.model.Response;
import appAmirSalyakhov.raiffeisenTask.model.Socks;
import appAmirSalyakhov.raiffeisenTask.repository.SocksRepository;
import appAmirSalyakhov.raiffeisenTask.service.socks.SocksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SocksServiceImpl implements SocksService {

    @Autowired
    private SocksRepository socksRepository;

    public Integer getSockByColorAndCottonPart(String color, String operation, int cottonPart) {
        List<Socks> socksResponse;
        switch (operation) {
            case "moreThan":
                socksResponse = socksRepository.findSocksByColorAndCottonPartIsGreaterThan(color, cottonPart);
                break;
            case "lessThan":
                socksResponse = socksRepository.findSocksByColorAndCottonPartIsLessThan(color, cottonPart);
                break;
            case "equal":
                socksResponse = socksRepository.findSocksByColorAndCottonPartEquals(color, cottonPart);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
        int socksValue = 0;
        for (Socks socks : socksResponse) {
            socksValue += socks.getQuantity();
        }
        return socksValue;
    }

    public ResponseEntity<Response> saveOrUpdateSocksQuantityFormWarehouse(Socks incomeSocks) {
        Socks selectForUpdateSocks = socksRepository.selectSocksByColorAndCottonPartEquals(incomeSocks.getColor(), incomeSocks.getCottonPart());
        Response responseBody = new Response();
        if (incomeSocks.getQuantity() > 0) {
            if (selectForUpdateSocks != null) {
                selectForUpdateSocks.setQuantity(selectForUpdateSocks.getQuantity() + incomeSocks.getQuantity());
                socksRepository.saveAndFlush(selectForUpdateSocks);
            } else socksRepository.saveAndFlush(incomeSocks);
            responseBody.setMessage("Socks successful added in Warehouse");
            new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else
            responseBody.setMessage("Please check quantity");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Response> subtractSocksFormWarehouse(Socks outcomeSocks) {
        Socks selectForUpdateSocks = socksRepository.selectSocksByColorAndCottonPartEquals(outcomeSocks.getColor(), outcomeSocks.getCottonPart());
        Response responseBody = new Response();
        if (selectForUpdateSocks != null) {
            if (selectForUpdateSocks.getQuantity() < outcomeSocks.getQuantity()) {
                responseBody.setMessage("Quantity must be less or equal than, " + selectForUpdateSocks.getQuantity()
                        + " units in Warehouse is available");
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            } else
                selectForUpdateSocks.setQuantity(selectForUpdateSocks.getQuantity() - outcomeSocks.getQuantity());
            socksRepository.saveAndFlush(selectForUpdateSocks);
            responseBody.setMessage("Socks successful subtract from Warehouse");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
        responseBody.setMessage("Cannot subtract socks");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
