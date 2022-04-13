package com.example.socks.db.repository;

import com.example.socks.db.entity.Socks;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SocksRepositoryBySpecification extends CrudRepository<Socks, Long>, JpaSpecificationExecutor<Socks> {
}
