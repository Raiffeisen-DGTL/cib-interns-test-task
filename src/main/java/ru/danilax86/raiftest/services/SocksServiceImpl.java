package ru.danilax86.raiftest.services;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danilax86.raiftest.models.Color;
import ru.danilax86.raiftest.models.Socks;
import ru.danilax86.raiftest.utils.IncorrectInputException;
import ru.danilax86.raiftest.utils.LessThanZeroException;

import java.util.Locale;


@Service
public class SocksServiceImpl implements SocksService {

	@Autowired
	private SocksStorage socksStorage;

	@Override
	public void add(Socks socks) throws IncorrectInputException, LessThanZeroException {
		checkSocks(socks);

		Socks oldSocks = checkPresence(socks);
		if (oldSocks == null) {
			socksStorage.save(socks);
			return;
		}

		socks.setId(oldSocks.getId());
		socks.setQuantity(socks.getQuantity() + oldSocks.getQuantity());

		socksStorage.save(socks);
	}

	@Override
	public void remove(Socks socks) throws LessThanZeroException, IncorrectInputException {
		checkSocks(socks);

		Socks oldSocks = checkPresence(socks);
		if (oldSocks == null) {
			throw new IncorrectInputException("Таких носков не существует");
		}

		if (oldSocks.getQuantity() - socks.getQuantity() < 0) {
			throw new LessThanZeroException("Нельзя вычесть из меньшего количества большее");
		}

		socks.setId(oldSocks.getId());
		socks.setQuantity(oldSocks.getQuantity() - socks.getQuantity());

		socksStorage.save(socks);

	}

	@Override
	public String getSocks(String color, String operation, int cottonPart) throws IncorrectInputException, LessThanZeroException {
		int quantity = 0;

		checkColor(color);
		checkCotton(cottonPart);

		for (Socks sock : socksStorage.findByColor(color)) {
			if (operation.equals("moreThan") && sock.getCottonPart() > cottonPart) {
				quantity += sock.getQuantity();
			} else if (operation.equals("lessThan") && sock.getCottonPart() > cottonPart) {
				quantity += sock.getQuantity();
			}
		}

		return String.valueOf(quantity);
	}


	public Socks checkPresence(Socks socks) {
		Iterable<Socks> entities = socksStorage.findByColor(socks.getColor());

		if (entities == null) {
			return null;
		}

		for (Socks entity : entities) {
			if (entity.getCottonPart() == socks.getCottonPart()) {
				return entity;
			}
		}
		return null;
	}

	public void checkColor(String color) throws IncorrectInputException {
		try {
			if (!EnumUtils.isValidEnum(Color.class, color.toUpperCase(Locale.ROOT)))
				throw new IncorrectInputException("Введён неправильный цвет");
		} catch (NullPointerException e) {
			throw new IncorrectInputException("Введён неправильный цвет");
		}
	}

	public void checkCotton(int cotton) throws LessThanZeroException, IncorrectInputException {
		if (cotton < 0) {
			throw new LessThanZeroException("Значение хлопка не может быть меньше нуля");
		}
		if (cotton > 100) {
			throw new IncorrectInputException("Значение хлопка не может быть больше ста");
		}
	}

	public void checkQuantity(int quantity) throws LessThanZeroException {
		if (quantity < 0) {
			throw new LessThanZeroException("Количество пар носков не может быть меньше нуля");
		}
	}

	public void checkSocks(Socks socks) throws IncorrectInputException, LessThanZeroException {
		checkColor(socks.getColor());
		checkCotton(socks.getCottonPart());
		checkQuantity(socks.getQuantity());
	}
}
