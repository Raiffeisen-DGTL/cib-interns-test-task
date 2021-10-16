package ru.raiffeisen.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.raiffeisen.core.api.StorageService;
import ru.raiffeisen.core.model.ColorInfoModel;
import ru.raiffeisen.core.model.SocksInfoModel;
import ru.raiffeisen.core.model.StorageInfoModel;
import ru.raiffeisen.core.repository.ColorsRepository;
import ru.raiffeisen.core.repository.StorageRepository;

import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ColorsRepository colorsRepository;

    private final String OPERATION_INCOME = "income";
    private final String OPERATION_OUTCOME = "outcome";


    @Override
    public ResponseEntity<String> updateStorageInfo(StorageInfoModel storageInfoModel, String operation) {

        Optional<ColorInfoModel> colorInfoModel = colorsRepository.findByColor(storageInfoModel.getColor());
        Long colorId;
        if (colorInfoModel.isPresent()) {

            colorId = colorInfoModel.get().getId();
        } else {

            return ResponseEntity.badRequest().body("Color is unknown");
        }

        Optional<SocksInfoModel> socksInfoModel = storageRepository.findByColorIdAndCottonPart(colorId, storageInfoModel.getCottonPart());
        if (socksInfoModel.isPresent()) {

            SocksInfoModel updatedSocksInfoModel = socksInfoModel.get();
            Integer oldQuantity = updatedSocksInfoModel.getQuantity();
            switch (operation) {
                case OPERATION_INCOME:
                    updatedSocksInfoModel.setQuantity(oldQuantity + storageInfoModel.getQuantity());
                    break;
                case OPERATION_OUTCOME:
                    updatedSocksInfoModel.setQuantity(oldQuantity - storageInfoModel.getQuantity());
                    break;
            }

            storageRepository.save(updatedSocksInfoModel);
            return ResponseEntity.ok("Storage info successfully updated");
        } else {

            return ResponseEntity.badRequest().body("Socks is unknown");
        }
    }

    @Override
    public ResponseEntity<String> getQuantityByParams(String color, String operation, int cottonPart) {

        Optional<ColorInfoModel> colorInfoModel = colorsRepository.findByColor(color);
        Long colorId;
        if (colorInfoModel.isPresent()) {

            colorId = colorInfoModel.get().getId();
        } else {

            return ResponseEntity.badRequest().body("Color is unknown");
        }

        Integer quantityByParams;
        switch (operation) {
            case "moreThan":
                quantityByParams = storageRepository.getQuantityByColorIdWithMoreThanCottonPart(colorId, cottonPart);
                break;
            case "lessThan":
                quantityByParams = storageRepository.getQuantityByColorIdWithLessThanCottonPart(colorId, cottonPart);
                break;
            case "equal":
                quantityByParams = storageRepository.getQuantityByColorIdWithEqualCottonPart(colorId, cottonPart);
                break;
            default:
                return ResponseEntity.badRequest().body("Operation is unknown");
        }

        if (quantityByParams == null) {

            return ResponseEntity.ok("0");
        } else {

            return ResponseEntity.ok(quantityByParams.toString());
        }
    }

    @Override
    public ResponseEntity<String> verifyParams(StorageInfoModel storageInfoModel) {

        if (storageInfoModel.getColor() == null) {
            return ResponseEntity.badRequest().body("Color is missing in request body");
        }
        if (storageInfoModel.getCottonPart() == null) {
            return ResponseEntity.badRequest().body("Cotton part is missing in request body");
        }
        if (storageInfoModel.getQuantity() == null) {
            return ResponseEntity.badRequest().body("Quantity is missing in request body");
        }
        if (storageInfoModel.getCottonPart() < 0 || storageInfoModel.getCottonPart() > 100) {
            return ResponseEntity.badRequest().body("Cotton part cannot be less than 0 or more than 100");
        }
        if (storageInfoModel.getQuantity() <= 0) {
            return ResponseEntity.badRequest().body("Quantity cannot be less than or equal to 0");
        }

        return ResponseEntity.ok("Verification of parameters successfully passed");
    }
}
