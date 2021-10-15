package joha.ch.raif.controller;


import joha.ch.raif.domain.Sock;
import joha.ch.raif.repo.SockRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/socks")
public class SocksController {

    @Autowired
    private final SockRepository sockRepository;

    public SocksController(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    @GetMapping
    public String list(@RequestParam(value = "color",defaultValue = "") String color,@RequestParam(value = "operation",defaultValue = "equal") String operation,@RequestParam(value = "cottonPart",defaultValue = "") String cottonPart) {
        int SocksCounter = 0;
        List<Sock> filter = new ArrayList<>();
        List<Sock> requestSocks = new ArrayList<>();
        List<Sock> allSocks = sockRepository.findAll();

//        поиск всех носков (/socks)
        if(color.equals("")&&cottonPart.equals("")){
            for(Sock result : sockRepository.findAll()){
                SocksCounter+=result.getQuantity()*2;
            }
        }

//        фильтрация только по хлопку (всех цветов /socks?operation=...&cottonPart=... или /socks?cottonPart=... "operation default=equal")
        if(color.equals("")){
            allSocks.stream().forEach(x->filter.add(x));
        }

//        1:фильтрация по цвету с последующей фильтрацией по хлопку(socks?color=...&operation=....&cottonPart=...)  2:фильтрация только по цвету с любым количество хлопка(/socks?color=...)
        if(!color.equals("")){
            allSocks.stream().filter(x-> x.getColor().equals(color)).forEach(x-> filter.add(x));
           if (cottonPart.equals("")){
               filter.stream().forEach(x->requestSocks.add(x));
           }
        }

//        фильтрация по operation(default=equal для поиска без operation /socks?cottonPart=....), когда cottonPart!=null.
        if(!cottonPart.equals("")) {
            switch (operation) {
                case("moreThan") :
                    filter.stream().filter(x -> Integer.parseInt(x.getCottonPart()) > Integer.parseInt(cottonPart)).forEach(x -> requestSocks.add(x));
                    break;
                case("lessThan") :
                    filter.stream().filter(x -> Integer.parseInt(x.getCottonPart()) < Integer.parseInt(cottonPart)).forEach(x -> requestSocks.add(x));
                    break;
                case("equal")    :
                    filter.stream().filter(x -> Integer.parseInt(x.getCottonPart()) == Integer.parseInt(cottonPart)).forEach(x -> requestSocks.add(x));
                    break;
            }
        }

        for(Sock result : requestSocks){
            SocksCounter+=result.getQuantity()*2;
        }
        return String.valueOf(SocksCounter);

    }

    @PostMapping("/income")
    public Sock income(@RequestBody Sock sock) {
        Sock sockFromDb = sockRepository.findByColorAndCottonPart(sock.getColor(),sock.getCottonPart());
        if (sockFromDb!=null){
            sockFromDb.setQuantity(sock.getQuantity()+sockFromDb.getQuantity());
            sockRepository.save(sockFromDb);
        } else {
            sockRepository.save(sock);
        }
        return sock;
    }

    @PostMapping("/outcome")
    public Sock outcome(@RequestBody Sock sock) {
        Sock sockFromDb = sockRepository.findByColorAndCottonPart(sock.getColor(),sock.getCottonPart());
        if (sockFromDb!=null){
            sockFromDb.setQuantity(sockFromDb.getQuantity()-sock.getQuantity());
            sockRepository.save(sockFromDb);
        } else {
            sockRepository.save(sock);
        }
        return sock;
    }

}
