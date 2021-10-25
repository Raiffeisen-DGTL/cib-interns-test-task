package com.example.task.Repository.Rep;

import com.example.task.Other.Socks;

import java.util.List;

public interface SockDAO {

    List<Socks> findAll ();
    void save ( Socks socks );
    void update ( Socks socks );
    void delete ( Socks socks );
}
