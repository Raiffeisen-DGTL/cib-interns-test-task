package ru.raiffeisen.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.core.model.SocksInfoModel;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<SocksInfoModel, Long> {

    Optional<SocksInfoModel> findByColorIdAndCottonPart(Long colorId, Integer cottonPart);

    @Query(value = "select sum(quantity) " +
                   "from socks " +
                   "where color_id = ?1 " +
                   "and cotton_part > ?2", nativeQuery = true)
    Integer getQuantityByColorIdWithMoreThanCottonPart(Long colorId, Integer cottonPart);

    @Query(value = "select sum(quantity) " +
                   "from socks " +
                   "where color_id = ?1 " +
                   "and cotton_part < ?2", nativeQuery = true)
    Integer getQuantityByColorIdWithLessThanCottonPart(Long colorId, Integer cottonPart);

    @Query(value = "select sum(quantity) " +
                   "from socks " +
                   "where color_id = ?1 " +
                   "and cotton_part = ?2", nativeQuery = true)
    Integer getQuantityByColorIdWithEqualCottonPart(Long colorId, Integer cottonPart);
}
