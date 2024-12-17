public class Lever extends Item {

    private final Room room; //oom that the lever opens a door in
    private final Doorway door;
    private boolean active = false; //whether the lever is "on" (door open) or "off" (door closed)

    /**
     * Constructor
     * @param name name of the item - how it is referred to by the player and environment
     * @param desc physical description of the item. include suggested actions in CAPS
     * @param rm room that the lever opens a door in
     * @param dr doorway that determines where the door leads
     */
    public Lever(String name, String desc, Room rm, Doorway dr) {
        super(name, desc);
        room = rm;
        door = dr;
    }
 
    /**
     * uses the lever, opening or closing the connected doorway
     * @param currentPlayer current viewpoint player, so the player can see the door open if they are in the right place
     */
    @Override
    public void use(Player currentPlayer) {
        System.out.print("You pull the lever with a satisfying KA-CHUNK.");
        if(active){
            if (room == currentPlayer.getLocation()){
                System.out.println(" A door closes to the " + door.getIn());
            }
            else{
                System.out.println();
            }
            door.undock(room);
        }
        else{
            if (room == currentPlayer.getLocation()){
                System.out.println(" A door opens to the " + door.getIn());
            }
            else{
                System.out.println();
            }
            door.dock(room);
        }   
        active = !active;
    }
    
    /**
     * uses the lever, opening or closing the connected doorway
     */
    @Override
    public void use() {
        System.out.println("You pull the lever with a satisfying KA-CHUNK.");
        if(active){
            door.undock(room);
        }
        else{
            door.dock(room);
        }
    }
}
