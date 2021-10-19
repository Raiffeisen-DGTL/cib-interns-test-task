package ru.lsan.cibinternstesttask.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lsan.cibinternstesttask.database.entity.SockEntity;

@Repository
public interface SockRepository extends JpaRepository<SockEntity, Long> {

    @Query("select sum(stored) as total from SockEntity where cotton_part >:percent and color =:color")
    long countMoreThan(@Param("color") String color, @Param("percent") int percent);

    @Query("select sum(stored) as total from SockEntity where cotton_part <:percent and color =:color")
    long countLessThan(@Param("color") String color, @Param("percent") int percent);

    @Query("select sum(stored) as total from SockEntity where cotton_part =:percent and color =:color")
    long countEquals(@Param("color") String color, @Param("percent") int percent);

    @Query("select u from SockEntity u where u.cotton_part =:percent and u.color =:color")
    SockEntity findByDto(@Param("color") String color, @Param("percent") int percent);

    @Transactional
    @Modifying
    @Query("update SockEntity set stored = stored +:quantity where cotton_part =:percent and color =:color")
    void incomeSockCount(@Param("color") String color, @Param("percent") int percent, @Param("quantity") int quantity);

    @Transactional
    @Modifying
    @Query("update SockEntity set stored = stored -:quantity where cotton_part =:percent and color =:color")
    void outcomeSockCount(@Param("color") String color, @Param("percent") int percent, @Param("quantity") int quantity);

}
