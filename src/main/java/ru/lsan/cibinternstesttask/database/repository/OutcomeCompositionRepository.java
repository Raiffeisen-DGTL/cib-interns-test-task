package ru.lsan.cibinternstesttask.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lsan.cibinternstesttask.database.entity.OutcomeCompositionEntity;

@Repository
public interface OutcomeCompositionRepository extends JpaRepository<OutcomeCompositionEntity, Long> {
}
