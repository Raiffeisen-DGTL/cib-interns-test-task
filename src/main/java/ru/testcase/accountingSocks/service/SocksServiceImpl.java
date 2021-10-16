package ru.testcase.accountingSocks.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.testcase.accountingSocks.model.Socks;
import ru.testcase.accountingSocks.repository.SocksRepository;

import java.util.List;


@Slf4j
@Service
public class SocksServiceImpl implements SocksService{

    @Autowired
    SocksRepository socksRepository;

//    @Override
//    public Socks getById(Long id) {
//        log.info("IN SocksServiceImpl getById {}", id);
//        return socksRepository.findById(id).orElse(null);
//    }

    @Override
    public void save(Socks socks) {
        log.info("IN SocksServiceImpl save {}", socks);
        socksRepository.save(socks);
    }

//    @Override
//    public void delete(Long id) {
//        log.info("IN SocksServiceImpl delete {}", id);
//        socksRepository.deleteById(id);
//    }

    @Override
    public List<Socks> getAll() {
        log.info("IN SocksServiceImpl getAll");
        return socksRepository.findAll();
    }

//    @Override
//    public List<Socks> getParam(Integer cottonpart) {
//        log.info("IN SocksServiceImpl getParam");
//        System.out.println(socksRepository.findByCottonpart(cottonpart));
//        return socksRepository.findByCottonpart(cottonpart);
//    }

    @Override
    public Socks getByColorAndCottonpart(String color, Integer cottonpart) {
        log.info("IN SocksServiceImpl getByColorAndCottonpart");
        System.out.println("getByColorAndCottonpart" + socksRepository.findByColorAndCottonpart(color, cottonpart));
        return socksRepository.findByColorAndCottonpart(color, cottonpart);
    }


    @Override
    public List<Socks> getByParam(String color, String operation, Integer cottonpart) {
        log.info("IN SocksServiceImpl getByParam");
        if(operation.equals("moreThan")){
            return socksRepository.findByColorAndCottonpartGreaterThan(color, cottonpart);
        } else if (operation.equals("lessThan")) {
            return socksRepository.findByColorAndCottonpartLessThan(color, cottonpart);
        } else if (operation.equals("equal")) {
            return socksRepository.findByColorAndCottonpartEquals(color, cottonpart);
        } else {
            return null;
        }

    }

    @Override
    public void updateSocksQuantity(String color, Integer cottonpart, Integer quantity) {
        Socks socksUpdate = socksRepository.findByColorAndCottonpart(color, cottonpart);
        socksUpdate.setQuantity(quantity);
        socksRepository.save(socksUpdate);
    }

    @Override
    public void reduceSocksQuantity(String color, Integer cottonpart, Integer quantity) {
        Socks socksReduce = socksRepository.findByColorAndCottonpart(color, cottonpart);
        socksReduce.setQuantity(socksReduce.getQuantity()-quantity);
        socksRepository.save(socksReduce);
    }


//    public List<Socks> getTest(String color, int cottonpart) {
//        log.info("IN SocksServiceImpl getTest");
////        System.out.println(socksRepository.findByCottonpart(cottonpart));
//        return socksRepository.findByColorAndCottonpart(color, cottonpart);
//    }
}
