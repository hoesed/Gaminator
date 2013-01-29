package at.fhj.games.battleships;

import at.fhj.swd.games.Game;

/**
* Main Class for game Battleships
* Class implements interface Game and GameEnging to step through the game
* @author mmussner, dfrech
*/
public class Battleships implements Game {
    
    // Indicates if game is finished
    private boolean finished = false;
    
    // Init player 1
    Board p1 = new Board(1,"Player 1");
    
    // Init player 2
    Board p2 = new Board(2,"Player 2");
    
    // Current player
    private int currentPlayer = 1;
    
    // Start game
    @Override
    public String start()
    {
        // Fill player boards with character that indicates no shoot, no hit, no ship
        p1.initMap(p1.shipMap," ");
        p1.initMap(p1.shootMap," ");
        
        p2.initMap(p2.shipMap," ");
        p2.initMap(p2.shootMap," ");
           
        // Place aircraft carriers
        p1.placeShip(p1.shipMap,"b8","a");
        p1.placeShip(p1.shipMap,"c8","a");
        p1.placeShip(p1.shipMap,"d8","a");
        p1.placeShip(p1.shipMap,"e8","a");
        p1.placeShip(p1.shipMap,"f8","a");
        
        p2.placeShip(p2.shipMap,"b1","a");
        p2.placeShip(p2.shipMap,"b2","a");
        p2.placeShip(p2.shipMap,"b3","a");
        p2.placeShip(p2.shipMap,"b4","a");
        p2.placeShip(p2.shipMap,"b5","a"); 

        // Place battleships
        p1.placeShip(p1.shipMap,"d1","b");
        p1.placeShip(p1.shipMap,"d2","b");
        p1.placeShip(p1.shipMap,"d3","b");
        p1.placeShip(p1.shipMap,"d4","b");
        
        p2.placeShip(p2.shipMap,"h5","b");
        p2.placeShip(p2.shipMap,"h6","b");
        p2.placeShip(p2.shipMap,"h7","b");
        p2.placeShip(p2.shipMap,"h8","b"); 
                
        // Place cruiser
        p1.placeShip(p1.shipMap,"e3","c");
        p1.placeShip(p1.shipMap,"f3","c");
        p1.placeShip(p1.shipMap,"g3","c");
        
        p2.placeShip(p2.shipMap,"e2","c");
        p2.placeShip(p2.shipMap,"f2","c");
        p2.placeShip(p2.shipMap,"g2","c");
        
        // Place destroyer
        p1.placeShip(p1.shipMap,"a5","d");
        p1.placeShip(p1.shipMap,"b5","d");
        
        p2.placeShip(p2.shipMap,"d6","d");
        p2.placeShip(p2.shipMap,"d7","d");
               
        // Print help and draw boards
        String s = this.getHelp() + 
        //p1.drawBoard(p1.playerName, p1.shipMap, "ships") +
        p2.drawBoard(p2.playerName, p2.shootMap, "shoot") + "\n\n" +
        p1.playerName + ": Please enter the coordinates for your first shot (eg \"d4\")";

        //return String
        return s;
    }

    
    // Action after each user input
    @Override
    public String next(String inp) 
    {
        // Print seperation line to last move
        System.out.println("======================================================");
        
        // Check if input is valid
        if (Util.checkInput(inp) && p2.getDetail(p2.shootMap, inp).equals(" ")) {
           
            // Check if ship has been hit
            if(p2.checkHit(inp)){
                
                // Variable declaration
                int count[];                
                String sunkMsg;
                String hitShip;
                
                // get type of hit ship
                //hitShip = p1.getShip(p1.shipMap, inp);
                hitShip = p2.getDetail(p2.shipMap, inp);
                
                // mark hit ship on board
                //p1.markHit(inp);
                p2.markHit(inp);
                
                // Draw board
                //System.out.print(p1.drawBoard(p1.playerName, p1.shipMap, "ships"));
                System.out.println(p2.drawBoard(p2.playerName, p2.shootMap, "shoot"));
                System.out.println("");
                
                // Count number of remaining ships
                //count = p1.countShips(p1.shipMap);
                count = p2.countShips(p2.shipMap);
                
                // Finish game if all ships have been destroyed
                if (count[0] == 0) {
                    this.finished = true;
                    return "Congratulations, you have destroyed all of your opponents ships!";
                }
                
                // Generat output if a whole ship has been sunk
                if (hitShip.equals("a") && count[1] == 0)
                    {sunkMsg = "\nFANTASTIC! You sunk the aircraft carrier!";}
                
                else if (hitShip.equals("b") && count[2] == 0)
                    {sunkMsg = "\nYESSSS! You sunk the battleship!";}
                
                else if (hitShip.equals("c") && count[3] == 0)
                    {sunkMsg = "\nBLUB BLUB! You sunk the cruiser!";}
                
                else if (hitShip.equals("d") && count[4] == 0)
                    {sunkMsg = "\nKAWOOM! You sunk the destroyer!";}
                
                else {sunkMsg ="";}
                
                // Return message that the ship has been hit
                return "BOOOOOM - You hit a ship!" + sunkMsg 
                        + "\n\nPlease enter the coordinates for your next shot (eg \"d4\")";
                
                
            } 
            // Action if no ship has been hit
            else {
                // Mark missed field
                p2.markMiss(inp);
                
                // Draw board
                //System.out.print(p1.drawBoard(p1.playerName, p1.shipMap, "ships"));
                System.out.println(p2.drawBoard(p2.playerName, p2.shootMap, "shoot"));
                System.out.println("");  
                
                // Return that no ship has been hit
                return "Oh no, you missed! There is no ship at this location."
                        + "\n\nPlease enter the coordinates for your next shot (eg \"d4\")";
            }       
        }
        // Tell the user that the input has been wrong
        else {
            // Output if a position has been selected, that was shot at before
            if (!p2.getDetail(p2.shootMap, inp).equals(" ")) {
                return "You have shot at this location already!"
                        + "Please try another position.";
            }
            // Output if user input had wrong format
            else {
                return "Wrong input!"+
                       "\nPlease try again and use coordinates like f3 for your shoot.";
            }
        }
        
    }

    // Not used
    @Override
    public String showSolution()
    {
        return "";
    }

    // Returns the name of the game
    @Override
    public String getName()
    {
        return "Battleships";
    }
    
    // Description of game at the start
    public String getHelp()
    {
        String s = "\nWelcome to Battleships! \n\n" +
                "The following ships have  been placed on the board of your oponent:\n" +
                "   1 Aircraft Carrier (5 fields)\n"+
                "   1 Battleship (4 fields)\n"+
                "   1 Cruiser (3 fields)\n"+
                "   1 Destroyers (2 fields)\n\n" +
                "Enter a position that you want to hit and destroy your opponents ships as \n"+
                "quickly as possible.\n";
        
       return s;
    }
    
    // Finishes the game
    @Override
    public boolean isFinished()
    {
        return this.finished;
    }
    
    // Exit message
    @Override
    public String stop()
    {
        // Nothing to do here
        return  "Thank you for playing Battleships!";
    }

}
