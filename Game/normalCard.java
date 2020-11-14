package Game;

public class normalCard extends card{
    private int number;



    public normalCard(String colour, int number)
    {
        super(colour);
        setNumber(number);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
