import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    
    //List of available players to switch between, and pointer to current player
    private static Player currentPlayer;
    private static ArrayList<Player> players;

    //progress flags
    private static boolean openedFirstDoor;
    private static boolean tookCage;
    private static boolean tookCollar;


    @SuppressWarnings("ConvertToTryWithResources") //because it's annoying
    public static void main(String[] args) {

        // This is a "flag" to let us know when the loop should end
        boolean stillPlaying = true;

        // We'll use this to get input from the user.
        Scanner userInput = new Scanner(System.in);

        // Storage for user's responses
        String userResponse;

        // runs setup, which creates the world and everthing in it
        setup();


        // This could be replaced with a more interesting opening 
        System.out.println("WELCOME TO MY GAME \nEnter \"commands\" for a list of commands.\n\n");
        currentPlayer.look();


        // The do...while structure means we execute the body of the loop once before checking the stopping condition
        do {
            // ************************************************
            // The stuff that happens in your game will go here
            //  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓
            
            userResponse = userInput.nextLine().toLowerCase();
            parse(userResponse);
            progressChecks();

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
        Room room1 = new Room("a green room", "The walls here are painted a soft grass green. A LEVER is built into the wall next to a locked door to the east. In the middle of the room is a pedestal, upon which sits a gilded cage.");
        Room room2 = new Room("a blue room", "The walls here are painted a soothing baby blue. In the middle of the room is a pedestal holding a golden collar.");
        Room room3 = new Room("a pink room", "The walls here are painted a subtle pastel pink.");

        
       
        //create "small" rooms for mouse
        //Room sRoom1 = new Room();
        //Room sRoom2 = new Room();

        //create the cage
        Cage cage = new Cage("cage", "A golden cage, designed for a rodent. Each side of the cage is fitted with a miniature doorway, held open or closed by an unknown mechanism.",new Room("a gilded cage", "The surrounding bars are thin and dense. The floor is covered in wood shavings. Each side of the cage is fitted with a miniature doorway, held open or closed by an unknown mechanism."));

        //create passages between roomsuse
        
        room2.addExit(room3, "east");
        room3.addExit(room2, "west");

        //add the player characters
        players = new ArrayList<>();
        players.add(new Player(room1));
        room1.visit(); //since the player character starts here it is "visited" by default
        players.add(new Player(cage.getInterior()));
        cage.getInterior().visit();
        currentPlayer = players.get(0);
        cage.setMouse(players.get(1));
        players.get(1).wearCollar();

        //add contents
        room1.addItem(cage);
        room2.addItem(new Item("collar", "A golden collar, sized for a human. It is engraved with strange runes. You can WEAR it."));

        //add furniture
        room1.addFurniture(new Lever("lever", "A mechanical lever in a deep green. It is solidly built into the wall. You can USE it.", room1, new Doorway("east", "west", room2)));

        //add scenery
        room1.addScenery("pedestal", "A marbled pillar in faint green. Upon closer inspection, it seems to be made not of stone but a dense plastic.");
        room2.addScenery("pedestal", "A marbled pillar in faint blue. Upon closer inspection, it seems to be made not of stone but a dense plastic.");
    }

    private static void parse(String input) {
        //special object interactions
        if (input.equals("touch collar") & currentPlayer.isWearingCollar()){
            swapPlayer();
        }
        else if ((input.equals("wear collar") || input.equals("use collar")) & currentPlayer.isHolding("collar")){
            System.out.println("You snap the collar around your neck. As you TOUCH it, something seems to happen... \n Reality shifts, and you find yourself in a different place, in a different body.");
            currentPlayer.destroy("collar");
            currentPlayer.wearCollar();
            currentPlayer = players.get( (players.indexOf(currentPlayer) + 1) % players.size() );
        }
        //observation
        else if (input.equals("look")){
            currentPlayer.look();
        }
        else if (input.startsWith("x ")){
            currentPlayer.examine(input.substring(2));
        }
        else if (input.startsWith("look at ") || input.startsWith("examine ")){
            currentPlayer.examine(input.substring(8));
        }
        else if (input.startsWith("look ")){
            currentPlayer.examine(input.substring(5));
        }
        else if (input.equals("inventory") || input.equals("i")){
            currentPlayer.checkInventory();
        }
        //movement
        else if (input.startsWith("go ")){
            currentPlayer.walk(input.substring(3));
        }
            //directional shortcuts
        else if (input.equals("north") || input.equals("n")){
            currentPlayer.walk("north");
        }
        else if (input.equals("south") || input.equals("s")){
            currentPlayer.walk("south");
        }
        else if (input.equals("east") || input.equals("e")){
            currentPlayer.walk("east");
        }
        else if (input.equals("west") || input.equals("w")){
            currentPlayer.walk("west");
        }
        //object interaction
        else if (input.startsWith("take ")){
            currentPlayer.grab(input.substring(5));
        }
        else if (input.startsWith("drop ")){
            currentPlayer.drop(input.substring(5));
        }
        else if (input.startsWith("use ")){
            currentPlayer.use(input.substring(4));
        }
        //Meta information
        else if (input.equals("commands")){
            commands();
        }
        else {
            System.out.println("I can't " + input);
        }
    }

    private static void swapPlayer(){
        currentPlayer = players.get( (players.indexOf(currentPlayer) + 1) % players.size() );
        System.out.println("You touch the golden collar around your throat. Reality shifts, and you find yourself in a different place, in a different body.");
    }

    private static void progressChecks(){
        if(!openedFirstDoor && currentPlayer.getLocation().getDirection("east") != null){
            currentPlayer.getLocation().setDescription(currentPlayer.getLocation().getDescription().replaceAll("a locked", "an open"));
        }
        if(!tookCage && currentPlayer.isHolding("cage")){
            currentPlayer.getLocation().setDescription(currentPlayer.getLocation().getDescription().replaceAll("pedestal, upon which sits a gilded cage", "marbled pedestal"));
        }
        if(!tookCollar && currentPlayer.isHolding("collar")){
            currentPlayer.getLocation().setDescription(currentPlayer.getLocation().getDescription().replaceAll("pedestal, upon which sits a gilded cage", "marbled pedestal"));
        }

    }

    public static void commands(){
        System.out.println("COMMANDS:");
        System.out.println("inventory");
        System.out.println("look");
        System.out.println("go [direction]");
        System.out.println("look [item] / look at [item]");
        System.out.println("take [item]");
        System.out.println("drop [item]");
        System.out.println("use [item]");

    }

    public static void help() {
        System.out.println("This is a text adventure game. This game is played by typing commands into the text input, like you have just done. Use these inputted commands to tell your player character what to do."); 
        System.out.println("As this game operates on a very simple parser, having been designed entirely by one college freshman, your options are limited.");
        System.out.println("For a list of example commands, type \"commands\"");

        System.out.println("Some other general hints: \n-Important items and actions will often be CAPITALIZED.\n-If you're stuck, try LOOKing AT things around you. Descriptions will often include what you can do with things.");
    }

}
