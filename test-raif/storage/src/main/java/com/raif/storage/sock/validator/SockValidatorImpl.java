package com.raif.storage.sock.validator;

import com.raif.storage.exception.SockValidationException;
import com.raif.storage.sock.model.Sock;
import com.raif.storage.sock.model.SockDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class SockValidatorImpl implements SockValidator {

    private static final int SOCK_COTTON_PART_MAX = 100;
    private static final int SOCK_COTTON_PART_MIN = 0;
    private static final Set<String> SOCK_VALID_OPERATIONS = Stream.of("moreThan", "lessThan", "equal")
            .collect(Collectors.toCollection(HashSet::new));

    @Override
    public void validateSockInPostRequest(SockDto sock) {
        log.debug("Validating sock {{}} in post request", sock);

        if (null == sock
                || null == sock.getColor()
                || sock.getCottonPart() < SOCK_COTTON_PART_MIN
                || sock.getCottonPart() > SOCK_COTTON_PART_MAX
                || sock.getQuantity() < 0) {

            log.error("Invalid sock {{}} in post request", sock);
            throw new SockValidationException("Invalid object passed in request");
        }
    }

    @Override
    public void validateGetRequestForSockCount(String color, String operation, int cottonPart) {
        log.debug("Validating get request for sock count: color={{}}, operation={{}}, cottonPart={{}}",
                color, operation, cottonPart);

        if (null == color
                || null == operation
                || !SOCK_VALID_OPERATIONS.contains(operation)
                || cottonPart < 0
                || cottonPart > 100) {

            log.error("Invalid get request for sock count: color={{}}, operation={{}}, cottonPart={{}}",
                    color, operation, cottonPart);
            throw new SockValidationException("Invalid parameters in get request for sock count");
        }
    }

    @Override
    public void validateSockForSaving(Sock sock) {
        log.debug("Validating sock {{}} for saving", sock);

        if (null == sock
                || null == sock.getColor()
                || sock.getCottonPart() < SOCK_COTTON_PART_MIN
                || sock.getCottonPart() > SOCK_COTTON_PART_MAX
                || sock.getQuantity() < 0) {

            log.error("Invalid sock {{}}, can not be saved", sock);
            throw new SockValidationException("Invalid object, can not be saved");
        }
    }
}
