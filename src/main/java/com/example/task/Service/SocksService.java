package com.example.task.Service;

import com.example.task.Other.Socks;
import com.example.task.Repository.Rep.SockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksService {

    private SockRepository sockRepository;

    @Autowired
    public void setSockRepository(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    public Page<Socks> getSocksWithPagingAndFiltering(Specification<Socks> specification, Pageable pageable){
        return sockRepository.findAll(specification, pageable);
    }

    public Socks save(Socks socks){
        return sockRepository.save(socks);
    }
}
