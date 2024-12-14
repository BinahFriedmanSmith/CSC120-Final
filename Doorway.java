public class Doorway extends Item {
    
    private final String inDirection; //direction of path into the connected room
    private final String outDirection; //direction of path out of the connected room

    private final Room connectedRoom;

    //constructor for doorways that exist as interactable objects
    public Doorway(String name, String desc, String in, String out, Room room) {
        super(name, desc);
        inDirection = in;
        outDirection = out;
        connectedRoom = room;
    }

    //constructor for doorways that don't represent something interactab;e
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

    public void undock(Room roomIn){
        /* idk if i need this
        if (roomIn.getDirection(inDirection) == null) {
            throw new RuntimeException("docked room is already missing that exit!");
        }
        if (connectedRoom.getDirection(outDirection) == null){
            throw new RuntimeException("connected room is already missing that exit!");
        }
            //*/
        roomIn.removeExit(connectedRoom, inDirection);
        connectedRoom.removeExit(roomIn, outDirection);
    }

    public String getIn(){
        return inDirection;
    }
}
