package at.fhj.swd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import at.fhj.games.guessaletter.GuessALetter;
import at.fhj.games.guessanumber.GuessANumber;
import at.fhj.games.tictactoe.TicTacToe;
import at.fhj.games.battleships.Battleships;
import at.fhj.swd.games.Game;

/**
 * Eine einfache Starterklasse 
 * @author Hutter
 */
public class GameEngine
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		// Spiel initialisieren
			//	Game g = new GuessALetter();
		//Game g = new GuessANumber();
		
		// und starten
			//play(g);
		System.out.println("============== ProgrammierProjekt ================");
		System.out.println("Spielesammlung:");
		
		// Jede Klasse die das Interface implementiert ist ein Spiel
		//Game g = new GuessALetter();
		//g = new GuessANumber();
		//g = new TicTacToe();
		
		// int gameNumber = 0;

		// Und Spiele lassen sich auch in Collections speichern
		List<Game> myGames = new ArrayList<Game>();
		myGames.add(new GuessALetter());
		myGames.add(new GuessANumber());
		myGames.add(new TicTacToe());
		myGames.add(new Battleships());
		
		// Ja und diese lassen sich auch ausgeben
		for(int i = 0; i < myGames.size(); i++)
		{
			System.out.println("Game#" + i + " = " + myGames.get(i).getName());
		}
		
		System.out.println("------------------------------------");
		System.out.print("Welches Spiel wollen Sie spielen? (0 - " + (myGames.size() - 1) + ")");
		System.out.println();
		
		for(int i = 1; i <= 10; i++) 
		{
			
			int gameNumber = -1;
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			if (scanner.hasNextInt()){
				gameNumber = scanner.nextInt();
			}
			
			//System.out.println(gameNumber);
				
			if(gameNumber == 0)
			{
				Game g = new GuessALetter();
				play(g);
			}
			if(gameNumber == 1)
			{
				Game g = new GuessANumber();
				play(g);
			}
			if(gameNumber == 2)
			{
				Game g = new TicTacToe();
				play(g);
			}
			if(gameNumber == 3)
			{
				Game g = new Battleships();
				play(g);
			}
			else
			{
				System.out.println("------------------------------------");
				for(int a = 0; a < myGames.size(); a++)
				{
					System.out.println("Game#" + a + " = " + myGames.get(a).getName());
				}
				System.out.println("Bitte geben Sie eine Zahl zwischen 0 und " + (myGames.size() - 1) + " ein");
			}
		}
	}

	/**
	 * Spielt das uebergebene Spiel. Diese Funktion darf nicht geaendert werden. 
	 * 
	 * 
	 * @param g zu spielendes Spiel
	 * @throws IOException
	 */
	public static void play(Game g) throws IOException
	{
		System.out.println("============== You are playing " + g.getName() + " ================");

		// Spiel starten und Text ausgeben
		System.out.println(g.start());

		// Von der Konsole wird immer Zeilenweise gelesen
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// Solange das Spiel nicht beendet ist	
		while(g.isFinished() == false)
		{
			System.out.println(g.next(in.readLine()));
		}

		// Spiel mitteilen dass es beendet wurde
		System.out.println(g.stop());
	}

}
