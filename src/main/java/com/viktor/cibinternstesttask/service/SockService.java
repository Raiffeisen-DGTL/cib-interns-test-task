package com.viktor.cibinternstesttask.service;

import com.viktor.cibinternstesttask.dao.SockDao;
import com.viktor.cibinternstesttask.dto.SockDto;
import com.viktor.cibinternstesttask.dto.SockParamsDto;
import com.viktor.cibinternstesttask.entity.Sock;
import com.viktor.cibinternstesttask.exception.WrongParameterException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class SockService {
    @Autowired
    private SockDao sockDao;

    public enum Action {
        INCOME,
        OUTCOME
    }

    public enum Operation {
        MORE_THAN("moreThan"),
        LESS_THAN("lessThan"),
        EQUAL("equal"),
        NONE("none");

        private final String label;

        private static final Map<String, Operation> lookup = new HashMap<>();

        static {
            for (Operation op : Operation.values()) {
                lookup.put(op.getLabel(), op);
            }
        }

        Operation(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }

        public static Operation get(String label) {
            return lookup.getOrDefault(label, Operation.NONE);
        }
    }

    public void update(SockDto sockDto, Action operation) {
        ModelMapper modelMapper = new ModelMapper();
        Sock sock = modelMapper.map(sockDto, Sock.class);

        switch (operation) {
            case INCOME:
                sockDao.updateIncome(sock);
                break;

            case OUTCOME:
                sockDao.updateOutcome(sock);
                break;
        }
    }

    public Long getSumOfNecessarySocks(SockParamsDto sockParamsDto) {
        Long sum = 0L;
        String operation = sockParamsDto.getOperation();

        switch (Operation.get(operation)) {
            case MORE_THAN:
                sum = sockDao.countSockWithMoreThanCottonPart(sockParamsDto.getColor(), sockParamsDto.getCottonPart());
                break;

            case LESS_THAN:
                sum = sockDao.countSockWithLessThanCottonPart(sockParamsDto.getColor(), sockParamsDto.getCottonPart());
                break;

            case EQUAL:
                sum = sockDao.countSockWithEqualCottonPart(sockParamsDto.getColor(), sockParamsDto.getCottonPart());
                break;

            case NONE:
                throw new WrongParameterException("Wrong comparison value");

        }

        return sum;
    }

}
