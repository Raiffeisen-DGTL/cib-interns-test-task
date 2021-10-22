package socks.handlers;

import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import socks.DatabaseProc;
import socks.SocksPack;
import socks.SocksSrv;

public class IncomeHandler implements HttpHandler {
	
	@Override
	public void handle(HttpExchange exchange) {
		
		if (!exchange.getRequestURI().toString().equals("/api/socks/income") ||
				!exchange.getRequestMethod().equals("POST")) { //Wrong request parameters
			
			SocksSrv.response(exchange, 400,
					"IncomeHandler.WARN: Request parameters error".getBytes()); //Parameters error

		} else {
									
			try {
				SocksPack socksPack = (new ObjectMapper()).readValue(
					exchange.getRequestBody().readAllBytes(), SocksPack.class);
				
				if (socksPack.check()) {
					
					try {
						DatabaseProc.incSocks(socksPack); //Process request finally
						SocksSrv.response(exchange, 200, "IncomeHandler.OK".getBytes()); //Processing request success
					} catch (SQLException e) {
						SocksSrv.response(exchange, 500, "IncomeHandler.ERROR: Database error".getBytes()); //Database error
						e.printStackTrace();
					}
					
				} else {
					SocksSrv.response(exchange, 400,
							"IncomeHandler.WARN: Socks pack parameters error".getBytes()); //Socks pack parameters error
				}
				
			} catch (IOException e) {
				SocksSrv.response(exchange, 400,
						"IncomeHandler.WARN: JSON format error".getBytes()); //JSON format error
				e.printStackTrace();
			}
		}
	}
}
