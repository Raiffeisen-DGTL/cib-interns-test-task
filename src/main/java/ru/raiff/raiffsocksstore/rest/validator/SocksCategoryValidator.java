package ru.raiff.raiffsocksstore.rest.validator;

import org.springframework.stereotype.Component;
import ru.raiff.raiffsocksstore.exception.CottonPartMoreThan100Exception;
import ru.raiff.raiffsocksstore.rest.dto.SocksCategoryDto;

@Component
public class SocksCategoryValidator {

    public void validate(SocksCategoryDto dto) {
        checkCottonPartLessThan100(dto.getCottonPart());
    }

    private void checkCottonPartLessThan100(Short cottonPart) {
        if (cottonPart > 100) throw new CottonPartMoreThan100Exception();
    }
}
