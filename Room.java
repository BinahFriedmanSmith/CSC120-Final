import java.util.HashMap;


public class Room{

    //adjacent rooms
    private HashMap<String, Room> exits;

    public String description = "This is a room.";
    private final String name = "a room";

    private final HashMap<String, Item> contents;

    private boolean beenVisited = false;

    /**
     * constructor
     */
    public Room(){
        contents = new HashMap<>();
        exits = new HashMap<>();
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    /**
     * Adds an item to the room, if it is not already there.
     * @param item the item to remove
     */

    public void addItem(Item item){
        if (contents.containsValue(item)){
            throw new RuntimeException("" + item + " is already in this room.");
        }     
        else {
            contents.put(item.toString(), item);
        }
    }
    
    /**
     * Removes an item from the room, if it is present
     * @param item the item to remove
     * @return the removed item
     */
    public Item removeItem(Item item){
        if (!contents.containsValue(item)){
            throw new RuntimeException("" + item + " is not in this room.");
        }     
        else {
            contents.remove(item.toString());
            return item;
        }        
    }

    public Item removeItem(String item){
        if (!contents.containsKey(item)){
            throw new RuntimeException("" + item + " is not in this room.");
        }     
        else {       
            return contents.remove(item);
        }        
    }

     /**
     * checks if an item is present in the room
     * @param item the item to check
     * @return whether the item is present
     */
    public boolean itemHere(Item item){
        return contents.containsValue(item);
    }

    public boolean itemHere(String item){
        return contents.containsKey(item);
    }

    public Item getItemHere(String item){
        return contents.get(item);
    }

    /**
     * returns the room located in the given direction
     * @param direction the direction to check
     * @return the room in that direction, or null if there is no room there/the direction is invalid
     */
    public Room getDirection(String direction){
        
        if (exits.containsKey(direction))
        {
            return exits.get(direction);
        }
        return null;
        
    }

    public void addExit(Room room, String direction){
        exits.put(direction,room);
    }
    
    public void removeExit(String direction){
        exits.remove(direction);
    }
    public void removeExit(Room room, String direction){
        exits.remove(direction,room);
    }


    /**
     * tostring override
     * @return the room object's individual description & any items present
     */
    @Override
    public String toString(){
        return description + "\nYou can see: " + contents;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasBeenVisited() {
        return beenVisited;
    }

    public void visit() {
        this.beenVisited = true;
    }
}
