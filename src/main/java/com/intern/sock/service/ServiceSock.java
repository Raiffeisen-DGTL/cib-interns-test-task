package com.intern.sock.service;
import com.intern.sock.enums.ErrorEnum;
import com.intern.sock.enums.OperationEnum;
import com.intern.sock.exceptions.DatabaseException;
import com.intern.sock.exceptions.UrlException;
import com.intern.sock.implservice.CheckService;
import com.intern.sock.model.Sock;
import com.intern.sock.repository.Repository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ServiceSock extends CheckService {
    @Autowired
    Repository repo;

    @Transactional
    public Long getByColorAndCottonPart(@NonNull String color, @NonNull OperationEnum operation, @NonNull Long cottonPart) throws UrlException, DatabaseException {
        super.checkCottonPart(cottonPart);
        if(operation.equals(OperationEnum.equal)){
            return repo.getSockByColorAndCottonPartEquals(color,cottonPart).get().getQuantity();
        }else if(operation.equals(OperationEnum.moreThan)){
            return super.countQuantity(repo.getSockByColorAndCottonPartIsGreaterThan(color,cottonPart));
        }else if(operation.equals(OperationEnum.lessThan)){
            return super.countQuantity(repo.getSocksByColorAndCottonPartLessThan(color,cottonPart));
        }
        throw new DatabaseException(ErrorEnum.NullResultForRequest);
    }

    @Transactional
    public void addSock(@NonNull Sock sock) throws UrlException {
        super.allCheck(sock);
        if(!repo.getSockByColorAndCottonPartEquals(sock.getColor(),sock.getCottonPart()).isPresent()){
            repo.save(sock);
        }else{
            Sock mySock = repo.getSockByColorAndCottonPartEquals(sock.getColor(),sock.getCottonPart()).get();
            mySock.setQuantity(sock.getQuantity()+mySock.getQuantity());
            repo.save(mySock);
        }
    }

    @Transactional
    public void deleteSock(@NonNull Sock sock) throws UrlException {
        super.allCheck(sock);
        if(repo.getSockByColorAndCottonPartEquals(sock.getColor(),sock.getCottonPart()).isPresent()){
            Sock mySock = repo.getSockByColorAndCottonPartEquals(sock.getColor(),sock.getCottonPart()).get();
            super.checkTwoQuantity(sock.getQuantity(),mySock.getQuantity());
            Long quantity = mySock.getQuantity() - sock.getQuantity();
            if(quantity != 0){
                mySock.setQuantity(quantity);
                repo.save(mySock);
            }else{
                repo.delete(mySock);
            }
        }else{
            throw new UrlException(ErrorEnum.IncorrectURL);
        }
    }
}
