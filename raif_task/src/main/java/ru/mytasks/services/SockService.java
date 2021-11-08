package ru.mytasks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.mytasks.dao.ColorsDAO;
import ru.mytasks.dao.SocksDAO;
import ru.mytasks.exceptions.SocksValidateExceptions;
import ru.mytasks.models.Sock;

@Service
public class SockService {
	private final ColorsDAO colorsDAO;
	private final SocksDAO socksDAO;
	
	@Autowired
	public SockService(ColorsDAO colorsDAO, SocksDAO sockDAO) {
		this.colorsDAO = colorsDAO;
		this.socksDAO = sockDAO;
	}
	
	public Sock income(Sock sock) {
		Sock newSock;
		
		validateSock(sock);
		newSock = socksDAO.getSock(sock);
		
		if (newSock == null) {
			newSock = sock;
			socksDAO.addSock(newSock);
		} else {
			newSock.setQuantity(newSock.getQuantity() + sock.getQuantity());
			validateSock(newSock);
			socksDAO.saveSockQuantity(newSock);
		}
		
		return newSock;
	}
	
	public Sock outcome(Sock sock) {
		Sock foundedSock;
		
		validateSock(sock);
		foundedSock = socksDAO.getSock(sock);
		
		if (foundedSock != null) {
			foundedSock.setQuantity(foundedSock.getQuantity() - sock.getQuantity());
			validateQuantity(foundedSock.getQuantity());
			socksDAO.saveSockQuantity(foundedSock);
		} else {
			throw new SocksValidateExceptions("Носков с выбранным сочетанием цвета и качества на складе нет");
		}
		
		return foundedSock;
	}

	public int getQuantitySocks (String color, String operation, int cottonPart) {
		int quantity = 0;
		validateColor(color);
		validateCottonPart(cottonPart);
		
		if (operation.equals("equal")) {
			quantity = socksDAO.getQuantityEqual(color, cottonPart);
		} else if (operation.equals("moreThan")) {
			quantity = socksDAO.getQuantityMoreThan(color, cottonPart);
		} else if (operation.equals("lessThan")) {
			quantity = socksDAO.getQuantityLessThan(color, cottonPart);
		} else {
			throw new SocksValidateExceptions("Не корректная операция");
		}
		
		return quantity;
	}
	
	private void validateColor(String color) {
		if (!colorsDAO.contains(color)) {
			throw new SocksValidateExceptions("Цвет должен быть из списка");
		}
	}
	
	private void validateCottonPart(int cottonPart) {
		if(cottonPart < 0 || cottonPart > 100) {
			throw new SocksValidateExceptions("Неерное значение cottonPart");
		}
	}
	
	private void validateQuantity(int quantity) {
		if (quantity < 0 ) {
			throw new SocksValidateExceptions("Не достаточно остатков для проведения операции");
		}
	}
	
	private void validateSock(Sock sock) {
		validateColor(sock.getColor());
		validateCottonPart(sock.getCottonPart());
		validateQuantity(sock.getQuantity());
	}
}
