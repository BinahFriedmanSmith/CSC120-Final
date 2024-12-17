public class Cage extends Item {
     
    private final Room interior; //Room that acts as the interior of the cage
    private Player mouse = null; //used to change the description based on if the mouse character is inside or not
    private Doorway dock = null; //current doorway cage is docked to

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
    @Override
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

    /**
     * returns the description, plus whether the mouse is inside.
     */
    @Override
    public String getDescription(){
        if (mouse.getLocation() == interior){
            return description + " Inside sits a little gray mouse, wearing a tiny golden collar.";
        }
        return description;
    }

    /**
     * assigns the creature that belongs in the cage
     * @param mouse the Player that belongs in the cage
     */
    public void setMouse(Player mouse) {
        this.mouse = mouse;
    }

    /**
     * when picked up, erases all exits to and from the cage's interior
     */
    @Override
    public void pickup(){
        if (dock != null) {            
            dock.undock(this);
            dock = null; 
        }
    }

    /**
     * sets dock to inputted doorway
     * @param d doorway to set dock to
     */
    public void setDock(Doorway d){
        dock = d;
    }
}
