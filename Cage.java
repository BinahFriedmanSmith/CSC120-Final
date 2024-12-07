public class Cage extends Item {
    
    private final Room interior;

    public Cage(String name, String desc, Room in) {
        super(name, desc);
        interior = in;
    }

    public Room getInterior(){
        return interior;
    }

    
    
}
