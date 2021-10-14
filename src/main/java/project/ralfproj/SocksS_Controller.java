package project.ralfproj;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;

import java.util.List;


@RestController
public class SocksS_Controller {


    @Autowired
    private SocksRepo socksRepo;


    @RequestMapping("/api/allSocks")
    public Iterable main (SocksS socksS){
        Iterable<SocksS> socks = socksRepo.findAll();
        return (socks);
    }


        @RequestMapping(method = RequestMethod.POST, value="/api/socks/income")
    public String CreateSocks (@RequestBody String payload) throws Exception {

            String color;
            String cottonPart;
            Integer iter;

            try {
                JSONObject jsonObj = new JSONObject(payload);
                color= (String) jsonObj.get("color");
                iter = (Integer) jsonObj.get("quantity");
                cottonPart = (String) jsonObj.get("cottonPart");

                if (color.isEmpty() || iter==0 || cottonPart.isEmpty() || Integer.parseInt(cottonPart)>100 || Integer.parseInt(cottonPart)<0 ){
                    throw new Exception("Empty variable");
                }

                try {
                    for (int i = 0; i<iter; i++){
                        SocksS Sock = new SocksS(color, cottonPart);
                        socksRepo.save(Sock);
                    }
                    System.out.println("HTTP 200");
                    return ("HTTP 200");
                } catch (Exception e){
                    System.out.println(e);
                    return ("HTTP 500");
                }

            } catch (Exception e){
                System.out.println("HTTP 400 ");
                System.out.println(e);
                return ("HTTP 400");
            }

        }


    @RequestMapping(method = RequestMethod.POST, value="/api/socks/outcome")
    public String DeleteSocks (@RequestBody String payload) throws Exception {

        String color;
        String cottonPart;
        Integer iter;

        try {
            JSONObject jsonObj = new JSONObject(payload);
            color= (String) jsonObj.get("color");
            iter = (Integer) jsonObj.get("quantity");
            cottonPart = (String) jsonObj.get("cottonPart");

            if (color.isEmpty() || iter==0 || cottonPart.isEmpty() || Integer.parseInt(cottonPart)>100 || Integer.parseInt(cottonPart)<0 ){
                throw new Exception("Empty variable");
            }


            try {
                List<SocksS> sock = socksRepo.findBycottonAndcolor(cottonPart, color);
                for (int i = 0; i<iter; i++){
                    SocksS socker = sock.get(i);
                    socksRepo.deleteById(socker.getId());
                }
                System.out.println("HTTP 200");
                return ("HTTP 200");

            } catch (Exception e){
                System.out.println(e);
                return ("HTTP 500");
            }

        } catch (Exception e){
            System.out.println("HTTP 400");
            System.out.println(e);
            return ("HTTP 400");
        }

    }

    @GetMapping(value = "/api/socks")
    public String getSocks(@RequestParam (value = "color") String color ,
                           @RequestParam (value="operation") String operation,
                           @RequestParam (value = "cottonPart") String cotton)
    {
        try {
            if (color.isEmpty() || operation.isEmpty() || cotton.isEmpty() || Integer.parseInt(cotton)>100 || Integer.parseInt(cotton)<0){
                throw new Exception("Empty variable");
            }
            try {
                if (operation.equals("moreThan")) {
                    return ((String.valueOf(socksRepo.findByCottonColorMore(color, cotton))));
                }

                if (operation.equals("equal")) {
                    return ((String.valueOf(socksRepo.findByCottonColorEqual(color, cotton))));

                }

                if (operation.equals("lessThan")) {
                    return ((String.valueOf(socksRepo.findByCottonColorLess(color, cotton))));

                }

                else {
                    throw new Exception("Нет значения сравнения");
                }


            } catch (Exception e){
                System.out.println(e);
                return ("HTTP 500");
            }

        } catch (Exception e){
            System.out.println(e);
            return ("HTTP 400");
        }

    }

}
