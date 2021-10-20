package org.RF;


import org.RF.POJO.Socks;
import org.RF.repos.SocksRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/socks")
public class RESTController {
    @Autowired
    private SocksRepos socksRepos;
    @GetMapping()
    public @ResponseBody
    ResponseEntity get(@RequestParam("color") String color,
                       @RequestParam("operation") String operation,
                       @RequestParam("cottonPart") int cottonPart){
        Optional<Integer> socks=null;
        switch (operation) {
            case "moreThan":
                socks = socksRepos.sumQuantityByColorAndCottonPartGreaterThan(color,cottonPart);
                break;
            case "lessThan":
                socks = socksRepos.sumQuantityByColorAndCottonPartLessThan(color,cottonPart);
                break;
            case "equal":
                socks = socksRepos.sumQuantityByColorAndCottonPartEquals(color, cottonPart);
                break;
        }
        return ResponseEntity.ok(socks);
    }
    @PostMapping("outcome")
    public ResponseEntity postSocksOutcome(@Valid @RequestBody Socks socks){
        return postFindoutcome(socks);
    }


    @PostMapping("income")
    public ResponseEntity  postSocksIncome(@Valid @RequestBody Socks socks){
        return postFindIncome(socks);
    }

    public ResponseEntity postFindIncome(Socks socks){
        Socks sock=socksRepos.findByColorAndCottonPart(socks.getColor(),socks.getCottonPart());
       if(sock == null) {
          socksRepos.save(socks);
          return ResponseEntity.ok().build();
       }
        else {sock.setQuantity(sock.getQuantity()+socks.getQuantity());
        socksRepos.save(sock);}
        return ResponseEntity.ok().build();
    }

    public ResponseEntity postFindoutcome(Socks socks){
        Socks sock=socksRepos.findByColorAndCottonPart(socks.getColor(),socks.getCottonPart());
        if(sock == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else if(sock.getQuantity()-socks.getQuantity()>0)
            {sock.setQuantity(sock.getQuantity()-socks.getQuantity());
            socksRepos.save(sock);}
            else if(sock.getQuantity()-socks.getQuantity()==0)socksRepos.delete(sock);
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok().build();
    }
}
