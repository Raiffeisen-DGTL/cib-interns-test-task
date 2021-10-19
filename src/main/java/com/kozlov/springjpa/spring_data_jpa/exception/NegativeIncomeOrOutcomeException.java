package com.kozlov.springjpa.spring_data_jpa.exception;

public class NegativeIncomeOrOutcomeException extends IllegalArgumentException{
    public NegativeIncomeOrOutcomeException() {
        super("Приход или отпуск носоков не может иметь отрицательное значение.");
    }
}
