package com.example.raif_task.Dao.interfaces;

import com.example.raif_task.entity.Socks;

import java.util.List;

public interface SocksDaoInterface {

    void save(Socks socks);

    List<Socks> getSocks(Socks socks);

    List<Socks> getSocksByColor(String color);
}
