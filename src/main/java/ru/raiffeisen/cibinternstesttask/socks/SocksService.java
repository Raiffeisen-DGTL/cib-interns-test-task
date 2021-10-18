package ru.raiffeisen.cibinternstesttask.socks;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.raiffeisen.cibinternstesttask.operations.Operation;
import ru.raiffeisen.cibinternstesttask.socks.dto.QuantityDto;
import ru.raiffeisen.cibinternstesttask.socks.dto.SocksDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocksService {

    private final SocksRepository socksRepository;
    private final ColorRepository colorRepository;

    @Transactional
    public void income(SocksDto dto) {
        var color = colorRepository.save(Color.of(dto.color()));
        var socks = socksRepository
                .findSocksByColorAndCottonPart(color, dto.cottonPart())
                .orElseGet(() -> socksRepository.save(
                                Socks.of(color, dto.cottonPart(), dto.quantity())));
        increaseQty(dto, socks);
    }

    @Transactional
    public void outcome(SocksDto dto) {
        var color = getColorOrThrow(dto.color());
        var socks = socksRepository
                .findSocksByColorAndCottonPart(color, dto.cottonPart())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        String.format(
                                "Socks with color: %s, cottonPart: %d not found",
                                color.getName(), dto.cottonPart())));
        decreaseQty(dto, socks);
    }

    public QuantityDto getSocksQuantity(String colorName, String opName, Short cottonPart) {
        checkCottonPart(cottonPart);
        var socksList = getOperation(opName)
                .getSocks(getColorOrThrow(colorName), cottonPart);
        var quantity = countSocks(socksList);
        return new QuantityDto(quantity);
    }

    private Color getColorOrThrow(String colorName) {
        return colorRepository
                .findByName(colorName)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "No color found with name " + colorName));
    }

    private void increaseQty(SocksDto dto, Socks socks) {
        socks.setQuantity(socks.getQuantity() + dto.quantity());
    }

    private void decreaseQty(SocksDto dto, Socks socks) {
        var quantity = socks.getQuantity() - dto.quantity();
        if (quantity < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantity must be less or equal " + socks.getQuantity());
        }
        socks.setQuantity(quantity);
    }

    private Operation getOperation(String opName) {
        return Operation.findOperationOrThrow(
                Operation.getOperationList(socksRepository), opName);
    }

    private void checkCottonPart(Short cottonPart) {
        if (cottonPart < 0 || cottonPart > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "cottonPart must be greater than 0 and less or equal 100");
        }
    }

    private int countSocks(List<Socks> socksList) {
        var result = 0;
        for (var item : socksList) {
            result += item.getQuantity();
        }
        return result;
    }
}
