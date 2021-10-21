package com.socks;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "invalid cottonPart value")
public class SocksServiceException extends RuntimeException{

}
