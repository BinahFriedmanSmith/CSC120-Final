import java.util.HashMap;


public class Room{
 
    //adjacent rooms
    private final HashMap<String, Room> exits;

    public String description = "This is a room.";
    private String name = "a room";

    //Items in the room. can be picked up
    private final HashMap<String, Item> contents;
    
    //Items in the room that can be interacted with, but not picked up
    private final HashMap<String, Item> furniture = new HashMap<>();

    //things in the room that cannot be interacted with, only observed
    private final HashMap<String, String> scenery = new HashMap<>();

    private boolean beenVisited = false;

    /**
     * constructor
     */
    public Room(){
        contents = new HashMap<>();
        exits = new HashMap<>();
    }

    public Room(String name, String desc){
        contents = new HashMap<>();
        exits = new HashMap<>();
        this.name = name;
        this.description = desc;
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

    //checks if an item is in thhe room (contents only, for picking up things)
    public boolean itemHere(String item){
        return contents.containsKey(item);
    }

    public Item getItemHere(String item){
        return contents.get(item);
    }

    //checks if an item is in thhe room (furniture only)
    public boolean interactableHere(String item){
        return furniture.containsKey(item);
    }

    public Item getInteractableHere(String item){
        return furniture.get(item);
    }

    //checks if an item is in thhe room (contents, furniture, or scenery)
    public boolean visibleItemHere(String item){
        return contents.containsKey(item) || furniture.containsKey(item) || scenery.containsKey(item);
    }

    //returns the description for a visible item
    public String viewVisibleItem(String item){
        if (itemHere(item)){
            return getItemHere(item).getDescription();
        }
        if (furniture.containsKey(item)){
            return furniture.get(item).getDescription();
        }
        if (scenery.containsKey(item)){
            return scenery.get(item);
        }
        return null;
    }

    public void addFurniture(Item item){
        if (furniture.containsValue(item)){
            throw new RuntimeException("" + item + " is already in this room.");
        }     
        else {
            furniture.put(item.toString(), item);
        }
    }

    public void addScenery(String name, String desc){
        if (scenery.containsKey(name)){
            throw new RuntimeException("" + name + " is already in this room.");
        }     
        else {
            scenery.put(name, desc);
        }
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
        return description;
    }

    public void printContents(){
        if (contents.isEmpty()){           
        }
        else{
            System.out.print("You can see: ");
            for ( String item : contents.keySet()) {
                System.out.print(item + ", ");
            }
            System.out.println();
        }
    }

    public void printExits(){
        if (exits.isEmpty()){
        }
        else{
            System.out.print("You can go: ");
            for ( String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();
        }
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
