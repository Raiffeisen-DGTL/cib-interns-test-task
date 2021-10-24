package ru.simbial.cibinternstesttask.app;

import ru.simbial.cibinternstesttask.app.model.SocksDBModel;
import ru.simbial.cibinternstesttask.app.model.SocksRequestData;


public class SocksUtil {

    //null if data is not valid
    public static SocksDBModel getSockFromRequestData(SocksRequestData data) {
        return validateSocksRequestBody(data) ?
                new SocksDBModel(
                        new SocksDBModel.SocksId(
                                data.getColor(), data.getCottonPart()), data.getQuantity())
                : null;
    }

    public static boolean checkGetAllSocksRequestFiltersOk(String color,
                                                           String operation,
                                                           Integer cottonPart) {
        return color != null
                && !color.isBlank()
                && cottonPart != null
                && validateOperation(operation);

    }

    private static boolean validateSocksRequestBody(SocksRequestData data) {
        return data != null
                && data.getColor() != null
                && !data.getColor().isBlank()
                && data.getCottonPart() != null
                && data.getQuantity() != null
                && validateCottonPart(data.getCottonPart())
                && checkQuantityIsPositive(data.getQuantity());
    }

    private static boolean validateCottonPart(int cottonPart) {
        return cottonPart >= 0 && cottonPart <= 100;
    }

    private static boolean checkQuantityIsPositive(long quantity) {
        return quantity > 0;
    }

    private static boolean validateOperation(String operation) {
        return operation != null && CottonPartComparisonOperation.getLabels().contains(operation);
    }

    /*private static boolean validateCottonPartFilter(int cottonPart, CottonPartComparisonOperation operation) {
        return operation == EQUAL && cottonPart >= 0 && cottonPart <= 100
                || operation == MORE_THAN && cottonPart >= -1 && cottonPart <= 99
                || operation == LESS_THAN && cottonPart > 0 && cottonPart < 101;
    }*/


}
