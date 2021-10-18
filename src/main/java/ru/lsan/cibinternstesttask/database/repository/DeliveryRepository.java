package ru.lsan.cibinternstesttask.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lsan.cibinternstesttask.database.entity.DeliveryEntity;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
}
