package com.rareart.socksservice.service;

import com.rareart.socksservice.dao.repository.ColorRepository;
import com.rareart.socksservice.dao.repository.SocksRepository;
import com.rareart.socksservice.dto.SocksDto;
import com.rareart.socksservice.dto.request.SocksParamsRequest;
import com.rareart.socksservice.exceptions.ColorNotFoundException;
import com.rareart.socksservice.exceptions.InvalidRequestParamsException;
import com.rareart.socksservice.exceptions.NotEnoughItemsException;
import com.rareart.socksservice.exceptions.SocksNotFoundException;
import com.rareart.socksservice.model.Color;
import com.rareart.socksservice.model.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class SocksServiceImpl implements SocksService {

    private final ColorRepository colorRepository;
    private final SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(ColorRepository colorRepository, SocksRepository socksRepository) {
        this.colorRepository = colorRepository;
        this.socksRepository = socksRepository;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public SocksDto incomeSocks(@Valid SocksDto socksDto) {
        Color color = colorRepository.findColorByName(socksDto.getColor());
        Socks socks;
        //quick socks search by color instance check:
        if (color == null){
            Color newColor = new Color(0, socksDto.getColor());
            colorRepository.save(newColor);
            colorRepository.flush();
            socks = new Socks(0, socksDto.getCottonPart(), socksDto.getQuantity(), newColor);
            socksRepository.save(socks);
            return new SocksDto(newColor.getName(), socks.getCottonPart(), socks.getQuantity());
        }
        socks = socksRepository.findSockByColorAndCottonPart(color, socksDto.getCottonPart());
        if (socks != null){
            socks.setQuantity(socks.getQuantity() + socksDto.getQuantity());
            socksRepository.save(socks);
            return new SocksDto(color.getName(), socks.getCottonPart(), socks.getQuantity());
        }
        socks = new Socks(0, socksDto.getCottonPart(), socksDto.getQuantity(), color);
        socksRepository.save(socks);
        return new SocksDto(color.getName(), socks.getCottonPart(), socks.getQuantity());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public SocksDto outcomeSocks(@Valid SocksDto socksDto) throws SocksNotFoundException, NotEnoughItemsException {
        Color color = colorRepository.findColorByName(socksDto.getColor());
        //fast validation by color instance check:
        if (color == null){
            ColorNotFoundException exception = new ColorNotFoundException("No socks found in this color");
            throw new SocksNotFoundException("Socks not found, caused by nested exception", exception);
        }
        Socks socks = socksRepository.findSockByColorAndCottonPart(color, socksDto.getCottonPart());
        if (socks == null){
            throw new SocksNotFoundException("Socks with the specified attributes were not found");
        }
        if(socksDto.getQuantity() <= socks.getQuantity()){
            socks.setQuantity(socks.getQuantity() - socksDto.getQuantity());
            socksRepository.save(socks);
            return new SocksDto(socks.getColor().getName(), socks.getCottonPart(), socks.getQuantity());
        } else {
            throw new NotEnoughItemsException("Еhe quantity of the requested item is not enough in stock");
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public long getSocksCountByParams(@Valid SocksParamsRequest params) throws InvalidRequestParamsException {
        //case 1: request has color and cottonPart params:
        if (params.getColor() != null
                && !params.getColor().equals("")
                && params.getCottonPart() != null){
            Color color = colorRepository.findColorByName(params.getColor());
            if (color == null){
                return 0;
            }
            if(params.getOperation() == null){
                throw new InvalidRequestParamsException("there is no operation operator in params");
            }
            return countSocks(findSocksByColorAndCottonPart(color, params));
        }

        //case 2: the request only has color param:
        if (params.getColor() != null){
            Color color = colorRepository.findColorByName(params.getColor());
            if (color == null){
                return 0;
            }
            return countSocks(socksRepository.findSocksByColor(color));
        }

        //case 3: the request only has cottonPart param:
        if (params.getCottonPart() != null){
            if(params.getOperation() == null){
                throw new InvalidRequestParamsException("there is no operation operator in params");
            }
            return countSocks(findSocksByCottonPart(params));
        }

        //case 4: the request has no parameters:
        return countSocks(socksRepository.findAll());
    }

    private List<Socks> findSocksByColorAndCottonPart(Color color, SocksParamsRequest params) throws InvalidRequestParamsException {
        List<Socks> socksList;
        switch (params.getOperation()){
            case "moreThan":
                socksList = socksRepository.findSocksByColorAndCottonPartGreaterThan(
                        color, params.getCottonPart()
                );
                break;
            case "lessThan":
                socksList = socksRepository.findSocksByColorAndCottonPartLessThan(
                        color, params.getCottonPart()
                );
                break;
            case "equal":
                socksList = socksRepository.findSocksByColorAndCottonPartEquals(
                        color, params.getCottonPart()
                );
                break;
            default:
                throw new InvalidRequestParamsException("invalid operation operator");
        }
        return socksList;
    }

    private List<Socks> findSocksByCottonPart(SocksParamsRequest params) throws InvalidRequestParamsException {
        List<Socks> socksList;
        switch (params.getOperation()){
            case "moreThan":
                socksList = socksRepository.findSocksByCottonPartGreaterThan(params.getCottonPart());
                break;
            case "lessThan":
                socksList = socksRepository.findSocksByCottonPartLessThan(params.getCottonPart());
                break;
            case "equal":
                socksList = socksRepository.findSocksByCottonPartEquals(params.getCottonPart());
                break;
            default:
                throw new InvalidRequestParamsException("invalid operation operator");
        }
        return socksList;
    }

    private long countSocks(List<Socks> socksList){
        return socksList.stream().mapToInt(Socks::getQuantity).sum();
    }
}
