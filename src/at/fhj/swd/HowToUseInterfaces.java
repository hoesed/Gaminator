package at.fhj.swd;

import java.util.ArrayList;
import java.util.List;

import at.fhj.games.guessaletter.GuessALetter;
import at.fhj.games.guessanumber.GuessANumber;
import at.fhj.games.tictactoe.TicTacToe;
import at.fhj.swd.games.Game;

public class HowToUseInterfaces
{
	public static void main(String[] args)
	{
		// Jede lasse die das Interface implementiert ist ein Spiel
		Game g = new GuessALetter();
		System.out.println("g=" + g.getName());
		
		g = new GuessANumber();
		System.out.println("g=" + g.getName());
		
		g = new TicTacToe();
		System.out.println("g=" + g.getName());
		
		System.out.println("------------------------------------");
		
		// Und Spiele lassen sich auch in Collections speichern
		List<Game> myGames = new ArrayList<Game>();
		myGames.add(new GuessALetter());
		myGames.add(new GuessANumber());
		myGames.add(new TicTacToe());
		
		// Ja und diese lassen sich auch ausgeben
		for(int i = 0; i < myGames.size(); i++)
		{
			System.out.println("Game#" + i + " = " + myGames.get(i).getName());
		}
		
		// Jetzt muss nur noch eine Auswahl in die GameEngine programmiert werden
		// ... oder auch eine andere Gameengine ?!?

		// Hier ein Denkansatz:
		/*
		  mainmenu() {
		    while (true) {
		        printMainMenu();
		        choice = readInt();   // make sure it's an int
		        switch (choice) {
		            case 0: exit();
		            case 1: foo();
		            case 2: bar();
		            default: print("Wrong choice");
		        }
		    }
		}
		*/
		
	}
	
}
