package joke;

import java.io.IOException;

import java.net.URI;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;

import javax.swing.*;
import java.awt.event.*;

public class Joke extends JFrame {
	public static String getJoke() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://icanhazdadjoke.com/")).header("Accept", "text/plain").build();
		
		HttpResponse<String> response = client
			     .send(request, BodyHandlers.ofString());
		
//		System.out.println("Response Body: " + response.body());
		
		return response.body();
	}
	
	public static void main(String[] args) {
		// UI Element Settings
		int windowWidth = 900;
		int windowHeight = 700;
		
		int buttonHeight = 50;
		int buttonWidth = 300;
		
		// Horizontally center button
		int buttonPosX = (windowWidth / 2) - (buttonWidth / 2);
		int buttonPosY = 600;
		
		// Initilize UI Classes
		JFrame mainWindow = new JFrame();
		JButton newJokeBtn = new JButton("Tell me a joke!");
		JTextField jokeDisplay = new JTextField();
		
		// Add an action listener to the button
		newJokeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					jokeDisplay.setText(getJoke());
				} catch(IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		// UI Settings
		newJokeBtn.setBounds(buttonPosX, buttonPosY, buttonWidth, buttonHeight);
		jokeDisplay.setBounds(0, 0, 900, 500);
		mainWindow.setSize(windowWidth, windowHeight);
		mainWindow.setTitle("Joke Generator (Java Edition)");
		
		// Add elements to window
		mainWindow.add(newJokeBtn);
		mainWindow.add(jokeDisplay);
		
		mainWindow.setLayout(null);
		mainWindow.setVisible(true);
		
	}
}
