package ru.raiff.raiffsocksstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raiff.raiffsocksstore.exception.CategoryAlreadyExistsException;
import ru.raiff.raiffsocksstore.repository.SocksCategoryRepository;
import ru.raiff.raiffsocksstore.rest.dto.SocksCategoryDto;
import ru.raiff.raiffsocksstore.rest.validator.SocksCategoryValidator;

import static ru.raiff.raiffsocksstore.rest.converter.SocksCategoryConverter.toModel;
import static ru.raiff.raiffsocksstore.rest.converter.SocksCategoryConverter.toDto;

@Service
@RequiredArgsConstructor
public class SocksCategoryService {

    private final SocksCategoryRepository repository;
    private final SocksCounterService counterService;
    private final SocksCategoryValidator validator;

    public SocksCategoryDto create(SocksCategoryDto dto) {
        validator.validate(dto);

        if (!repository.existsByColorAndCottonPart(dto.getColor(), dto.getCottonPart())) {
            var savedCategory = repository.save(toModel(dto));
            counterService.createCategoryCounter(savedCategory);

            return toDto(savedCategory);
        } else throw new CategoryAlreadyExistsException();
    }
}