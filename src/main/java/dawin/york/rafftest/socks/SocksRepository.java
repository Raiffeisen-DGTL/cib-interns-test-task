package dawin.york.rafftest.socks;

import dawin.york.rafftest.socks.tos.Socks;
import dawin.york.rafftest.socks.tos.SocksId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends JpaRepository<Socks, SocksId> {
    @Query("select sum(e.quantity) from Socks e where e.color = :color and e.cottonPart > :cotton_part")
    Integer getMoreThan(@Param("color") String color, @Param("cotton_part") Integer cotton_part);

    @Query("select sum(e.quantity) from Socks e where e.color = :color and e.cottonPart < :cotton_part")
    Integer getLessThan(@Param("color") String color, @Param("cotton_part") Integer cotton_part);

    @Query("select sum(e.quantity) from Socks e where e.color = :color and e.cottonPart = :cotton_part")
    Integer getEqual(@Param("color") String color, @Param("cotton_part") Integer cotton_part);

}
