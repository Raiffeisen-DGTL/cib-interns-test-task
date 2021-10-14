package com.example.raif_task.services;

import com.example.raif_task.Dao.interfaces.SocksDaoInterface;
import com.example.raif_task.Dto.SocksDTO;
import com.example.raif_task.entity.Socks;
import com.example.raif_task.services.interfaces.SocksServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocksService implements SocksServiceInterface {
    @Autowired
    private SocksDaoInterface socksDaoInterface;

    public ResponseEntity<Socks> save(Socks socks) {
        if (socks.getColor().equals("error")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!validation(socks)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Socks socksTemp = socksDaoInterface.getSocks(socks).get(0);
        if (socksTemp == null) {
            socksDaoInterface.save(socks);
        } else {
            socksTemp.setQuantity(socksTemp.getQuantity() + socks.getQuantity());
            socksDaoInterface.save(socksTemp);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Socks> update(Socks socks) {
        if (socks.getColor().equals("error")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!validation(socks)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Socks socksTemp = socksDaoInterface.getSocks(socks).get(0);
        if (socksTemp != null) {
            if (socksTemp.getQuantity() > socks.getQuantity()) {
                socksTemp.setQuantity(socksTemp.getQuantity() - socks.getQuantity());
                socksDaoInterface.save(socksTemp);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Socks>> get(SocksDTO socksDTO) {
        if (socksDTO.getColor().equals("error")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!validationDTO(socksDTO)) {
            System.out.println(111);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Socks> socksTemp = socksDaoInterface.getSocksByColor(socksDTO.getColor());
        if (socksTemp != null) {
            return new ResponseEntity<>(find(socksTemp, socksDTO), null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private List<Socks> find(List<Socks> socks, SocksDTO socksDTO) {
        List<Socks> list = new ArrayList<>();

        if (socksDTO.getOperation().equals("moreThan")) {
            for (Socks s : socks) {
                if (s.getCottonPart() > socksDTO.getCottonPart()) {
                    list.add(s);
                }
            }
        }
        if (socksDTO.getOperation().equals("lessThan")) {
            for (Socks s : socks) {
                if (s.getCottonPart() < socksDTO.getCottonPart()) {
                    list.add(s);
                }
            }
        }
        if (socksDTO.getOperation().equals("equal")) {
            for (Socks s : socks) {
                if (s.getCottonPart() == socksDTO.getCottonPart()) {
                    list.add(s);
                }
            }
        }
        return list;
    }

    private Boolean validationDTO(SocksDTO socksDTO) {
        boolean operation = socksDTO.getOperation().equals("moreThan")
                || socksDTO.getOperation().equals("lessThan")
                || socksDTO.getOperation().equals("equal");

        return socksDTO.getColor() != null
                && socksDTO.getCottonPart() > 0
                && socksDTO.getCottonPart() < 100
                && operation;
    }

    private Boolean validation(Socks socks) {
        return socks.getColor() != null
                && socks.getQuantity() > 0
                && socks.getCottonPart() > 0
                && socks.getCottonPart() < 100;
    }
}
