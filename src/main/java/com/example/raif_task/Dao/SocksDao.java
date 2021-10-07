package com.example.raif_task.Dao;

import com.example.raif_task.Dao.interfaces.SocksDaoInterface;
import com.example.raif_task.Dao.repositories.SocksRepo;
import com.example.raif_task.entity.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocksDao implements SocksDaoInterface {
    @Autowired
    private SocksRepo socksRepo;

    public void save(Socks socks) {
        socksRepo.save(socks);
    }

    public List<Socks> getSocks(Socks socks) {
        List<Socks> list = socksRepo
                .getAllByColorIsAndCottonPartIs(socks.getColor(),
                        socks.getCottonPart());

        return list.isEmpty() ? null : list;
    }

    public List<Socks> getSocksByColor(String color) {
        List<Socks> list = socksRepo.getAllByColorIs(color);

        return list.isEmpty() ? null : list;
    }
}
