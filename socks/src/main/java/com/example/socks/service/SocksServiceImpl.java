package com.example.socks.service;
import com.example.socks.model.Socks;
import com.example.socks.repository.SocksRepository;
import com.example.socks.utils.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SocksServiceImpl implements SocksService {

    @Autowired
    private SocksRepository socksRepository;

    @Override
    public void addSocks(Socks socks) {
        Optional<Long> id = socksRepository.getIdByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if(id.isEmpty()) {
            socksRepository.save(socks);
        } else {
            Optional<Socks> socksDB = socksRepository.findById(id.get());
            Socks test = socksDB.get();
            test.setQuantity(test.getQuantity() + socks.getQuantity());
            socksRepository.save(test);
        }
    }

    @Override
    public void deleteSocks(Socks socks) throws Exception {
        Optional<Long> id = socksRepository.getIdByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (id.isEmpty()) {
            throw new Exception();
        } else {
            Optional<Socks> socksDB = socksRepository.findById(id.get());
            Socks test = socksDB.get();
            if (socksDB.get().getQuantity() - socks.getQuantity() < 0) {
                throw new Exception();
            }
            test.setQuantity(test.getQuantity() - socks.getQuantity());
            socksRepository.save(test);
        }
    }

    @Override
    public int getSocks(String color, Operation operation, int cottonPart) {
        Optional<Integer> result = Optional.empty();
        switch (operation) {
            case EQUAL:
                result = socksRepository.getSocksEqual(color, cottonPart);
                break;
            case MORE_THAN:
                result = socksRepository.getSocksMoreThan(color, cottonPart);
                break;
            case LESS_THAN:
                result = socksRepository.getSocksLessThan(color, cottonPart);
                break;
        }
        return result.isEmpty() ? 0 : result.get();
    }
}
