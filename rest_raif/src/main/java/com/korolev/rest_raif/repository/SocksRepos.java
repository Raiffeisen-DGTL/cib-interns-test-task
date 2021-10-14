package com.korolev.rest_raif.repository;

import com.korolev.rest_raif.domain.Sock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SocksRepos extends CrudRepository<Sock,Long> {
    @Query(value = "select (sum(case when operation = 'true' then quantity else 0 end) - sum(case when operation = 'false' then quantity else 0 end)) as balance from Sock where color = :color and" +
            "(case when :oper = 'lessThan' then cotton_part < :cotton " +
            "when :oper = 'moreThan' then cotton_part > :cotton " +
            "when :oper = 'equal' then cotton_part = :cotton end)",
            nativeQuery = true)
    Integer findBalance(@Param("color") String color,
                        @Param("oper") String oper,
                        @Param("cotton") Integer cotton);

    @Query(value = "select (sum(case when operation = 'true' then quantity else 0 end) - sum(case when operation = 'false' then quantity else 0 end)) as balance from Sock where color = :color and " +
             " cotton_part = :cotton",
            nativeQuery = true)
    Integer findBalanceBeforeOutcome(@Param("color") String color,
                                     @Param("cotton") Integer cotton);

}
