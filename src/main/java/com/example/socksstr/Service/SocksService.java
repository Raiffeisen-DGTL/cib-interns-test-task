package com.example.socksstr.Service;

import com.example.socksstr.Model.Socks;

import java.util.List;

public interface SocksService {

    void save (Socks socks);

    void delete(Long id, Socks socks);

    List<Socks> getAll();

    Socks getById(Long id);
}

