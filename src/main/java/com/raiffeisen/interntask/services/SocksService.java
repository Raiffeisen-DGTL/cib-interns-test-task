package com.raiffeisen.interntask.services;

import com.raiffeisen.interntask.classes.SocksOrder;
import com.raiffeisen.interntask.repos.ColorRepository;
import com.raiffeisen.interntask.repos.SocksOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

enum ResponseMessage {
    OK_CREATED("{\"code\":200,\"message\":\"Order was created\"}"),
    OK_UPDATED("{\"code\":200,\"message\":\"Order was updated\"}"),
    BAD_REQUEST("{\"code\":400,\"message\":\"Request is invalid\"}"),
    BAD_NOT_FOUND("{\"code\":400,\"message\":\"Order not found\"}"),
    BAD_QUANTITY("{\"code\":400,\"message\":\"Not enough quantity\"}");

    private final String data;

    ResponseMessage(String data) {
        this.data = data;
    }

    public String GetMessage(){
        return data;
    }

    public static String QuantityAnswer(Integer value){
        return "{\"code\":200,\"answer\":" + value +
                "}";
    }
}

@Service
@AllArgsConstructor
public class SocksService {

    private final SocksOrderRepository repository;
    private final ColorRepository colors;

    private boolean OrderIsNotValid(SocksOrder order) {
        return (order.getQuantity() < 1 || order.getCottonPart() < 0 || order.getCottonPart() > 100 || !colors.existsByName(order.getColor().getName()));
    }

    public ResponseEntity<String> incomeOrder(SocksOrder order){
        if(OrderIsNotValid(order))
            return new ResponseEntity<>( ResponseMessage.BAD_REQUEST.GetMessage(), HttpStatus.BAD_REQUEST);

        if(!repository.existsByColor_NameAndCottonPart(order.getColor().getName(), order.getCottonPart())) {
            repository.save(order);
            return new ResponseEntity<>( ResponseMessage.OK_CREATED.GetMessage(), HttpStatus.OK);
        }
        else {
            SocksOrder updated_order = repository.findByColor_NameAndCottonPart(order.getColor().getName(), order.getCottonPart());
            updated_order.setQuantity(updated_order.getQuantity() + order.getQuantity());
            repository.save(updated_order);
            return new ResponseEntity<>( ResponseMessage.OK_UPDATED.GetMessage(), HttpStatus.OK);
        }
    }

    public ResponseEntity<String> outcomeOrder(SocksOrder order){
        if(OrderIsNotValid(order))
            return new ResponseEntity<>( ResponseMessage.BAD_REQUEST.GetMessage(), HttpStatus.BAD_REQUEST);

        if(!repository.existsByColor_NameAndCottonPart(order.getColor().getName(), order.getCottonPart())) {
            return new ResponseEntity<>(ResponseMessage.BAD_NOT_FOUND.GetMessage(), HttpStatus.BAD_REQUEST);
        }
        else {
            SocksOrder updated_order = repository.findByColor_NameAndCottonPart(order.getColor().getName(), order.getCottonPart());
            if(updated_order.getQuantity() - order.getQuantity() >= 0) {
                updated_order.setQuantity(updated_order.getQuantity() - order.getQuantity());
                repository.save(updated_order);
                return new ResponseEntity<>( ResponseMessage.OK_UPDATED.GetMessage(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(ResponseMessage.BAD_QUANTITY.GetMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ResponseEntity<String> GetOrder(String color, String operation, String cPart){

        Short cottonPart;

        try {
            cottonPart = Short.parseShort(cPart);
        }
        catch (Exception e){
            return new ResponseEntity<>(ResponseMessage.BAD_REQUEST.GetMessage(), HttpStatus.BAD_REQUEST);
        }

        if(!colors.existsByName(color) || cottonPart < 0 || cottonPart > 100)
            return new ResponseEntity<>(ResponseMessage.BAD_REQUEST.GetMessage(), HttpStatus.BAD_REQUEST);

        List<SocksOrder> orders = repository.findAllByColor_Name(color);

        Integer answer = 0;
        switch (operation) {
            case "equal":
                for (SocksOrder order:
                     orders) {
                    if(Objects.equals(cottonPart, order.getCottonPart()))
                        answer += order.getQuantity();
                }
                break;
            case "moreThan":
                for (SocksOrder order:
                        orders) {
                    if(cottonPart < order.getCottonPart())
                        answer += order.getQuantity();
                }
                break;
            case "lessThan":
                for (SocksOrder order:
                        orders) {
                    if(cottonPart > order.getCottonPart())
                        answer += order.getQuantity();
                }
                break;
            default:
                return new ResponseEntity<>(ResponseMessage.BAD_REQUEST.GetMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ResponseMessage.QuantityAnswer(answer), HttpStatus.OK);
    }
}
