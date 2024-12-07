public class Item {

    public String description = "This is an item.";
    private String name = "item";

    public Item(String name, String desc){
        this.name = name;
        this.description = desc;
    }

    
    @Override
    public String toString(){
        return name;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

}
