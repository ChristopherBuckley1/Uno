package Game;
//player.java
/**
 * An instantiable class which defines a Player
 * @author Chris Buckley
 * */

import java.util.ArrayList;

public class player {
    private String name;
    private ArrayList<normalCard> hand;
    private int points;


    /**
     * Player 3-argument constructor. Calls the 3 mutators to initialise
     * the attributes of a player object with values supplied by the program.
     * @param name the name of the player
     * @param hand Array List of cards that the player holds
     * @param points number of points a player has
     * */
    public player(String name, ArrayList<normalCard> hand,int points)
    {
        setName(name);
        setHand(hand);
        setPoints(points);
    }
    /**
     * Method to get the Name of a player object
     * @return a String value specifying the title of a player object
     * */
    public String getName() {
        return name;
    }
    /**
     * Method to set the name of a player object
     * @param name the name number of a player
     */

    public void setName(String name) {
        this.name = name;
    }
    /**
     * Method to get the Hand of a player object
     * @return an Array list of normalCard objects value specifying the hand of a player object
     * */
    public ArrayList<normalCard> getHand() {
        return hand;
    }
    /**
     * Method to set the hand of a player object
     * @param hand the cards in the players hand
     */
    public void setHand(ArrayList<normalCard> hand) {
        this.hand = hand;
    }
    /**
     * Method to get the points of a player object
     * @return an Integer representing the points of a player object
     * */
    public int getPoints() {
        return points;
    }
    /**
     * Method to set the points of a player object
     * @param points the points of a player object
     */
    public void setPoints(int points) {
        this.points = points;
    }
    /**
     * Method to get the state of a Player object
     * @return a String value specifying the state of a player object
     */

    public String toString(){
        String cards = "";
        for(int i=0;i<hand.size();i++)
        {   if(getHand().get(i)!=null)
            cards += "Card "+(i+1)+": "+getHand().get(i).toString();
        }
        return "Remaining cards in hand: \n"+cards+"\n\n";
    }
}
