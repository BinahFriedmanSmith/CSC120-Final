import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    
        private static Player currentPlayer;

        private static ArrayList<Player> players;
    @SuppressWarnings("ConvertToTryWithResources") //because it's annoying
    public static void main(String[] args) {

        // This is a "flag" to let us know when the loop should end
        boolean stillPlaying = true;

        // We'll use this to get input from the user.
        Scanner userInput = new Scanner(System.in);

        // Storage for user's responses
        String userResponse = "";


        setup();

        // This could be replaced with a more interesting opening
        System.out.println("******************");
        System.out.println("WELCOME TO MY GAME");
        System.out.println("******************");


        // The do...while structure means we execute the body of the loop once before checking the stopping condition
        do {
            // ************************************************
            // The stuff that happens in your game will go here
            //  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓
            
            userResponse = userInput.nextLine().toLowerCase();
            parse(userResponse);

            // ***********************************************************************
            // And as the player interacts, you'll check to see if the game should end
            //  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓
            if (userResponse.equals("WIN") || userResponse.equals("LOSE")) {
                stillPlaying = false;
            }
        } while (stillPlaying);

        // Tidy up
        userInput.close();

    }

    private static void setup() {
        //create rooms for human
        Room room1 = new Room();
        Room room2 = new Room();
        Room room3 = new Room();

        //create "small" rooms for mouse
        Room cage = new Room();
        Room sRoom1 = new Room();
        Room sRoom2 = new Room();


        //create passages between rooms
        room1.addExit(room2, "east");
        room2.addExit(room1, "west");

        players = new ArrayList<>();
        players.add(new Player(room1));
        players.add(new Player(cage));
        currentPlayer = players.get(0);
    }

    private static void parse(String input) {
        if (input.equals("touch collar") & currentPlayer.isWearingCollar()){
            swapPlayer();
        }
        else if (input.equals("look")){
            currentPlayer.look();
        }
        else if (input.startsWith("go ")){
            currentPlayer.walk(input.substring(3));
        }
        else if (input.startsWith("take ")){
            currentPlayer.grab(input.substring(5));
        }
        else if (input.startsWith("drop ")){
            currentPlayer.drop(input.substring(5));
        }
        else if (input.equals("look")){
            currentPlayer.look();
        }
        else{
            System.out.println("I can't " + input);
        }
    }

    private static void swapPlayer(){
        
    }
}
