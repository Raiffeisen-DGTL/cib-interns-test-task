package project.ralfproj;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocksRepo  extends CrudRepository<SocksS, Long> {

    @Query(value = "SELECT * FROM sockss WHERE cotton = ?1 AND color = ?2", nativeQuery = true) //WHERE cotton = ?1 AND color= ?2
    List<SocksS> findBycottonAndcolor(String cotton , String color); //String cotton, String color


    @Query(value="SELECT COUNT(*) FROM sockss WHERE color = ?1 AND cotton > ?2", nativeQuery = true)
    Integer findByCottonColorMore(String color , String cotton); //String cotton, String color

    @Query(value="SELECT COUNT(*) FROM sockss WHERE color = ?1 AND cotton < ?2", nativeQuery = true)
    Integer findByCottonColorLess(String color , String cotton); //String cotton, String color

    @Query(value="SELECT COUNT(*) FROM sockss WHERE color = ?1 AND cotton = ?2", nativeQuery = true)
    Integer findByCottonColorEqual(String color , String cotton); //String cotton, String color

}
