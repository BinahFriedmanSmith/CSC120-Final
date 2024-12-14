public class Lever extends Item {

    private final Room room;
    private final Doorway door;

    public Lever(String name, String desc, Room rm, Doorway dr) {
        super(name, desc);
        room = rm;
        door = dr;
    }

    public void use(Player currentPlayer) {
        System.out.print("You pull the lever with a satisfying KA-CHUNK.");
        if (room == currentPlayer.getLocation()){
            System.out.println(" A door opens to the " + door.getIn());
        }
        door.dock(room);
    }
    
    @Override
    public void use() {
        System.out.println("You pull the lever with a satisfying KA-CHUNK.");
        door.dock(room);
    }
}
