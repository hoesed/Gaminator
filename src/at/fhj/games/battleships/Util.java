package at.fhj.games.battleships;

/**
 * Class for utilities for game battleships
 * @author dfrech
 */
public class Util {

     /**
     * Validates if the input is valid
     * @param in string
     */
    public static boolean checkInput(String in) {
        if (in.matches("[a-h][1-8]")) {
            return (true);
        } else {
            return (false);
        }
    }
    
}
