public class Doorway extends Item {
    
    private final String inDirection; //direction of path into the connected room
    private final String outDirection; //direction of path out of the connected room

    private final Room connectedRoom; //connected room
 
    //constructor for doorways that exist as interactable objects
    public Doorway(String name, String desc, String in, String out, Room room) {
        super(name, desc);
        inDirection = in;
        outDirection = out;
        connectedRoom = room;
    }

    //constructor for doorways that don't represent something interactable
    public Doorway(String in, String out, Room room) {
        super(null, null);
        inDirection = in;
        outDirection = out;
        connectedRoom = room;
    }

    /**
     * Activated when a Room is placed inside. Creates new exits in inputted Room and connectedRoom to each other in inDirection and outDirection respectively. fails if either already has an exit in that direction.
     */
    public void dock(Room roomIn){
        if (roomIn.getDirection(inDirection) != null) {
            throw new RuntimeException("docked room already has an exit that way!");
        }
        if (connectedRoom.getDirection(outDirection) != null){
            throw new RuntimeException("connected room already has an exit that way!");
        }
        roomIn.addExit(connectedRoom, inDirection);
        connectedRoom.addExit(roomIn, outDirection);
    }

    /**
     * Activated when a Room is removed. removes exits between inputted Room and connectedRoom
     * @param roomIn room to disconnect
     */
    public void undock(Room roomIn){
        roomIn.removeExit(connectedRoom, inDirection);
        connectedRoom.removeExit(roomIn, outDirection);
    }


     /**
     * Activated when a Cage is placed inside. Creates new exits in inputted Cage's interior and connectedRoom to each other in inDirection and outDirection respectively. fails if either already has an exit in that direction.
     * @param cage cage to connect
     */
    public void dock(Cage cage){
        if (cage.getInterior().getDirection(inDirection) != null) {
            throw new RuntimeException("docked room already has an exit that way!");
        }
        if (connectedRoom.getDirection(outDirection) != null){
            throw new RuntimeException("connected room already has an exit that way!");
        }
        cage.getInterior().addExit(connectedRoom, inDirection);
        connectedRoom.addExit(cage.getInterior(), outDirection);
    }
     /**
     * Activated when a Cage is removed. removes exits between inputted Cage's interior and connectedRoom
     * @param cage cage to disconnect
     */
    public void undock(Cage cage){
        cage.getInterior().removeExit(inDirection);
        connectedRoom.removeExit(outDirection);
    }

    /**
     * returns the direction of path into the connected room
     * @return direction of path into the connected room
     */
    public String getIn(){
        return inDirection;
    }
}
