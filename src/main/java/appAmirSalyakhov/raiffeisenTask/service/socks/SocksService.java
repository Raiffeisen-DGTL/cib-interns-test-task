package appAmirSalyakhov.raiffeisenTask.service.socks;

import appAmirSalyakhov.raiffeisenTask.model.Response;
import appAmirSalyakhov.raiffeisenTask.model.Socks;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SocksService {

    Integer getSockByColorAndCottonPart(String color, String operation, int cottonPart);

    ResponseEntity<Response> saveOrUpdateSocksQuantityFormWarehouse(Socks socks);

    ResponseEntity<Response> subtractSocksFormWarehouse(Socks socks);
}
