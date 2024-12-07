public class Doorway {
    private String inDirection; //direction of path into the connected room
    private String outDirection; //direction of path out of the connected room

    private Room connectedRoom;

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
}
