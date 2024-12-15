

import java.util.HashMap;

public class Player{

    private final HashMap<String, Item> inventory; //keeps track of held items
    
    private Room location; //room the player is in

    private String description = "Nothing out of the ordinary."; //physical description of the character

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
     * second constructor
     * @param startingRoom the room the character will be in when they are created
     * @param desc physical description of the character
     */
    public Player(Room startingRoom, String desc){
        inventory = new HashMap<>();
        location = startingRoom;
        description = desc;
    }

    
    /**
     * Adds an item to the character's inventory, if the item is present, the character has inventtory space, and they are not already holding it.
     * @param item the item to add
     */
    
    public void grab(String item){
        if (inventory.containsKey(item)){
            System.out.println("You are already holding the " + item + ".");
        }
        else if (location.itemHere(item)){
            System.out.println("You pick up the " + item + ".");
            inventory.put(item, location.removeItem(item));
        }     
        else if (location.visibleItemHere(item)){
            System.out.println("You can't take that! (" + item + ")");
        }
        else {
            System.out.println("You don't see that here.");
        }
    }


    
    /**
     * Removes an item from the character's inventory and puts it in their current room location, if they are holding it.
     * @param item the item to remove
     * @return the removed item
     */
    
    public Item drop(String item){
        if (!isHolding(item)){
            System.out.println("You are not holding a " + item + ".");
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
     * @param item the name of the item to examine
     */    
    public void examine(String item){
        if (item.equals("me") || item.equals("body")|| item.equals("self")){
            System.out.println(description);
            if (isWearingCollar){
                System.out.println("A golden COLLAR covered in strange runes is locked around your neck. You can TOUCH it.");
            }
        }
        else if (location.visibleItemHere(item) || isHolding(item)){          
            if (isHolding(item)){
                System.out.println(inventory.get(item).getDescription() + " (You are holding this item.)");
            }
            else{
                System.out.println(location.viewVisibleItem(item));
            }
        }
        else {
            System.out.println("You don't see that here. (" + item + ")");
        }
    }

    /**
     * Destroys a held item
     * @param item the name of the item to destroy
     */
    public void destroy(String item){
        if (inventory.containsKey(item)){
            inventory.remove(item);          
        }     
    }

    /**
     * Calls the use() method of an avaliable Item.
     * @param item the name of the item to use
     */
    public void use(String item){     
       if (location.interactableHere(item)){
            location.getInteractableHere(item).use(this);
       }
       else if (isHolding(item)){
            inventory.get(item).use(this);
       }
       else if (location.itemHere(item)){
            System.out.println("Try picking it up first!");
       }
       else{
        System.out.println("You don't see that here. (" + item + ")");
       }
    }
    
    /**
     * Checks if this player's inventory contains an Item assigned to an inputted key 
     * @param item the name of the key
     * @return whether this player's inventory contains an Item assigned to key "item"
     */
    public boolean isHolding(String item){
        return inventory.containsKey(item);
    }   
    /**
     * Checks if this player's inventory contains an inputted Item
     * @param item the item to check for
     * @return whether this player's inventory contains the Item "item"
     */
    public boolean isHolding(Item item){
        return inventory.containsValue(item);
    }

    /**
     * prints the character's current inventory
     */
    public void checkInventory(){
        System.out.println("You are carrying: " + inventory);
    }
 
    /**
     * prints a description of the room the player is in, as well as grabbable objects and exits, if there are any
     */
    public void look(){
        System.out.println("You are in " + location.getName());
        System.out.println(location);
        location.printContents();
        location.printExits();
    }
    
    /**
     * returns the player's current location 
     * @return the player's current location 
     */
    public Room getLocation() {
        return location;
    }
    /**
     * sets the player's current location 
     * @param room the room to set the player's current location to
     */
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
            if (!location.hasBeenVisited()){
                look();
                location.visit();
            }
            else{
                System.out.println("You are in " + location.getName());
            }            
            return true;
        }      
    }

    /**
     * Checks if the player is wearing the bodyswap collar
     * @return True if the player is wearing the collar, false if not
     */
    public boolean isWearingCollar() {
        return isWearingCollar;
    }

    /**
     * sets isWearingCollar to true
     */
    public void wearCollar(){
        isWearingCollar = true;
    }

}
