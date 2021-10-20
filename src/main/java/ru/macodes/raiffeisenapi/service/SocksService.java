package ru.macodes.raiffeisenapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.macodes.raiffeisenapi.data.dto.GetSocksDTO;
import ru.macodes.raiffeisenapi.data.dto.SocksDto;
import ru.macodes.raiffeisenapi.data.entity.Socks;
import ru.macodes.raiffeisenapi.data.repository.SocksRepository;
import ru.macodes.raiffeisenapi.util.exception.SocksException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocksService {

    private final SocksRepository socksRepository;

    public void addSocks(@Valid SocksDto socksDto) {
        Optional<Socks> socksOptional = socksRepository.getSocksByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        socksOptional.ifPresent(socks -> socks.setQuantity(socks.getQuantity() + socksDto.getQuantity()));
        socksRepository.save(socksOptional.orElse(Socks.getSocksFromDTO(socksDto)));
    }

    public void deleteSocks(@Valid SocksDto socksDto) throws SocksException {
        Socks socks = socksRepository.getSocksByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart())
                .orElseThrow(() -> new SocksException(
                        String.format("No socks found with color %s, cotton %s", socksDto.getColor(), socksDto.getCottonPart())
                ));
        if(socksDto.getQuantity() > socks.getQuantity())
            throw new SocksException(String.format("Cannot outcome more socks than available (%d)", socks.getQuantity()));
        socks.setQuantity(socks.getQuantity() - socksDto.getQuantity());
        socksRepository.save(socks);
    }

    public long getSocksCount(@Valid GetSocksDTO getSocksDTO) throws SocksException {
        List<Socks> socks;
        switch (getSocksDTO.getOperation()) {
            case moreThan:
                socks = socksRepository.findAllByCottonPartIsGreaterThan(getSocksDTO.getCottonPart());
                break;
            case lessThan:
                socks = socksRepository.findAllByCottonPartIsLessThan(getSocksDTO.getCottonPart());
                break;
            case equal:
                socks = socksRepository.findAllByCottonPartIs(getSocksDTO.getCottonPart());
                break;
            default:
                throw new SocksException("No such operation available");
        }
        return socks.stream().mapToLong(Socks::getQuantity).sum();
    }

}
