package cib.test.cibtest.controller;

import cib.test.cibtest.enums.OperationEnum;
import cib.test.cibtest.exceptions.excclass.DatabaseException;
import cib.test.cibtest.exceptions.excclass.UrlException;
import cib.test.cibtest.model.Sock;
import cib.test.cibtest.service.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final ApiOperation apiOperation;

    @GetMapping(value = "api/socks")
    public Long getSocksByColorAndCottonPart(@RequestParam(value = "color") final String color,
                                             @RequestParam(value = "operation") final OperationEnum operation,
                                             @RequestParam(value = "cottonPart") final Long cottonPart) throws UrlException, DatabaseException {
        return apiOperation.getByColorAndCottonPart(color, operation, cottonPart);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "api/socks/income", method = {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_XML_VALUE)
    public void addSocks(@RequestBody Sock sock) throws UrlException {
        apiOperation.addSocks(sock);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "api/socks/outcome", method = {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_XML_VALUE)
    public void reduceSocks(@RequestBody Sock sock) throws UrlException {
        apiOperation.reduceSocks(sock);
    }
}