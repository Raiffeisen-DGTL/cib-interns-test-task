package socks.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import socks.SocksPack;
import socks.SocksSrv;
import socks.DatabaseProc;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;


public class StatusHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) {
		
		if (!exchange.getRequestMethod().equals("GET")) { //Wrong request parameters
			
			SocksSrv.response(exchange, 400,
					"StatusHandler.WARN: Request parameters error".getBytes()); //Parameters error

		} else {
			
			String query = URLDecoder.decode(exchange.getRequestURI().getQuery(), StandardCharsets.UTF_8);
			String [] pairs = query.split("&");
			
			if(pairs.length != 3) {
				SocksSrv.response(exchange, 400,
						"IncomeHandler.WARN: Wrong parameter count".getBytes()); //Parameters error
			} else {
				
				SocksPack socksPackConstraints = new SocksPack();
				String compareOp = null;
				boolean paramErr = false;
				
				for(int i = 0; i < pairs.length; i++) { //Query pairs parsing
					
					String [] pair = pairs[i].split("=");
					
					if(i == 0) { //Socks color
						if(!pair[0].equals("color") || pair[1].isBlank() || (pair[1].length() > 255)) {
							paramErr = true;
						} else {
							socksPackConstraints.setColor(pair[1]);
						}
					} else if(i == 1) { //Socks GET constraint
						if(!pair[0].equals("operation") || !DatabaseProc.DB_OPS.contains(pair[1])) {
							paramErr = true;
						} else {
							compareOp = pair[1];
						}
					} else {
						if(!pair[0].equals("cottonPart")) {
							paramErr = true;
						} else {
							try {
								
								socksPackConstraints.setCottonPart(Integer.parseInt(pair[1]));
								
								socksPackConstraints.setQuantity(1);
								paramErr = !socksPackConstraints.check(); //Validate values 
								
							} catch (NumberFormatException e) {
								paramErr = false;
						    }
						}
					}
				}
				
				if(paramErr) {
					SocksSrv.response(exchange, 400,
							"IncomeHandler.WARN: Wrong parameter count".getBytes()); //Parameters error
				} else { //Process request finally
					
					int sum = 0;
					
					try {
						sum = DatabaseProc.getAllSocks(socksPackConstraints, compareOp);
					} catch (SQLException e) {
						SocksSrv.response(exchange, 500, "IncomeHandler.ERROR: Database error".getBytes()); //Database error
						e.printStackTrace();
					}
					
					SocksSrv.response(exchange, 200,
							Integer.valueOf(sum).toString().getBytes());
					
				}
				
			}			
		}
	}

}
