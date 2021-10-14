package ru.raiffeisen.shopwarehouse.service;

import org.springframework.stereotype.Service;
import ru.raiffeisen.shopwarehouse.entity.Socks;
import ru.raiffeisen.shopwarehouse.repository.SocksRepository;

import java.util.List;

@Service
public class SocksService {
    private final SocksRepository socksRepository;

    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public boolean create(Socks socks) {
        socksRepository.save(socks);
        if(!socksRepository.getById(socks.getSocksId()).equals(null)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Socks> getAll() {
        return socksRepository.findAll();
    }

    public Socks get(Socks socks) {
        Socks localSocks = socksRepository.getSocksByCottonPartAndColor(socks.getCottonPart(), socks.getColor());
        return localSocks;
    }

    public boolean update(Socks socks, long id) {
        Socks localSocks = socksRepository.getById(id);
        if(!localSocks.equals(null)) {
            socks.setSocksId(id);
            socksRepository.save(socks);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(long id) {
        socksRepository.deleteById(id);
        if(socksRepository.getById(id).equals(null)) {
            return true;
        } else {
            return false;
        }
    }
}
