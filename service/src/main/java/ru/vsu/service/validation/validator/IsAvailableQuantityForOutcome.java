package ru.vsu.service.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vsu.db.entity.Socks;
import ru.vsu.db.provider.SocksProvider;
import ru.vsu.service.model.SocksDto;
import ru.vsu.service.validation.IsAvailableQuantity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class IsAvailableQuantityForOutcome implements ConstraintValidator<IsAvailableQuantity, SocksDto> {

  private final SocksProvider provider;

  @Autowired
  public IsAvailableQuantityForOutcome(SocksProvider provider) {
    this.provider = provider;
  }

  @Override
  public boolean isValid(SocksDto value, ConstraintValidatorContext context) {
    if (value == null || value.getQuantity() == null) {
      return true;
    }
    Optional<Socks> socks = provider.findById(value.getColor(), value.getCottonPart());
    if (socks.isEmpty()) {
      return true;
    }

    return socks.get().getQuantity() >= value.getQuantity();
  }
}
