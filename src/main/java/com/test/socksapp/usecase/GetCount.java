package com.test.socksapp.usecase;

import com.test.socksapp.repository.SockRepository;
import com.test.socksapp.requestmodel.ComparisonOperation;

public class GetCount {
    private SockRepository sockRepo;

    public GetCount(SockRepository sockRepo) {
        this.sockRepo = sockRepo;
    }

    public int execute(String color,
                       ComparisonOperation operation,
                       int cottonPart) {
        return switch (operation) {
            case equal -> sockRepo.countWhereEqual(color, cottonPart);
            case lessThan -> sockRepo.countWhereLess(color, cottonPart);
            case moreThan -> sockRepo.countWhereMore(color, cottonPart);
        };
    }
}
