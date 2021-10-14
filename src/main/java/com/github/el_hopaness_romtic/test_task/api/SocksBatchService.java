package com.github.el_hopaness_romtic.test_task.api;

import com.github.el_hopaness_romtic.test_task.model.SocksBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksBatchService {

    @Autowired
    private SocksBatchDAO socksBatchDAO;

    int countQuantityByColorAndCottonPart(String color, int cottonPart) {
        List<SocksBatch> list = socksBatchDAO.findByColorAndCottonPart(color, cottonPart);
        return list.isEmpty() ? 0 : list.get(0).quantity;
    }

    int countQuantityByColorAndCottonPartLessThan(String color, int cottonPart) {
        Integer result = socksBatchDAO.countQuantityByColorAndCottonPartLessThan(color, cottonPart);
        return result != null ? result : 0;
    }

    int countQuantityByColorAndCottonPartMoreThan(String color, int cottonPart) {
        Integer result = socksBatchDAO.countQuantityByColorAndCottonPartMoreThan(color, cottonPart);
        return result != null ? result : 0;
    }

    SocksBatch addBatch(SocksBatch socksBatch) {
        SocksBatch result;
        List<SocksBatch> socksBatchList = socksBatchDAO.findByColorAndCottonPart(socksBatch.color, socksBatch.cottonPart);
        if (socksBatchList.isEmpty()) {
            result = socksBatchDAO.save(socksBatch);
        } else {
            SocksBatch oldResult = socksBatchList.get(0);
            oldResult.quantity += socksBatch.quantity;
            result = socksBatchDAO.save(oldResult);
        }
        return result;
    }

    SocksBatch removeBatch(SocksBatch socksBatch) {
        List<SocksBatch> socksBatchList = socksBatchDAO.findByColorAndCottonPart(socksBatch.color, socksBatch.cottonPart);
        if (socksBatchList.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("There is no socks with Color = \"%s\" and CottonPart = %d", socksBatch.color, socksBatch.cottonPart)
            );
        }

        SocksBatch oldResult = socksBatchList.get(0), result;
        oldResult.quantity -= socksBatch.quantity;
        if (oldResult.quantity < 0) {
            throw new IllegalArgumentException("There is not enough socks in the storage");
        }

        result = socksBatchDAO.save(oldResult);
        return result;
    }
}
