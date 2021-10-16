package cib.test.cibtest.service;

import cib.test.cibtest.enums.OperationEnum;
import cib.test.cibtest.exceptions.excclass.DatabaseException;
import cib.test.cibtest.exceptions.excclass.UrlException;
import cib.test.cibtest.model.Sock;
import lombok.NonNull;

public interface ApiOperation {
    Long getByColorAndCottonPart(@NonNull String color, @NonNull OperationEnum operation, @NonNull Long cottonPart) throws UrlException, DatabaseException;
        void addSocks(@NonNull Sock sock) throws UrlException;
        void reduceSocks(@NonNull Sock sock) throws UrlException;

}
