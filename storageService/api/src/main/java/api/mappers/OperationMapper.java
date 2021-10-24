package api.mappers;

import api.dto.OperationDTO;
import model.enums.Operation;
import org.springframework.web.bind.MissingServletRequestParameterException;


public class OperationMapper {

    public static Operation map(OperationDTO operationDTO) throws MissingServletRequestParameterException {
        switch (operationDTO) {
            case moreThan: return Operation.moreThan;
            case lessThan: return Operation.lessThan;
            case equal: return Operation.equal;
            default: throw new MissingServletRequestParameterException("Operation", "Operation");
        }
    }
}
