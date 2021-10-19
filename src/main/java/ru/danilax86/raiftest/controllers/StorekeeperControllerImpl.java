package ru.danilax86.raiftest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.danilax86.raiftest.models.Socks;
import ru.danilax86.raiftest.services.SocksService;
import ru.danilax86.raiftest.utils.IncorrectInputException;
import ru.danilax86.raiftest.utils.LessThanZeroException;

@RestController
@RequestMapping("/api/socks")
public class StorekeeperControllerImpl implements StorekeeperController {

	@Autowired
	private SocksService socksService;

	@Override
	public ResponseEntity<String> addSocks(Socks socks) {
		try {
			socksService.add(socks);
			return ResponseEntity.status(HttpStatus.OK).body("удалось добавить приход");
		} catch (LessThanZeroException | IncorrectInputException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("параметры запроса отсутствуют или имеют некорректный формат");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("произошла ошибка, не зависящая от вызывающей стороны");
		}
	}

	@Override
	public ResponseEntity<String> removeSocks(Socks socks) {
		try {
			socksService.remove(socks);
			return ResponseEntity.status(HttpStatus.OK).body("удалось отнять приход");
		} catch (LessThanZeroException | IncorrectInputException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("параметры запроса отсутствуют или имеют некорректный формат");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("произошла ошибка, не зависящая от вызывающей стороны");
		}
	}

	@Override
	public ResponseEntity<String> getSocks(String color, String operation, int cottonPart) {
		try {
			String result = socksService.getSocks(color, operation, cottonPart);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (LessThanZeroException | IncorrectInputException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("параметры запроса отсутствуют или имеют некорректный формат");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("произошла ошибка, не зависящая от вызывающей стороны");
		}
	}
}
