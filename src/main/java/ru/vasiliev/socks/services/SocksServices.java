package ru.vasiliev.socks.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.vasiliev.socks.Error.SocksError;
import ru.vasiliev.socks.repository.Socks;
import ru.vasiliev.socks.repository.SocksRepository;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class SocksServices {

    private final SocksRepository socksRepository;

    public List<Socks> getSocks(String color, Integer cottonPath, String operation){
        List<Socks> socks = new ArrayList<>();
        for (Socks s:socksRepository.findAll()) {
            if( color.equals( s.getColor() ) ) {
                switch (operation){
                    case "moreThan" :{
                        if(cottonPath<s.getCottonPath()){
                            socks.add(s);
                        }
                        break; }
                    case "lessThan" :{
                        if(cottonPath>s.getCottonPath()){
                            socks.add(s);
                        }
                        break; }
                    case "equal" :{
                        if(cottonPath==s.getCottonPath()){
                            socks.add(s);
                        }
                        break; }
                }
            }
        }
    return socks;}

    public Socks income(Socks socks){
        if( check(socks.getColor()) == true){
            throw new SocksError();
        }
        for (Socks s:socksRepository.findAll()){
            if( (socks.getColor().equals(s.getColor())) && (socks.getCottonPath().equals(s.getCottonPath())) ){
                s.setQuantity(socks.getQuantity() + s.getQuantity());
                return socksRepository.save(s);
            }
        }
        return socksRepository.save(socks); }

    public void outcome(Socks socks){
        for (Socks s:socksRepository.findAll()){
            if( socks.getColor().equals( s.getColor() ) && socks.getCottonPath().equals( s.getCottonPath() ) ){
                if(s.getQuantity()>socks.getQuantity()) {
                    s.setQuantity(s.getQuantity() - socks.getQuantity());
                    socksRepository.save(s);
                }
                else if (s.getQuantity()==socks.getQuantity()){
                    socksRepository.delete(s);
                }
                else{
                    throw new SocksError();
                }
            }
        }
        throw new SocksError();
    }

    public boolean check(String s){
        try{
            double a = Double.parseDouble(s);
        }
        catch (NumberFormatException | NullPointerException e){
            return false;
        }
        return true;
    }

}
