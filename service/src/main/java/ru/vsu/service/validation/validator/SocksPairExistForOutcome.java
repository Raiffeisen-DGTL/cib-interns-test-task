package ru.vsu.service.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vsu.db.provider.SocksProvider;
import ru.vsu.service.model.SocksDto;
import ru.vsu.service.validation.SocksPairExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SocksPairExistForOutcome implements ConstraintValidator<SocksPairExist, SocksDto> {

  private final SocksProvider provider;

  @Autowired
  public SocksPairExistForOutcome(SocksProvider provider) {
    this.provider = provider;
  }

  @Override
  public boolean isValid(SocksDto value, ConstraintValidatorContext context) {
    if (value == null || value.getColor() == null || value.getCottonPart() == null) {
      return true;
    }
    return provider.findById(value.getColor(), value.getCottonPart()).isPresent();
  }
}
