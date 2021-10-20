package com.raif.app.dao.repository;

import com.raif.app.dao.model.SocksStorage;
import com.raif.app.dao.mapper.SockStorageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SocksStorageRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SockStorageMapper sockStorageMapper;

    public SocksStorage findByColorAndCottonPart(String color, Integer cottonPart) {
        List<SocksStorage> socks = jdbcTemplate.query(
                "SELECT * from socks_storage WHERE color = :color AND cotton_part = :cotton_part",
                new MapSqlParameterSource()
                        .addValue("color", color)
                        .addValue("cotton_part", cottonPart),
                sockStorageMapper
        );
        if (socks.isEmpty()) {
            return null;
        }
        return socks.iterator().next();
    }

    public List<SocksStorage> findAllByColorAndCottonPartIsGreaterThan(String color, Integer cottonPart) {
        return jdbcTemplate.query(
                "SELECT * from socks_storage WHERE color = :color AND cotton_part > :cotton_part",
                new MapSqlParameterSource()
                        .addValue("color", color)
                        .addValue("cotton_part", cottonPart),
                sockStorageMapper
        );
    }

    public List<SocksStorage> findAllByColorAndCottonPartIsLessThan(String color, Integer cottonPart) {
        return jdbcTemplate.query(
                "SELECT * from socks_storage WHERE color = :color AND cotton_part < :cotton_part",
                new MapSqlParameterSource()
                        .addValue("color", color)
                        .addValue("cotton_part", cottonPart),
                sockStorageMapper
        );
    }

    public int updateQuantity(Long id, Long quantity) {
        return jdbcTemplate.update(
                "UPDATE socks_storage SET quantity = :quantity WHERE id = :id",
                new MapSqlParameterSource()
                        .addValue("quantity", quantity)
                        .addValue("id", id)
        );
    }

    public int insert(SocksStorage socksStorage) {
        return jdbcTemplate.update(
                "INSERT INTO socks_storage (color, cotton_part, quantity) " +
                        "VALUES (:color, :cottonPart, :quantity)",
                new MapSqlParameterSource()
                        .addValue("color", socksStorage.getColor())
                        .addValue("cottonPart", socksStorage.getCottonPart())
                        .addValue("quantity", socksStorage.getQuantity())
        );
    }

}
