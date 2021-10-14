package com.rf.accountingforsocks.service;

import com.rf.accountingforsocks.dto.SocksDto;
import com.rf.accountingforsocks.entity.Color;
import com.rf.accountingforsocks.entity.Socks;
import com.rf.accountingforsocks.repository.SocksRepository;
import com.rf.accountingforsocks.util.Convert;
import com.rf.accountingforsocks.util.SocksOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SocksService {
    @Autowired
    private final ColorService colorService;

    @Autowired
    private final SocksRepository socksRepository;

    public SocksService(ColorService colorService, SocksRepository socksRepository) {
        this.colorService = colorService;
        this.socksRepository = socksRepository;
    }

    public Integer findQuantityOfSocksByColorOrOperationAndCottonPart(String colorName,
                                                                      SocksOperation socksOperation,
                                                                      Integer cottonPart) {
        Color color = colorService.findColorByName(colorName);
        List<Socks> socks = null;
        if (socksOperation == SocksOperation.moreThan) {
            socks = socksRepository.findSocksByColorAndCottonPartGreaterThan(color, cottonPart);
        }
        if (socksOperation == SocksOperation.lessThan) {
            socks = socksRepository.findSocksByColorAndCottonPartLessThan(color, cottonPart);
        }
        if (socksOperation == SocksOperation.equal) {
            socks = socksRepository.findSocksByColorAndCottonPartEquals(color, cottonPart);
        }
        if (socks.isEmpty()) return 0;
        return socks.stream().map(Convert::socksToDto).mapToInt(SocksDto::getQuantity).sum();

    }


    public void findByColorAndOutcome(SocksDto socksDto) {
        Color color = colorService.findColorByName(socksDto.getColor());
        Optional<Socks> socksOpt = socksRepository.findSocksByColorAndCottonPart(color, socksDto.getCottonPart());

        socksOpt.orElseThrow().outcome(socksDto.getQuantity());
        socksRepository.save(socksOpt.get());
    }

    public void income(SocksDto socksDto) {
        Color color = colorService.findColorByName(socksDto.getColor());
        Optional<Socks> socksOpt = socksRepository.findSocksByColorAndCottonPart(color, socksDto.getCottonPart());
        if (socksOpt.isEmpty()) {
            Socks socks = Convert.dtoToSocks(socksDto);
            socks.setColor(color);
            socksRepository.save(socks);
        } else {
            socksOpt.get().income(socksDto.getQuantity());
            socksRepository.save(socksOpt.get());
        }
    }
}
