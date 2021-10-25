package ru.simbial.cibinternstesttask.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.simbial.cibinternstesttask.app.model.SocksDBModel;


@Repository
public interface SocksRepository extends JpaRepository<SocksDBModel, SocksDBModel.SocksId> {

    @Query("select coalesce(sum(s.quantity), 0) from SocksDBModel s " +
            " where s.id.color=?1 and s.id.cottonPart>?2")
    Long getQuantityByColorAndCottonPartMoreThan(String color, Integer cottonPart);


    @Query("select coalesce(sum(s.quantity), 0) from SocksDBModel s " +
            "where s.id.color=?1" +
            " and s.id.cottonPart<?2 ")
    Long getQuantityByColorAndCottonPartLessThan(String color, Integer cottonPart);

    @Query("select coalesce(sum(s.quantity), 0) from SocksDBModel s " +
            "where s.id.color=?1" +
            " and s.id.cottonPart=?2 ")
    Long getQuantityByColorAndCottonPartEqual(String color, Integer cottonPart);

}
