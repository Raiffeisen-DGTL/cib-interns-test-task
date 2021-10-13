package com.github.furetur.raiffeisentask;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks, Long> {
}
