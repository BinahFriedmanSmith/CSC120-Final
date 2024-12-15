import java.util.HashMap;


public class Room{
 
    //adjacent rooms
    private final HashMap<String, Room> exits;

    public String description = "This is a room."; //long description of the room, including any scenery and furniture
    private String name = "a room"; //short summary name of the room

    //Items in the room. can be picked up
    private final HashMap<String, Item> contents;
    
    //Items in the room that can be interacted with, but not picked up
    private final HashMap<String, Item> furniture = new HashMap<>();

    //things in the room that cannot be interacted with, only observed
    private final HashMap<String, String> scenery = new HashMap<>();

    //keeps track of whether the room has been visited; rooms only print their full description on entrance the first time it is entered. 
    private boolean beenVisited = false;

    /**
     * constructor
     */
    public Room(){
        contents = new HashMap<>();
        exits = new HashMap<>();
    }

    /**
     * constructor with parameters
     * @param name short summary name of the room
     * @param desc long description of the room, including any scenery and furniture
     */
    public Room(String name, String desc){
        contents = new HashMap<>();
        exits = new HashMap<>();
        this.name = name;
        this.description = desc;
    }

    /**
     * returns the name of the room
     * @return the name of the room
     */
    public String getName(){
        return name;
    }

    /**
     * returns the room's description
     * @return the room's description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Adds an item to the room's contents, if it is not already there.
     * @param item the item to add
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

    /**
     * Removes an item from the room, if there is an item attatched to the inputted key
     * @param item the name of the item to remove
     * @return the removed item
     */
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

    /**
     * checks if an item is in the room (contents only, for picking up things)
     * @param item name/key attatched to Item
     * @return whether Item is there
     */
    public boolean itemHere(String item){
        return contents.containsKey(item);
    }

    /**
     * returns an item in the room (contents only, for picking up things)
     * @param item name/key attatched to Item
     * @return Item from contents with key 'item'
     */
    public Item getItemHere(String item){
        return contents.get(item);
    }

    /**
     * checks if an item is in the room (furniture only)
     * @param item name/key attatched to Item
     * @return whether Item is there
     */
    public boolean interactableHere(String item){
        return furniture.containsKey(item);
    }

    /**
     * returns an item in the room (furniture only)
     * @param item name/key attatched to Item
     * @return Item from furniture with key 'item'
     */
    public Item getInteractableHere(String item){
        return furniture.get(item);
    }

    /**
     * checks if an item is in the room (contents, furniture, or scenery)
     * @param item name/key attatched to Item
     * @return whether Item is there
     */ 
    public boolean visibleItemHere(String item){
        return contents.containsKey(item) || furniture.containsKey(item) || scenery.containsKey(item);
    }

    /**
    * returns the description for a visible item (contents, furniture, or scenery)
    * @param item name/key attatched to Item
    * @return the description for visile Item with key 'item'
    */ 
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

    /**
     * Adds an item to the room's furniture, if it is not already there.
     * @param item the item to add
     */
    public void addFurniture(Item item){
        if (furniture.containsValue(item)){
            throw new RuntimeException("" + item + " is already in this room.");
        }     
        else {
            furniture.put(item.toString(), item);
        }
    }

    /**
     * Adds an item to the room's scenery, if it is not already there.
     * @param item the item to add
     */
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

    /**
     * Adds an exit to the room
     * @param room the Room it leads to
     * @param direction the direction of the exit
     */
    public void addExit(Room room, String direction){
        if (this.getDirection(direction) != null){
            throw new RuntimeException("this room ("+ description +") already has an exit that way! ("+ direction +")");
        }
        exits.put(direction,room);
    }
    
    /**
     * removes an exit from the room
     * @param direction the direction of the exit
     */
    public void removeExit(String direction){
        exits.remove(direction);
    }
    
    /**
     * removes an exit from the room
     * @param room the Room it leads to
     * @param direction the direction of the exit
     */
    public void removeExit(Room room, String direction){
        exits.remove(direction,room);
    }


    /**
     * tostring override
     * @return the room object's individual description
     */
    @Override
    public String toString(){
        return description;
    }

    /**
     * prints grabbable items (Items in contents()), if there are any
     */
    public void printContents(){
        if (contents.isEmpty()){           
        }
        else{
            boolean first = true;
            System.out.print("You can see: ");
            for ( String item : contents.keySet()) {
               if (!first){
                System.out.print(", ");
               }
               else{
                first = false;
               }
               System.out.print(item);
            }
            System.out.println();
        }
    }

    /**
     * prints exits, if there are any
     */
    public void printExits(){
        if (exits.isEmpty()){
        }
        else{
            boolean first = true;
            System.out.print("You can go: ");
            for ( String exit : exits.keySet()) {
                if (!first){
                    System.out.print(", ");
                   }
                   else{
                    first = false;
                   }
                System.out.print(exit);
            }
            System.out.println();
        }
    }

    /**
     * setter for description
     * @param description what to change description to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks if the room has been visited
     * @return True if the room has been visited, false if not
     */
    public boolean hasBeenVisited() {
        return beenVisited;
    }

    /**
     * sets beenVisited to true
     */
    public void visit() {
        this.beenVisited = true;
    }
}
