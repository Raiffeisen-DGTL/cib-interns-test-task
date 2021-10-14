package task.raif.model;

import org.springframework.lang.NonNull;
import task.raif.enumContainer.Operations;
import task.raif.exception.SocksValidationException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class SocksFilter {

    @NotBlank
    private final String color;

    @Min(0)
    @Max(100)
    private final int cottonPart;

    @NonNull
    Operations operation;

    public SocksFilter(String color, int cottonPart, Operations operation) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.operation = operation;
    }

    public String getColor() {
        return color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public Operations getOperation() {
        return operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SocksFilter socksFilter = (SocksFilter) o;
        return cottonPart == socksFilter.cottonPart && Objects.equals(color, socksFilter.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart, operation);
    }



}
