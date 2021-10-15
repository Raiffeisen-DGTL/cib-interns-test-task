package com.test.socksapp.usecase;

import com.test.socksapp.entity.Sock;
import com.test.socksapp.exception.ValueConstraintValidator;
import com.test.socksapp.repository.SockRepository;

public class RegIncome {
    private SockRepository sockRepo;

    public RegIncome(SockRepository sockRepo) {
        this.sockRepo = sockRepo;
    }

    public void execute(String color,
                        int cottonPart,
                        int q) {
        ValueConstraintValidator.notNullValidate(color);
        ValueConstraintValidator.percentValidate(cottonPart);
        ValueConstraintValidator.positiveValidate(q);

        Sock sockRecord = sockRepo.findByColorAndCottonpart(color, cottonPart);
        if (sockRecord != null) {
            sockRecord.setQuantity(sockRecord.getQuantity() + q);
            sockRepo.save(sockRecord);
            return;
        }

        Sock newSockRecord = new Sock();
        newSockRecord.setColor(color);
        newSockRecord.setCottonpart(cottonPart);
        newSockRecord.setQuantity(q);
        sockRepo.save(newSockRecord);

    }
}
