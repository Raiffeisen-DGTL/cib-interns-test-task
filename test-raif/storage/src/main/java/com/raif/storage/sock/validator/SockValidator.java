package com.raif.storage.sock.validator;

import com.raif.storage.sock.model.Sock;
import com.raif.storage.sock.model.SockDto;

public interface SockValidator {

    void validateSockInPostRequest(SockDto sock);

    void validateGetRequestForSockCount(String color, String operation, int cottonPart);

    void validateSockForSaving(Sock sock);
}
