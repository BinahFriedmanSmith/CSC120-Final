public class Cage extends Item {
    
    private final Room interior;
    private Player mouse = null;

    public Cage(String name, String desc, Room in) {
        super(name, desc);
        interior = in;
    }

    public Room getInterior(){
        return interior;
    }

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
