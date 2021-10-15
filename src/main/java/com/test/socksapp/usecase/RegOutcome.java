package com.test.socksapp.usecase;

import com.test.socksapp.entity.Sock;
import com.test.socksapp.repository.SockRepository;

public class RegOutcome {
    private SockRepository sockRepo;

    public RegOutcome(SockRepository sockRepo) {
        this.sockRepo = sockRepo;
    }

    public void execute(String color,
                        int cottonPart,
                        int q) throws IllegalArgumentException {
        Sock sockRecord = sockRepo.findByColorAndCottonpart(color, cottonPart);
        if (sockRecord != null) {
            sockRecord.setQuantity(sockRecord.getQuantity() - q);
            sockRepo.save(sockRecord);
            return;
        }

        throw new IllegalArgumentException();
    }
}
