package Game;

public class normalCard{
    private String attribute;
    private String colour;



    public normalCard(String colour, String attribute)
    {
        setColour(colour);
        setAttribute(attribute);
    }


    public String getColour(){return colour;}

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setColour(String colour){
        this.colour = colour;
    }

    public String toString(){


        return "Color: "+getColour()+"\nAttribute: "+getAttribute();
    }


}
