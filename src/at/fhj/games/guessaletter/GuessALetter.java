package at.fhj.games.guessaletter;

import java.util.Random;

import at.fhj.swd.games.Game;

/**
 * Ein Spiel wo man einen Buchstaben erraten muss
 * 
 * @author gue
 */
public class GuessALetter implements Game
{
	private char myLetter;
	private boolean finished = false;
	@Override
	public String start()
	{
		Random r = new java.util.Random();
		this.myLetter = (char) (r.nextInt(26) + 'a');
		return this.getHelp();
	}

	@Override
	public String stop()
	{
		// Nothing to do here
		return  "Thank you for playing Guess-a-number.\n" +
				"Make a donation at paypal for me !";
	}

	@Override
	public String next(String inp) 
	{
		if(inp.length() == 0)
		{
			return "You must enter a letter !";
		}
		else if (inp.length() > 1)
		{
			return "Only one letter is allowed, you entered " + inp.length();
		}
		
		if(inp.equalsIgnoreCase(String.valueOf(this.myLetter)))
		{
			this.finished = true;
			return "Bingo !";
		}
		else
		{
			this.finished = false;
			return "Nope, try again !";
		}
	}

	@Override
	public String showSolution()
	{
		return "The letter was " + String.valueOf(this.myLetter);
	}

	@Override
	public String getName()
	{
		return "Guess-A-Letter";
	}

	public String getHelp()
	{
		return  "Welcome to Guess-a-letter. \n" +
				"The rules are simple: You have to guess the\n" +
				"letter that i am thinking of (a-z).\n" +
				"Good Luck !\n";
	}

	@Override
	public boolean isFinished()
	{
		return this.finished;
	}
}
