package socks;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import socks.handlers.IncomeHandler;
import socks.handlers.OutcomeHandler;
import socks.handlers.StatusHandler;

public class SocksSrv {
	
	public static void response(HttpExchange exchange, int rCode, byte[] resp) {
		
		try {
			
			exchange.sendResponseHeaders(rCode, resp.length);
			
			OutputStream os = exchange.getResponseBody();
			os.write(resp);
			os.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]) {
		
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 20);
			server.createContext("/api/socks/income", new IncomeHandler());
			server.createContext("/api/socks/outcome", new  OutcomeHandler());
			server.createContext("/api/socks", new  StatusHandler());
			
			server.setExecutor((ThreadPoolExecutor) Executors.newFixedThreadPool(10));
			server.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
