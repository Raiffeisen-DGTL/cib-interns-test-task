package ru.danilax86.raiftest.services;

import ru.danilax86.raiftest.models.Socks;
import ru.danilax86.raiftest.utils.IncorrectInputException;
import ru.danilax86.raiftest.utils.LessThanZeroException;

public interface SocksService {

	void add(Socks socks) throws IncorrectInputException, LessThanZeroException;

	void remove(Socks socks) throws LessThanZeroException, IncorrectInputException;

	String getSocks(String color, String operation, int cottonPart) throws IncorrectInputException, LessThanZeroException;

}
