package clientTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.ArrayList;

import socks.SocksPack;

public class ClientStatus {
		
	public static void main(String[] args) {
		
		try {
			
			HttpClient client = HttpClient.newHttpClient();
			
			ArrayList<SocksPack> list = new ArrayList<SocksPack>();
			ArrayList<String> opsList = new ArrayList<String>();
			
			opsList.add(socks.DatabaseProc.DB_OPS.get(0));
			opsList.add(socks.DatabaseProc.DB_OPS.get(1));
			opsList.add(socks.DatabaseProc.DB_OPS.get(2));
			
			//list.add(new SocksPack("blue", 40, 0));
			//list.add(new SocksPack("red", 30, 0));
			list.add(new SocksPack("black", 20, 0));
			//list.add(new SocksPack("error", 101, 0));
			
			for(SocksPack socks: list) {
				for(String op: opsList) {
				
					String url = "http://localhost:8080/api/socks?" +
							"color=" + socks.getColor() + "&" +
							"operation=" + op + "&" +
							"cottonPart=" + socks.getCottonPart();
					
					System.out.println("Client URL: " + url);
					
					HttpRequest request = HttpRequest.newBuilder()
			        		.uri(URI.create(url)).GET()
			                .timeout(Duration.ofSeconds(2))
			                .build();
					
					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
					
					System.out.println(response.statusCode());
					System.out.println(response.body());
					System.out.println();
				}
			}
		} catch (HttpTimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
