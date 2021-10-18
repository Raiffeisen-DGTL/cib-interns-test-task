package ru.lsan.cibinternstesttask.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lsan.cibinternstesttask.database.entity.CompositionEntity;

@Repository
public interface CompositionRepository extends JpaRepository<CompositionEntity, Long> {
}
