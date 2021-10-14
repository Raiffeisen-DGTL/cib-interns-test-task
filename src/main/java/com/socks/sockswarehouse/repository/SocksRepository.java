package com.socks.sockswarehouse.repository;

import com.socks.sockswarehouse.models.socks.Socks;
import org.springframework.data.repository.CrudRepository;

public interface SocksRepository extends CrudRepository<Socks, Long> {
}
