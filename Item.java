public class Item {

    public String description = "This is an item."; //physical description of the item. include suggested actions in CAPS
    private String name = "item"; //name of the item - how it is referred to by the player

    //constructor
    public Item(String name, String desc){
        this.name = name;
        this.description = desc;
    }

    /**
     * toString override returns the item's name
     * @return the item's name
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * returns the item's name
     * @return the item's name
     */
    public String getName(){
        return name;
    }

    /**
     * returns the item's description
     * @return the item's description
     */
    public String getDescription(){
        return description;
    }

    /**
     * multipurpose method designed to be overridden by children
     * @param currentPlayer player that does the action. some children may have special messages depending on the player's attributes
     */
    public void use(Player currentPlayer){
        System.out.println("You can't use that!");
    }

    /**
     * multipurpose method designed to be overridden by children
     */
    public void use(){
        System.out.println("You can't use that!");
    }

    /**
     * multipurpose method designed to be overridden by children. called when item is picked up
     * @param currentPlayer player that does the action. some children may have special messages depending on the player's attributes
     */
    public void pickup(Player currentPlayer){
    }

    /**
     * multipurpose method designed to be overridden by children. called when item is picked up
     */
    public void pickup(){
    }



    /**
     * method designed to be overridden by cage so i can call it
     */
    public void dock(Cage c){
        System.out.println("You can't put the cage there!");
    }

    /**
     * method designed to be overridden by cage so i can call it
     */
    public Room getInterior(){
        return null;
    }
}
