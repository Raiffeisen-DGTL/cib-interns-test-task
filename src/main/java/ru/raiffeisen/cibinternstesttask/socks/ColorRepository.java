package ru.raiffeisen.cibinternstesttask.socks;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ColorRepository extends CrudRepository<Color, String> {

    Optional<Color> findByName(String name);

}
