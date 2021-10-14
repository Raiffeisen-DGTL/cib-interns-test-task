package com.example.interntask;

import org.springframework.boot.json.*;
import org.javatuples.Triplet;
import java.util.Map;

public class ParsingService{
    public static Triplet<String, Integer, Integer> JSONParse(String raw){
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = parser.parseMap(raw);
        Triplet <String, Integer, Integer> retValue = Triplet.with(map.get("color").toString(),
                Integer.parseInt(map.get("cottonPart").toString()), Integer.parseInt(map.get("quantity").toString()));
        int firstVal = Integer.parseInt(retValue.getValue1().toString());
        int secondVal = Integer.parseInt(retValue.getValue2().toString());
        if(retValue.getValue0().isEmpty() ||
                !(0 <= firstVal && firstVal <= 100) ||
                !(0 < secondVal)){
            throw new IllegalArgumentException();
        }
        return retValue;
    }


}
