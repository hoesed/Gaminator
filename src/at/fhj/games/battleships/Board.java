package at.fhj.games.battleships;

import java.util.*;

/**
 * Class Board to handle the boards of each player
 * @author mmussner, dfrech
 */
public class Board {

    // variable for player name and board ID
    public String playerName;
    public int boardID;
    
    // player maps
    Map<String,String> shootMap = new HashMap<>();
    Map<String,String> shipMap = new HashMap<>();
      
    
    /**
     * ctor
     * Each player has two boards, one for the ships, one for the shoots
     * @param boardID int, playerName String
     */
    
    public Board (int boardID, String playerName) {
        
        this.boardID = boardID;
        this.playerName = playerName;
        this.shootMap = new HashMap<>();
        this.shipMap = new HashMap<>();
    }
  
    
    /**
     * Fills each field of the board with the same character 
     * @param m Map, c String with fill character
     */
    public Map initMap (Map m, String c) {
        
        for (int i = 1; i < 9; i++) {
            m.put("a"+Integer.toString(i), c);
            m.put("b"+Integer.toString(i), c);
            m.put("c"+Integer.toString(i), c);
            m.put("d"+Integer.toString(i), c);
            m.put("e"+Integer.toString(i), c);
            m.put("f"+Integer.toString(i), c);
            m.put("g"+Integer.toString(i), c);
            m.put("h"+Integer.toString(i), c);
        }
        
        return m;
    
    }
    
     /**
     * Returns a string with the board of a given board array.
     * @param m Map, l legend to be printed below the board
     */
    public String drawBoard(String playerName, Map m, String legend) {

        String l;
        
        // Initializes correct legend
        if (legend.equalsIgnoreCase("shoot")) {
            l = "o Miss / x Hit";
        }
        else {
            l = "a Carrier / b Battleship / c cruiser / d destroyer\n";
        }
        
        // Builds board
        String s = "\nBoard of "+ playerName + ":\n" +
        "    |-------------------------------|\n"+
        "    | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |\n"+
        "|---|-------------------------------|\n"+  
        "| a | "+m.get("a1")+" | "+m.get("a2")+" | "+m.get("a3")+" | "+m.get("a4")+" | "+m.get("a5")+" | "+m.get("a6")+" | "+m.get("a7")+" | "+m.get("a8")+" |\n"+
        "|---|-------------------------------|\n"+
        "| b | "+m.get("b1")+" | "+m.get("b2")+" | "+m.get("b3")+" | "+m.get("b4")+" | "+m.get("b5")+" | "+m.get("b6")+" | "+m.get("b7")+" | "+m.get("b8")+" |\n"+
        "|---|-------------------------------|\n"+
        "| c | "+m.get("c1")+" | "+m.get("c2")+" | "+m.get("c3")+" | "+m.get("c4")+" | "+m.get("c5")+" | "+m.get("c6")+" | "+m.get("c7")+" | "+m.get("c8")+" |\n"+
        "|---|-------------------------------|\n"+
        "| d | "+m.get("d1")+" | "+m.get("d2")+" | "+m.get("d3")+" | "+m.get("d4")+" | "+m.get("d5")+" | "+m.get("d6")+" | "+m.get("d7")+" | "+m.get("d8")+" |\n"+
        "|---|-------------------------------|\n"+
        "| e | "+m.get("e1")+" | "+m.get("e2")+" | "+m.get("e3")+" | "+m.get("e4")+" | "+m.get("e5")+" | "+m.get("e6")+" | "+m.get("e7")+" | "+m.get("e8")+" |\n"+
        "|---|-------------------------------|\n"+
        "| f | "+m.get("f1")+" | "+m.get("f2")+" | "+m.get("f3")+" | "+m.get("f4")+" | "+m.get("f5")+" | "+m.get("f6")+" | "+m.get("f7")+" | "+m.get("f8")+" |\n"+
        "|---|-------------------------------|\n"+
        "| g | "+m.get("g1")+" | "+m.get("g2")+" | "+m.get("g3")+" | "+m.get("g4")+" | "+m.get("g5")+" | "+m.get("g6")+" | "+m.get("g7")+" | "+m.get("g8")+" |\n"+
        "|---|-------------------------------|\n"+
        "| h | "+m.get("h1")+" | "+m.get("h2")+" | "+m.get("h3")+" | "+m.get("h4")+" | "+m.get("h5")+" | "+m.get("h6")+" | "+m.get("h7")+" | "+m.get("h8")+" |\n"+
        "|---|-------------------------------|\n"+
        l;
    
        // return board
        return s;
    }
    
    
    /**
     * Checks if a shot has hit a ship
     * @param s
     * @return Boolean
     */
    public Boolean checkHit(String s) {
        
        if (shipMap.get(s).equals(" ")) {
            return (false);
        }
        else {
            return (true);
        }
    }  
    
    
    /**
     * Marks a hit on the players map
     * @param s input String
     */
    public void markHit(String s) {   
        shootMap.put(s, "x");            
        shipMap.put(s, "x");
    }   
    
     /**
     * Marks a miss on the players map
     * @param s input String
     */
    public void markMiss(String s) {   
        shootMap.put(s, "o");            
        shipMap.put(s, "o");
    } 
    
    
     /**
     * Counts the number of remaining fields with ships and returns the count in an Array
     * [0] Total number of fields with ships
     * [1] Aircraft carriers
     * [2] Battleships
     * [3] Cruiser
     * [4] Destroyer
     * @param map Map
     */
    public int[] countShips(Map map) {
        
        // Initialize variables
        int count[];
        count = new int[5];
        
        // Create iterator
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        
        // Loop through all fields and create count of ships
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            switch (entry.getValue()) {
                case "a":
                    count[0]++;
                    count[1]++;
                    break;
                case "b":
                    count[0]++;
                    count[2]++;
                    break;
                case "c":
                    count[0]++;
                    count[3]++;
                    break;
                case "d":
                    count[0]++;
                    count[4]++;
                    break;
            }
        }
        
        return count;
        
    }
      
    /**
     * Place ships on map
     * @param pos Position
     * @param ship Type of ship
     */
    public void placeShip (Map m, String pos, String ship) {
        m.put(pos, ship);
    }
    
    
     /**
     * Get detail for a position on the map
     * @param pos Position
     * @param m Map 
     */
    public String getDetail (Map m, String pos) {
        return m.get(pos).toString();
    }
}
