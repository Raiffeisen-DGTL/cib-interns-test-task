package clientTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.ArrayList;

import socks.SocksPack;

public class ClientOutcome {
		
	public static void main(String[] args) {
		
		try {
			
			HttpClient client = HttpClient.newHttpClient();
			
			ArrayList<SocksPack> list = new ArrayList<SocksPack>();
			
			list.add(new SocksPack("blue", 40, 1));
			list.add(new SocksPack("red", 30, 1));
			list.add(new SocksPack("black", 40, 1));
			list.add(new SocksPack("error", 101, 1));
			
			for(SocksPack socks: list) {
				
				System.out.println("Client JSON: " + socks);
				
				HttpRequest request = HttpRequest.newBuilder()
		        		.uri(URI.create("http://localhost:8080/api/socks/outcome"))
		                .POST(BodyPublishers.ofByteArray(socks.toString().getBytes()))
		                .timeout(Duration.ofSeconds(2))
		                .build();
				
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				
				System.out.println(response.statusCode());
				System.out.println(response.body());
				System.out.println();
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
