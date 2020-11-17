package Game;

public class player {
    private String name;
    private normalCard hand[];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public normalCard[] getHand() {
        return hand;
    }

    public void setHand(normalCard[] hand) {
        this.hand = hand;
    }

    public player(String name, normalCard hand[])
    {
        setName(name);
        setHand(hand);
    }
}
