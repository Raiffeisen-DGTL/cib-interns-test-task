package com.mn000009.warehouse.repository;

import com.mn000009.warehouse.domain.SocksPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<SocksPackage, Long> {

  Optional<SocksPackage> findSocksByColor_TitleAndCottonPartEqualsAndQuantityGreaterThanEqual(String color, int cottonPart,
                                                                                              int quantity);

  Optional<SocksPackage> findSocksByColor_TitleAndCottonPartEquals(String color, int cottonPart);

  @Query("select sum(s.quantity) " +
      "from SocksPackage s " +
      "where s.color.title=?1 " +
      "and s.cottonPart>?2")
  Optional<Integer> findAllByColor_TitleAndCottonPartGreaterThan(String color, int cottonPart);

  @Query("select sum(s.quantity) " +
      "from SocksPackage s " +
      "where s.color.title=?1 " +
      "and s.cottonPart<?2")
  Optional<Integer> findAllByColor_TitleAndCottonPartLessThan(String color, int cottonPart);

  @Query("select sum(s.quantity) " +
      "from SocksPackage s " +
      "where s.color.title=?1 " +
      "and s.cottonPart<?2")
  Optional<Integer> findAllByColor_TitleAndCottonPartEquals(String color, int cottonPart);

}
