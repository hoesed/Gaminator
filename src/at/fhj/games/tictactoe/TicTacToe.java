package at.fhj.games.tictactoe;

import at.fhj.swd.games.Game;

/**
 * @author Hoesele & Ly-Kurtansky
 */

public class TicTacToe implements Game {

	// ====== Attribute ======
	private static final int reihen = 3;
	private static final int spalten = 3;
	private char[][] board = new char[reihen][spalten];
	private boolean finished = false;
	private char spieler1 = 'X';
	private char spieler2 = 'O';
	private static boolean spieler = false;
	private int anzahlZuege = 0;
	private int i;
	private int j;
	private String input;
	private static Spieler s1 = new Spieler();
	private static Spieler s2 = new Spieler();

	// ====== Konstruktor ======
	public TicTacToe() {
		// erstellt ein Board und fuellt die Felder mit einer laufenden Nummer
		int counter = 1;
		for (i = 0; i < reihen; i++) {
			for (j = 0; j < spalten; j++) {
				board[i][j] = Character.forDigit(counter++, 10);
			}
		}
	}

	// ====== Methoden ======
	@Override
	public String getName() {
		return "TicTacToe";
	}

	@Override
	public String start() {
		System.out.print("Name des 1. Spielers: ");
		s1.setName(s1.getPrompt());
		System.out.print("Name des 2. Spielers: ");
		s2.setName(s2.getPrompt());
		return getInfoZumSpiel() + drawBoard() + "\n" + toContinue();
	}

	// Das Board wird ausgegeben.
	public String drawBoard() {
		String r = "";
		for (i = 0; i < reihen; i++) {
			for (j = 0; j < spalten; j++) {
				r += "[" + board[i][j] + "]";
			}
			r += "\n";
		}
		return r;
	}

	@Override
	public String next(String inp) {
		String result = "";
		boolean belegteZahl = false;
		input = inp;

		try {
			int zahl = Integer.parseInt(inp);

			if (zahl < 1 || zahl > 9) {
				result += fehler();
			} else {
				for (i = 0; i < reihen; i++) {
					for (j = 0; j < spalten; j++) {
						// Wandelt Zahl in char um und ueberprueft ob sie noch vorhanden ist
						if (board[i][j] == inp.charAt(0)) {
							belegteZahl = true;
							// Setzt das Zeichen auf die gewaehlte Position
							if (!spieler) {
								board[i][j] = spieler1;
								spieler = true;
							} else {
								board[i][j] = spieler2;
								spieler = false;
							}
							anzahlZuege++;
						}
					}
				}
				// Falls die eingegebene Zahl bereits belegt ist, wird eine
				// Fehlermeldung ausgegeben.
				if (!belegteZahl) {
					result += fehler2();
				}
			}
			result += drawBoard() + "\n";

			// Status wird ermittelt
			if (checkGewinn()) {
				result += showSolution();
				this.finished = true;
			} else if (anzahlZuege == 9) {
				result += unentschieden();
				this.finished = true;
			} else {
				result += toContinue();
			}

		} catch (NumberFormatException ex) {
			if (inp.equals("ende") || inp.equals("exit") || inp.equals("end")) {
				this.finished = true;
			} else {
				result += fehler();
			}
		}
		return result;
	}

	public boolean checkGewinn() {
		// Ueberprueft drei in einer Reihe
		char marker = ' ';
		for (i = 0; i < reihen; i++) {
			for (j = 0; j < spalten; j++) {
				if (!Character.isLetter(board[i][j])) {
					break;
				}
				if (j == 0) {
					marker = board[i][j];
				} else if (marker != board[i][j]) {
					break;
				}
				if (j == 2) {
					return true;
				}
			}
		}

		// Ueberprueft drei in einer Spalte
		for (i = 0; i < reihen; i++) {
			marker = ' ';
			for (j = 0; j < spalten; j++) {
				if (!Character.isLetter(board[j][i])) {
					break;
				}
				if (j == 0) {
					marker = board[j][i];
				} else if (marker != board[j][i]) {
					break;
				}
				if (j == 2) {
					return true;
				}
			}
		}

		// Ueberprueft drei in einer Diagonale
		marker = board[0][0];
		if (Character.isLetter(marker) && board[1][1] == marker
				&& board[2][2] == marker) {
			return true;
		}
		marker = board[2][0];
		if (Character.isLetter(marker) && board[1][1] == marker
				&& board[0][2] == marker) {
			return true;
		}
		return false;
	}

	// Statusmeldungen
	private String getInfoZumSpiel() {
		return "\nEin klassisches, einfaches Zweipersonen-Strategiespiel: \n"
				+ "Beide Spieler markieren abwechselnd ihre Zeichen im Feld. \n"
				+ "Der Spieler, der als erstes drei seiner Zeichen in einer \n"
				+ "Reihe, Spalte oder Diagonale hat, gewinnt. \n"
				+ "Wenn allerdings alle Felder markiert sind und keiner drei \n"
				+ "in einer Reihe hat, endet das Spiel unentschieden. Viel Spass! \n"
				+ "(Um das Spiel vorzeitig zu beenden, gebe \"exit\" ein.)\n\n";
	}

	private String toContinue() {
		return (!spieler ? s1.getName() : s2.getName()) + ", waehle ein Feld: ";
	}

	private String fehler() {
		return "=== Fehler! === \n" + "So geht es nicht, "
				+ (!spieler ? s1.getName() : s2.getName()) + "!\n" + "\""
				+ input + "\" ist keine gueltige Zahl, versuche es nochmals.\n";
	}

	private String fehler2() {
		return "=== Fehler! === \n" + "Das geht nicht, "
				+ (!spieler ? s1.getName() : s2.getName()) + "!\n" + "Feld \""
				+ input + "\" ist bereits belegt, versuche es nochmals.\n";
	}

	private String unentschieden() {
		return "Uiiii... Das Spiel endet untschieden!";
	}

	@Override
	public String showSolution() {
		return "Gratulation! " + (!spieler ? s2.getName() : s1.getName())
				+ " hat gewonnen!";
	}

	@Override
	public String stop() {
		return "Bis zum naechsten TicTacToe-Spiel!";
	}

	@Override
	public boolean isFinished() {
		return this.finished;
	}

}
