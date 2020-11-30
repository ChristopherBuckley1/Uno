package Game;

import java.util.ArrayList;

public class player {
    private String name;
    private ArrayList<normalCard> hand;
    private int points;

    public player(String name, ArrayList<normalCard> hand,int points)
    {
        setName(name);
        setHand(hand);
        setPoints(points);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<normalCard> getHand() {
        return hand;
    }

    public void setHand(ArrayList<normalCard> hand) {
        this.hand = hand;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String toString(){
        String cards = "";
        for(int i=0;i<hand.size();i++)
        {   if(getHand().get(i)!=null)
            cards += "Card "+(i+1)+": "+getHand().get(i).toString();
        }
        return "Remaining cards in hand: \n"+cards+"\n\n";
    }
}
