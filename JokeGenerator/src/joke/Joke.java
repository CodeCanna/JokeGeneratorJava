package joke;

// Import Exception stuff
import java.io.IOException;

// Import HTTP stuff
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;

// Import UI stuff
import javax.swing.*;
import javax.swing.border.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

/**
 * This program pulls a random joke from icanhazdadjoke.com and parses it into the java UI class
 * to be displayed in a text field.
 *
 * This was written with the help of the Oracle javadocs.
 */
public class Joke extends JFrame {
	/*
	 * This function retrieves a joke from icanhazdadjoke.com
	 */
	public static String getJoke() throws IOException, InterruptedException {
		// Create out http client
		HttpClient client = HttpClient.newHttpClient();
		
		// Create our http request to be sent by client
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://icanhazdadjoke.com/"))
				.header("Accept", "text/plain").build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		// System.out.println("Response Body: " + response.body());
		
		// Return a random joke
		return response.body();
	}

	public static void main(String[] args) {
		// UI Element Settings
		int windowWidth = 1000;
		int windowHeight = 700;
		
		int jokeDisplayWidth = 1000;
		int jokeDisplayHeight = 500;

		int buttonHeight = 50;
		int buttonWidth = 300;

		// Horizontally center button
		int buttonPosX = (windowWidth / 2) - (buttonWidth / 2);
		int buttonPosY = 550;

		// Initialize UI Classes
		JFrame mainWindow = new JFrame();
		JButton newJokeBtn = new JButton("Tell me a joke!");
		JTextField jokeDisplay = new JTextField();

		// UI Settings
		newJokeBtn.setBounds(buttonPosX, buttonPosY, buttonWidth, buttonHeight);
		
		/* Joke Display Settings */
		jokeDisplay.setBounds(0, 0, jokeDisplayWidth, jokeDisplayHeight);
		jokeDisplay.setFont(new Font("Sans", Font.BOLD, 15));
		jokeDisplay.setBackground(Color.black);
		jokeDisplay.setForeground(Color.lightGray);
		jokeDisplay.setBorder(new EmptyBorder(100, 200, 100, 120));

		mainWindow.setSize(windowWidth, windowHeight);
		mainWindow.setTitle("Joke Generator (Java Edition)");

		// Add an action listener to the button
		newJokeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String joke = getJoke();
					jokeDisplay.setText(joke);
					System.out.println(joke.length());
					// TODO: Add these to an array?  Perhaps write to file?
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// Add elements to window
		mainWindow.add(newJokeBtn);
		mainWindow.add(jokeDisplay);
		
		// Show main window
		mainWindow.setLayout(null);
		mainWindow.setVisible(true);

	}
}