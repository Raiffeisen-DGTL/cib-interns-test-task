package api.mappers;

import api.dto.OperationDTO;
import model.enums.Operation;
import org.springframework.web.bind.MissingServletRequestParameterException;


public class OperationMapper {

    public static Operation map(OperationDTO operationDTO) throws MissingServletRequestParameterException {
        switch (operationDTO.getOperation()) {
            case "moreThan": return Operation.MORE_THAN;
            case "lessThan": return Operation.LESS_THAN;
            case "equal": return Operation.EQUAL;
            default: throw new MissingServletRequestParameterException("Operation", "Operation");
        }
    }
}
