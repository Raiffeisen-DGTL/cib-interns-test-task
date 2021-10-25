package application.services.utils;

import lombok.NonNull;
import model.models.Sock;
import org.springframework.http.HttpStatus;
import security.exceptions.CustomException;

public class CheckCorrect {

    public static void checkCottonPart(int cottonPart) throws CustomException{
        if (cottonPart < 0 | cottonPart > 100) {
            throw new CustomException("Cotton part is not correct", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkColor(@NonNull String color) throws CustomException {}

    public static void checkQuantity(int quantity) throws CustomException{
        if (quantity <= 0) {
            throw new CustomException("Quantity is not correct", HttpStatus.BAD_REQUEST);
        }
    }

    public static void check(@NonNull Sock sock) throws CustomException{
        checkCottonPart(sock.getCottonPart());
        checkQuantity(sock.getQuantity());
        checkColor(sock.getColor());
    }

    public static void check(int cottonPart, @NonNull int quantity) throws CustomException{
        checkCottonPart(cottonPart);
        checkQuantity(quantity);
    }

}
