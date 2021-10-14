package com.example.raif_task.services.interfaces;

import com.example.raif_task.Dto.SocksDTO;
import com.example.raif_task.entity.Socks;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SocksServiceInterface {

    ResponseEntity<Socks> save(Socks socks);

    ResponseEntity<Socks> update(Socks socks);

    ResponseEntity<List<Socks>> get(SocksDTO socksDTO);
}
