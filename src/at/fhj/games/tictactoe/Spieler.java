package at.fhj.games.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Spieler erzeugen
 * @author Hoesele & Ly-Kurtansky
 */
public class Spieler {

	// ====== Attribute ======
	private String name;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));
	

	// ====== Methoden ======
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getPrompt() {
		String prompt = "";
		try {
			prompt = reader.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (prompt.equals("") || prompt.equals(" ")) {
			return "\"Will-anonym-bleiben\"";
		} else {
			return prompt;
		}
	}
}
