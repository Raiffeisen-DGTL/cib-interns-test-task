package com.n75jr.apitesttask.dao;

import com.n75jr.apitesttask.model.Sock;
import com.n75jr.apitesttask.model.SockID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SockRepository extends JpaRepository<Sock, SockID> {
}
