package ru.samusev.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.remoting.RemoteTimeoutException;
import org.springframework.stereotype.Component;
import ru.samusev.api.db.model.Socks;
import ru.samusev.api.dto.SocksResponse;

import java.util.Optional;

@Component
public class SocksToSocksResponseConverter implements Converter<Socks, SocksResponse> {
    @Override
    public SocksResponse convert(Socks request) {
        return Optional.ofNullable(request)
                .map(socks -> new SocksResponse()
                        .setColor(socks.getColor().name())
                        .setCottonPart(socks.getCottonPart())
                        .setQuantity(Optional.ofNullable(socks.getQuantity()).orElse(0L))
                ).orElseThrow(() -> new RemoteTimeoutException("Socks is null"));
    }
}
