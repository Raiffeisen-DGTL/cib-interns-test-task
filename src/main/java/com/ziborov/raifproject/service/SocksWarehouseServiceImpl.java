package com.ziborov.raifproject.service;

import com.ziborov.raifproject.dto.ComparisonOperation;
import com.ziborov.raifproject.dto.SocksAccountingRequest;
import com.ziborov.raifproject.exception.RequestValidationException;
import com.ziborov.raifproject.model.Socks;
import com.ziborov.raifproject.repository.SocksRepository;
import com.ziborov.raifproject.validator.SocksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.ziborov.raifproject.dto.ComparisonOperation.*;

@Service
public class SocksWarehouseServiceImpl implements SocksWarehouseService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksWarehouseServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Transactional
    public void socksIncome(SocksAccountingRequest incomeRequest) {
        List<Socks> socksList = new ArrayList<>();

        for (int i = 0; i < incomeRequest.getQuantity(); i++) {
            socksList.add(Socks.builder()
                    .color(incomeRequest.getColor())
                    .cottonPart(incomeRequest.getCottonPart())
                    .build());
        }

        socksRepository.saveAll(socksList);
    }

    @Transactional
    public void socksOutcome(SocksAccountingRequest outcomeRequest) {
        long socksCount = socksRepository.countByColorAndCottonPart(outcomeRequest.getColor(), outcomeRequest.getCottonPart());

        if (outcomeRequest.getQuantity() > socksCount) {
            throw new RequestValidationException("Quantity is more than socks in the database, socks count is " + socksCount);
        }

        List<Socks> socksToRemove = socksRepository.findAllByColorAndCottonPart(outcomeRequest.getColor(), outcomeRequest.getCottonPart(),
                PageRequest.of(0, outcomeRequest.getQuantity()));
        socksRepository.deleteAllInBatch(socksToRemove);
    }

    public Long getSocksQuantity(String color, String operation, Integer cottonPart) {
        SocksValidator.validateSocksValues(color, operation, cottonPart);

        Socks.SocksColor socksColor = Socks.SocksColor.fromString(color);
        ComparisonOperation comparisonOperation = ComparisonOperation.fromString(operation);

        if (comparisonOperation.equals(EQUAL)) {
            return socksRepository.countByColorAndCottonPart(socksColor, cottonPart);
        } else if (comparisonOperation.equals(LESS_THAN)) {
            return socksRepository.countByColorAndCottonPartLessThan(socksColor, cottonPart);
        } else {
            return socksRepository.countByColorAndCottonPartGreaterThan(socksColor, cottonPart);
        }
    }

}