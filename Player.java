

import java.util.HashMap;

public class Player{

    private final HashMap<String, Item> inventory; //keeps track of held items
    
    private Room location; //room the player is in

    private boolean isWearingCollar; //tracks if player is wearing the collar that allows body swapping

    /**
     * Constructor
     * @param startingRoom the room the character will be in when they are created
     */
    public Player(Room startingRoom){
        inventory = new HashMap<>();
        location = startingRoom;
    }

    
    /**
     * Adds an item to the character's inventory, if the item is present, the character has inventtory space, and they are not already holding it.
     * @param item the item to add
     */
    
    public void grab(String item){
        if (inventory.containsKey(item)){
            System.out.println("You are already holding the " + item + ".");
        }
        else if (!location.itemHere(item)){
            System.out.println("You don't see that here.");
        }     
        else {
            System.out.println("You pick up the " + item + ".");
            inventory.put(item, location.removeItem(item));

        }
    }


    
    /**
     * Removes an item from the character's inventory, if they are holding it.
     * @param item the item to remove
     * @return the removed item
     */
    
    public Item drop(String item){
        if (!inventory.containsKey(item)){
            System.out.println("You are not holding the " + item + ".");
            return null;
        }     
        else {
            System.out.println("You drop the " + item + ".");
            location.addItem(inventory.get(item));
            return inventory.remove(item);  
        }        
       
    }

    /**
     * Prints a string that changes based on inputted item and gamestate
     * @param item the item to examine
     */    
    public void examine(String item){
        if (location.itemHere(item) || inventory.containsKey(item)){
            System.out.print("It's a(n) " + item + ". ");          
            if (inventory.containsKey(item)){
                System.out.print("You are holding it. ");
            }
            System.out.println();
        }
        else {
            System.out.println("You don't see that here.");
        }
    }

    /**
     * Uses an item. Items can only be used if they are being held.
     * @param item the item to use
     */
    public void use(Item item){
        if (!inventory.containsValue(item)){
            System.out.println("You are not holding the " + item + ".");         
        }     
        /*else {
            System.out.println("You use the " + item + ".");   ////fix fix
            inventory.remove(item);
        }        
        prevAction = "use";
        prevActionString = item;
        */
    }

    /**
     * prints the character's current inventory
     */
    public void checkInventory(){
        System.out.println("You are carrying: " + inventory);
    }

    public void look(){
        System.out.println("You are in" + location.getName());
        System.out.println(location);
    }

    private void setLocation(Room room){
        location = room;
    } 

    /**
     * Attempts to move to a room in the specified direction (north, south, east, or west)
     * @param direction the direction to move
     * @return whether the action was sucessful
     */
    public boolean walk(String direction){
        if (location.getDirection(direction) == null){
            System.out.println("You can't go that way! (" + direction + ")");
            return false;
        }
        else{
            System.out.println("You go "+ direction + ".");
            this.setLocation(location.getDirection(direction));
            if (location.hasBeenVisited()){
                look();
                location.visit();
            }            
            return true;
        }      
    }

    public boolean isWearingCollar() {
        return isWearingCollar;
    }
}
