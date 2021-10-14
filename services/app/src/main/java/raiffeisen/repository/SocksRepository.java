package raiffeisen.repository;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import raiffeisen.models.socks.Socks;

import java.util.Optional;

/**
 * @author voroningg
 */
@Repository
public interface SocksRepository extends CrudRepository<Socks, String> {

    @Modifying
    @Query("create table \"Socks\"\n" +
            "(\n" +
            "    color text not null,\n" +
            "    cotton_part int not null,\n" +
            "    quantity int not null,\n" +
            "    constraint socks_pk\n" +
            "\tunique (color, cotton_part)\n" +
            ");\n" +
            "\n" +
            "create unique index socks_color_uindex\n" +
            "    on \"Socks\" (color, cotton_part);")
    void createTable();


    @Query("SELECT SUM(quantity) FROM public.\"Socks\"\n" +
            "WHERE color = :color AND cotton_part < :cottonPart")
    Optional<Integer> countLessThan(
            @Param("color") String color,
            @Param("cottonPart") int cottonPart);


    @Query("SELECT SUM(quantity) FROM public.\"Socks\"\n" +
            "WHERE color = :color AND cotton_part > :cottonPart")
    Optional<Integer> countMoreThan(
            @Param("color") String color,
            @Param("cottonPart") int cottonPart);


    @Query("SELECT SUM(quantity) FROM public.\"Socks\"\n" +
            "WHERE color = :color AND cotton_part = :cottonPart")
    Optional<Integer> countEquals(
            @Param("color") String color,
            @Param("cottonPart") int cottonPart);


    @Query("SELECT * FROM public.\"Socks\" where color = :color and cotton_part = :cottonPart")
    Optional<Socks> findSocks(@Param("color") String color, @Param("cottonPart") int cottonPart);
}
