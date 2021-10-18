package ru.lsan.cibinternstesttask.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lsan.cibinternstesttask.database.entity.GoodEntity;

@Repository
public interface GoodRepository extends JpaRepository<GoodEntity, Long> {

    @Query("select count(u) from GoodEntity u where u.cottonPart >:percent and u.color =:color")
    long countMoreThan(@Param("color") String color, @Param("percent") int percent);

}
