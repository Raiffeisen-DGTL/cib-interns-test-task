package ru.project.raiffeisenbank.service;

import org.springframework.stereotype.Service;
import ru.project.raiffeisenbank.exception.SocksParamException;

@Service
public class SocksValidation {
    public void validateIncomeOutcomeException(String color, int cottonPart, Long quantity) {
        if (color == null || color.isEmpty() || cottonPart < 0 || cottonPart > 100 || quantity == null || quantity <= 0) {
            throw new SocksParamException("Invalid parameters!");
        }
    }

    public void validateGetSocksException(String color, String operation, int cottonPart) {
        if (color == null || color.isEmpty() || cottonPart < 0 || cottonPart > 100 ||
                !operation.equals("moreThan") && !operation.equals("lessThan") && !operation.equals("equal")) {
            throw new SocksParamException("Invalid parameters!");
        }
    }
}
