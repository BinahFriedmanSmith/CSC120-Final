public class Cage extends Item {
     
    private final Room interior; //Room that acts as the interior of the cage
    private Player mouse = null; //used to change the description based on if the mose character is inside or not

    /**
     * Constructor 
     * @param name name of the item - how it is referred to by the player
     * @param desc physical description of the item. include suggested actions in CAPS
     * @param in interior
     */
    public Cage(String name, String desc, Room in) {
        super(name, desc);
        interior = in;
    }
 
    /**
     * returns the interior
     * @return the interior
     */
    public Room getInterior(){
        return interior;
    }

    /**
     * Adds an exit to the room inside the cage
     * @param room the Room it leads to
     * @param direction the direction of the exit
     */
    public void addExit(Room room, String direction){
        interior.addExit(room, direction);
    }
    
    public void removeExit(String direction){
        interior.removeExit(direction);
    }
    public void removeExit(Room room, String direction){
        interior.removeExit(room, direction);
    }

    @Override
    public String getDescription(){
        if (mouse.getLocation() == interior){
            return description + " Inside sits a little gray mouse, wearing a tiny golden collar.";
        }
        return description;
    }

    public void setMouse(Player mouse) {
        this.mouse = mouse;
    }
}
