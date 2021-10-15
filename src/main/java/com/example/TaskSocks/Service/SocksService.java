package com.example.TaskSocks.Service;
import com.example.TaskSocks.Exeptions.ErrorOperationExeption;
import com.example.TaskSocks.Model.Socks;
import com.example.TaskSocks.Repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocksService {

 @Autowired
    SocksRepository socksRepository;

    public int result(String color,int cottonPart,String operation) throws ErrorOperationExeption {
        switch(operation) {
            case "moreThan": return socksRepository.findMoreSocks(color,cottonPart);
            case "lessThan": return socksRepository.findLessSocks(color,cottonPart);
            case "equal": return socksRepository.findEqualSocks(color,cottonPart);
            default: throw new ErrorOperationExeption("Bad Request");
        }
    }

    public Socks add(Socks socks) throws ErrorOperationExeption {
        if (socks.getQuantity()>0) {
            Socks foundSocks=socksRepository.findSocks(socks.getColor(),socks.getCotton_part());
            if(foundSocks!=null) {
                foundSocks.setQuantity(foundSocks.getQuantity()+socks.getQuantity());
                socksRepository.save(foundSocks);
                return socksRepository.save(foundSocks);
            }
            else {
                socksRepository.save(socks);
                return  socksRepository.save(socks);
            }
        }
        else
            throw new ErrorOperationExeption("Bad Request");
    }
    public Socks remove(Socks socks) throws ErrorOperationExeption {
        if (socks.getQuantity()>0) {
            Socks foundSocks=socksRepository.findSocks(socks.getColor(),socks.getCotton_part());
            if(foundSocks!=null) {
                if(foundSocks.getQuantity()<socks.getQuantity())
                    foundSocks.setQuantity(0);
                else
                    foundSocks.setQuantity(foundSocks.getQuantity()-socks.getQuantity());
                socksRepository.save(foundSocks);
                return socksRepository.save(foundSocks);
            }
            else
                throw  new ErrorOperationExeption("No such item found");
        }
        else
            throw new ErrorOperationExeption("Bad Request");
    }

}
