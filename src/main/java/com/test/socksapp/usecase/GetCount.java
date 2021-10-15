package com.test.socksapp.usecase;

import com.test.socksapp.entity.Sock;
import com.test.socksapp.exception.SockInvalidArgumentException;
import com.test.socksapp.exception.ValueConstraintValidator;
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
        ValueConstraintValidator.notNullValidate(color);
        ValueConstraintValidator.percentValidate(cottonPart);

        switch (operation) {
            case equal:
                return sockRepo.findWhereEqual(color, cottonPart).stream().mapToInt(Sock::getQuantity).sum();
            case lessThan:
                return sockRepo.findWhereLess(color, cottonPart).stream().mapToInt(Sock::getQuantity).sum();
            case moreThan:
                return sockRepo.findWhereMore(color, cottonPart).stream().mapToInt(Sock::getQuantity).sum();
        }

        throw new SockInvalidArgumentException();
    }
}
