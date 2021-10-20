package ru.samusev.api.sercvice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.samusev.api.constant.Operation;
import ru.samusev.api.constant.SocksColor;
import ru.samusev.api.converter.SocksToSocksResponseConverter;
import ru.samusev.api.db.model.Socks;
import ru.samusev.api.db.repository.SocksRepository;
import ru.samusev.api.dto.SocksRequest;
import ru.samusev.api.dto.SocksResponse;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    private final SocksToSocksResponseConverter socksResponseConverter;

    @Override
    public Long getQuantityByCriterion(String color, Integer cottonPart, String operation) {
        if (cottonPart < 0 || cottonPart > 100) {
            throw new IllegalArgumentException(String.format("Bad cottonPart: %d", cottonPart));
        }

        if (Operation.EQUALS.getTitle().equalsIgnoreCase(operation)) {
            return socksRepository.getQuantityByColorAndCottonPart(SocksColor.getByName(color), cottonPart).orElse(0L);
        } else if (Operation.MORE_THAN.getTitle().equalsIgnoreCase(operation)) {
            return socksRepository.getQuantityByColorAndCottonPartMoreThan(SocksColor.getByName(color), cottonPart).orElse(0L);
        } else if (Operation.LESS_THAN.getTitle().equalsIgnoreCase(operation)) {
            return socksRepository.getQuantityByColorAndCottonPartLessThan(SocksColor.getByName(color), cottonPart).orElse(0L);
        } else {
            throw new IllegalArgumentException(String.format("Bad operation: %s", operation));
        }
    }

    @Override
    public SocksResponse income(SocksRequest request) {
        validateRequest(request);

        Socks socks = socksRepository.findAllByColorAndCottonPart(SocksColor.getByName(request.getColor()), request.getCottonPart())
                .orElse(new Socks());

       socks.setColor(SocksColor.getByName(request.getColor()))
            .setCottonPart(request.getCottonPart())
            .setQuantity(socks.getQuantity() + request.getQuantity());

        return socksResponseConverter.convert(
                socksRepository.save(socks)
        );
    }

    @Override
    public SocksResponse outcome(SocksRequest request) {
        validateRequest(request);

        SocksColor socksColor = SocksColor.getByName(request.getColor());
        Socks socks = socksRepository.findAllByColorAndCottonPart(socksColor, request.getCottonPart())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Socks with color %s and cotton part %d not found", request.getColor(), request.getCottonPart())));

        if (socks.getQuantity() < request.getQuantity()) {
            throw new IllegalArgumentException(String.format("Not enough socks! In stock: %d, requested: %d", socks.getQuantity(), request.getQuantity()));
        }

        socks.setColor(socksColor)
            .setCottonPart(request.getCottonPart())
            .setQuantity(socks.getQuantity() - request.getQuantity());

        return socksResponseConverter.convert(
                socksRepository.save(socks)
        );
    }

    private void validateRequest(SocksRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getColor()) || Objects.isNull(request.getCottonPart()) || Objects.isNull(request.getQuantity())) {
            throw new IllegalArgumentException("Bad SocksRequest");
        }

        if (request.getQuantity() < 0) {
            throw new IllegalArgumentException(String.format("Negative quantity: %d", request.getQuantity()));
        }

        if (request.getCottonPart() < 0 || request.getCottonPart() > 100) {
            throw new IllegalArgumentException(String.format("Bad cottonPart: %d", request.getCottonPart()));
        }
    }
}
