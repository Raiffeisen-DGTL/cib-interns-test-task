package ru.raiffeisen.cibinternstesttask.system.dto;

/**
 * Используется для возврата клиенту информации об ошибке.
 */
public record ErrorResponse(Long timestamp,
                            Integer status,
                            String error,
                            String message,
                            String path) {
}
