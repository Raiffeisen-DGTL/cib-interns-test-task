package org.example.accounting_of_soks;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/api/socks")
public class SocksController {

    private static boolean checking_input_parameters(String operation, int cottonPart){

        String[] operations = new String[] { "moreThan", "lessThan", "equal"};
        return (cottonPart >= 0 && cottonPart <= 100) && (Arrays.asList(operations).contains(operation));
    }

    private static boolean checking_input_parameters(int cottonPart, int quantity){

        return (cottonPart >= 0 && cottonPart <= 100) && (quantity > 0);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> show_information(@RequestParam("color") String color,
                                                       @RequestParam("operation") String operation,
                                                       @RequestParam("cottonPart") Integer cottonPart) {

        if (checking_input_parameters(operation, cottonPart))
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Integer.toString(PairsOfSocksDAO.show_information(color, operation, cottonPart)));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("invalid input");
    }

    @PostMapping(value = "/income",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> show_income(@RequestBody PairsOfSocks pairs) {

        if (checking_input_parameters(pairs.getCottonPart(), pairs.getQuantity())){
            PairsOfSocksDAO.load_income(pairs.getColor(), pairs.getCottonPart(), pairs.getQuantity());
            return ResponseEntity.status(HttpStatus.OK).body("Successfully");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
    }

    @PostMapping(value = "/outcome",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> show_outcome(@RequestBody PairsOfSocks pairs) {

        if (checking_input_parameters(pairs.getCottonPart(), pairs.getQuantity())){

            switch (PairsOfSocksDAO.load_outcome(pairs.getColor(), pairs.getCottonPart(), pairs.getQuantity())){
                case -1:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to send more than there is");
                case 0:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("These pairs of socks are not in stock");
                case 1:
                    return ResponseEntity.status(HttpStatus.OK).body("Successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
    }
}