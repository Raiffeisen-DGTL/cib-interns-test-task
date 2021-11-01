package ru.ikuzin.DGTLTask.Socks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ikuzin.DGTLTask.Socks.dto.SocksInfo;
import ru.ikuzin.DGTLTask.Socks.model.SocksColor;
import ru.ikuzin.DGTLTask.Socks.model.SocksCottonPart;
import ru.ikuzin.DGTLTask.Socks.model.SocksStock;
import ru.ikuzin.DGTLTask.Socks.repository.SocksColorRepository;
import ru.ikuzin.DGTLTask.Socks.repository.SocksCottonPartRepository;
import ru.ikuzin.DGTLTask.Socks.repository.SocksRepository;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class SocksService {
    private final SocksRepository SocksRepo;
    private final SocksColorRepository ColorRepo;
    private final SocksCottonPartRepository CottonPartRepo;

    public SocksColor findColorOrCreate(String color) {
        return ColorRepo.findByColor(color).orElseGet(() -> ColorRepo.save(new SocksColor(color)));
    }

    public SocksCottonPart findCottonPartOrCreate(Integer part) {
        return CottonPartRepo.findByPartEquals(part).orElseGet(() -> CottonPartRepo.save(new SocksCottonPart(part)));
    }

    public void takeIncome(SocksInfo incomeInfo) {
        SocksColor color = findColorOrCreate(incomeInfo.getColor());
        SocksCottonPart cottonPart = findCottonPartOrCreate(incomeInfo.getCottonPart());
        SocksStock stock = SocksRepo.findByColorAndPart(color, cottonPart);

        if (stock != null) {
            stock.setCount(stock.getCount() + incomeInfo.getCount());
            SocksRepo.save(stock);
        } else {
            SocksRepo.save(new SocksStock(
                    incomeInfo.getCount(),
                    color,
                    cottonPart
            ));
        }
    }

    public void takeOutcome(SocksInfo outcomeInfo) {
        SocksColor color = findColorOrCreate(outcomeInfo.getColor());
        SocksCottonPart cottonPart = findCottonPartOrCreate(outcomeInfo.getCottonPart());
        SocksStock stock = SocksRepo.findByColorAndPart(color, cottonPart);

        if (stock != null && stock.getCount() > outcomeInfo.getCount()) {
            stock.setCount(stock.getCount() - outcomeInfo.getCount());
            SocksRepo.save(stock);
        } else {
            throw new IllegalArgumentException("try to make less non-existent");
        }
    }

    public List<SocksStock> socksByArgs(SocksColor color, SocksCottonPart cottonPart, String operation) {
        return switch (operation) {
            case "lessThan" -> SocksRepo.findAllByColorAndPartLessThan(color, cottonPart);
            case "moreThan" -> SocksRepo.findAllByColorAndPartGreaterThan(color, cottonPart);
            case "equal" -> SocksRepo.findAllByColorAndPartEquals(color, cottonPart);
            default -> throw new IllegalArgumentException("non-existent method");
        };
    }

    public SocksInfo getStockInfo(String color, Integer cottonPart, String operation) {
        List<SocksStock> socks = socksByArgs(
                ColorRepo.findByColor(color).orElse(null),
                CottonPartRepo.findByPartEquals(cottonPart).orElse(null),
                operation
        );
        return new SocksInfo(color, socks.stream().mapToInt(SocksStock::getCount).sum(), cottonPart);
    }
}
