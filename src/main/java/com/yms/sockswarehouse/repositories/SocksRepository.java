package com.yms.sockswarehouse.repositories;

import com.yms.sockswarehouse.models.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Repository interface for {@link com.yms.sockswarehouse.models.Socks} class
 */

public interface SocksRepository extends JpaRepository<Socks, Long> {
    Socks findBycottonpart(int cottonpart);
    Socks findByColor(String color);
    Socks findByQuantity(Long quantity);
    Socks findBycottonpartAndColor(int cottonpart, String color);
    List<Socks> findAll();
    List<Socks> findAllBycottonpartGreaterThanAndColor(int cottonpart, String color);
    List<Socks> findAllBycottonpartLessThanAndColor(int cottonpart, String color);
    List<Socks> findAllBycottonpartAndColor(int cottonpart, String color);


}
