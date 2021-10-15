package com.test.socksapp.usecase;

import com.test.socksapp.entity.Sock;
import com.test.socksapp.exception.SockInvalidArgumentException;
import com.test.socksapp.exception.ValueConstraintValidator;
import com.test.socksapp.repository.SockRepository;

public class RegOutcome {
    private SockRepository sockRepo;

    public RegOutcome(SockRepository sockRepo) {
        this.sockRepo = sockRepo;
    }

    public void execute(String color,
                        int cottonPart,
                        int q) {
        ValueConstraintValidator.notNullValidate(color);
        ValueConstraintValidator.percentValidate(cottonPart);
        ValueConstraintValidator.positiveValidate(q);

        Sock sockRecord = sockRepo.findByColorAndCottonpart(color, cottonPart);
        if (sockRecord != null && sockRecord.getQuantity() >= q) {
            sockRecord.setQuantity(sockRecord.getQuantity() - q);
            sockRepo.save(sockRecord);
            return;
        }

        throw new SockInvalidArgumentException();
    }
}
