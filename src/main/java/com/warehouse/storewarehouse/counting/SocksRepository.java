package com.warehouse.storewarehouse.counting;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SocksRepository extends CrudRepository<SocksRecords,Long> {

    @Query("select socks from SocksRecords socks where socks.color = ?1 and socks.cottonPart = ?2")
    Optional<SocksRecords> getSocksRecordsByColorAndcottonPart(String color, int cottonPart);

    @Modifying
    @Transactional
    @Query("update SocksRecords socks set socks.quantity = ?1 where socks.color = ?2 and socks.cottonPart = ?3")
    void setNewQuantityForSocksRecord(Integer newQuantity, String color, int cottonPart);

    @Query("select sum(socks.quantity) from SocksRecords socks where socks.color = ?1 and socks.cottonPart = ?2")
    int findSocksRecordsByColorEquals(String color, int cottonPart);

    @Query("select sum(socks.quantity) from SocksRecords socks where socks.color = ?1 and socks.cottonPart > ?2")
    int findSocksRecordsByColorAndMoreThanCottonPart(String color, int cottonPart);

    @Query("select sum(socks.quantity) from SocksRecords socks where socks.color = ?1 and socks.cottonPart < ?2")
    int findSocksRecordsByColorAndLessThanCottonPart(String color, int cottonPart);
}
