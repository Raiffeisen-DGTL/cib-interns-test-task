package me.mlnn.raif.util;

import me.mlnn.raif.model.SocksModel;

public class SocksValidatorUtil {
    public static Boolean validateColor(String color) {
        return color != null;
    }
    
    public static Boolean validateCottonPart(Integer cottonPart) {
        return cottonPart != null && cottonPart >= 0 && cottonPart <= 100;
    }
    
    public static Boolean validateQuantity(Integer quantity) {
        return quantity != null && quantity > 0;
    }
    
    public static Boolean validateSocksModel(SocksModel socks) {
        return validateColor(socks.getColor())
                && validateCottonPart(socks.getCottonPart())
                && validateQuantity(socks.getQuantity());
    }
}
