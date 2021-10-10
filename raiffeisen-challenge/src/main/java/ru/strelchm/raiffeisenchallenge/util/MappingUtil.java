package ru.strelchm.raiffeisenchallenge.util;

import org.springframework.stereotype.Service;
import ru.strelchm.raiffeisenchallenge.domain.Sock;
import ru.strelchm.raiffeisenchallenge.dto.SockDto;

@Service
public class MappingUtil {
    public SockDto mapToSockDto(Sock sock) {
        return SockMapper.INSTANCE.toDto(sock);
    }
}
