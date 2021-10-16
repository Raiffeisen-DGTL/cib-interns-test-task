package cib.test.cibtest.implservice;

import cib.test.cibtest.enums.EnumForError;
import cib.test.cibtest.enums.OperationEnum;
import cib.test.cibtest.exceptions.excclass.DatabaseException;
import cib.test.cibtest.exceptions.excclass.UrlException;
import cib.test.cibtest.model.Sock;
import cib.test.cibtest.repository.Repository;
import cib.test.cibtest.service.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Service extends CheckService implements ApiOperation {
    private final Repository repository;

    @Transactional
    @Override
    public Long getByColorAndCottonPart(@NonNull String color, @NonNull OperationEnum operation, @NonNull Long cottonPart) throws UrlException, DatabaseException {
        super.checkCottonPart(cottonPart);
        if(operation.equals(OperationEnum.equal)) {
            return repository.getSocksByColorAndCottonPartEquals(color,cottonPart).get().getQuantity();
        }
        if(operation.equals(OperationEnum.lessThan)){
            return super.countQuantity(repository.getSocksByColorAndCottonPartLessThan(color,cottonPart));
        }
        if(operation.equals(OperationEnum.moreThan)){
            return super.countQuantity(repository.getSocksByColorAndCottonPartIsGreaterThan(color,cottonPart));
        }
        throw new DatabaseException(EnumForError.NullResultForRequest);
    }


    @Override
    @Transactional
    public void addSocks(@NonNull Sock sock) throws UrlException {
        super.fullCheck(sock);
        if (!repository.getSocksByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).isPresent()) {
            repository.save(sock);
        } else {
            Sock ourSock = repository.getSocksByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).get();
            ourSock.setQuantity(sock.getQuantity() + ourSock.getQuantity());
            repository.save(ourSock);
        }
    }

    @Override
    @Transactional
    public void reduceSocks(@NonNull Sock sock) throws UrlException {
        super.fullCheck(sock);
        if (repository.getSocksByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).isPresent()) {
            Sock ourSock = repository.getSocksByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).get();
            super.checkTwoQuantity(sock.getQuantity(), ourSock.getQuantity());
            long quantity = ourSock.getQuantity() - sock.getQuantity();
            if (quantity != 0) { // если количество носков 0 - логично удалить эту позицию из базы данных, так как api запроса на put всё равно создаст "новый" носок
                ourSock.setQuantity(quantity);
                repository.save(ourSock);
            } else {
                repository.delete(ourSock);
            }
        } else {
            throw new UrlException(EnumForError.IncorrectURL);
        }
    }
}

