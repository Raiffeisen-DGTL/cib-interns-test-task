package ru.ikuzin.DGTLTask.Socks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ikuzin.DGTLTask.Socks.model.SocksColor;
import ru.ikuzin.DGTLTask.Socks.model.SocksCottonPart;
import ru.ikuzin.DGTLTask.Socks.model.SocksStock;

import java.util.List;


@Repository
public interface SocksRepository extends CrudRepository<SocksStock, Integer> {
    SocksStock findByColorAndPart(SocksColor color, SocksCottonPart part);
    List<SocksStock> findAllByColorAndPartGreaterThan(SocksColor color, SocksCottonPart part);
    List<SocksStock> findAllByColorAndPartEquals(SocksColor color, SocksCottonPart part);
    List<SocksStock> findAllByColorAndPartLessThan(SocksColor color, SocksCottonPart part);
}
