package socks;


import java.util.*;

public class SocksService {
    private final Map<Socks,Long> socksMap;

    public SocksService(){
        socksMap=new HashMap<>();
    }

    public boolean addSocks(Socks socks){
        if(socks.quantity<=0)
            return false;
        if(socksMap.containsKey(socks)){
            socksMap.put(socks,socksMap.get(socks)+socks.quantity);
            return true;
        } else{
            socksMap.put(socks,socks.quantity);
            return true;
        }
    }
    public boolean delSocks(Socks socks){
        if(socks.quantity<=0)
            return false;
        if(socksMap.containsKey(socks)){
            if(socks.quantity<socksMap.get(socks)){
                socksMap.put(socks,socksMap.get(socks)-socks.quantity);
                return true;
            }
        }
        return false;
    }
    public int get(String color,String operation, Long cottonPart){
        int result=0;
        for (Socks socks: socksMap.keySet()) {
            if(color.equals(socks.color))
                if(operation.equals("moreThan")){
                    if(socks.cottonPart>cottonPart)
                        result+=socksMap.get(socks);
                } else if(operation.equals(("lessThan"))){
                    if(socks.cottonPart<cottonPart)
                        result+=socksMap.get(socks);
                } else if(operation.equals("equal")){
                    if(socks.cottonPart.equals(cottonPart))
                        result+=socksMap.get(socks);
                }
        }
        return result;
    }

    @Override
    public String toString() {
        return "SocksService{" +
                "socksMap=" + socksMap +
                '}';
    }
}
