package org.vetirdoit.sock.registration.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vetirdoit.sock.registration.domain.Color;
import org.vetirdoit.sock.registration.domain.entities.SockType;

import java.util.Optional;

@Repository
public interface SockRepository extends CrudRepository<SockType, Long> {
    Optional<SockType> findSockTypeByColorAndCottonPart(Color color, int cottonPart);

    @Query("SELECT COALESCE(SUM(st.quantity), 0) FROM SockType st WHERE st.color = ?1 and st.cottonPart > ?2")
    long countSockTypesWhenCottonPartGreaterThan(Color color, int cottonPart);

    @Query("SELECT COALESCE(SUM(st.quantity), 0) FROM SockType st WHERE st.color = ?1 and st.cottonPart = ?2")
    long countSockTypesWhenCottonPartEqual(Color color, int cottonPart);

    @Query("SELECT COALESCE(SUM(st.quantity), 0) FROM SockType st WHERE st.color = ?1 and st.cottonPart < ?2")
    long countSockTypesWhenCottonPartLessThan(Color color, int cottonPart);

}
