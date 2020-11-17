package Game;

import java.util.Scanner;

public class testGame {
    public static void main(String[] args) {

        //generate deck
       normalCard allCards[] = new normalCard[103];
        //generate numeric cards for each color
        for(int i=0;i<10;i++)
        {
                allCards[i] = new normalCard("red",Integer.toString(i));

        }
        for(int i=0;i<10;i++)
        {
            allCards[i+10] = new normalCard("yellow",Integer.toString(i));

        }
        for(int i=0;i<10;i++)
        {
            allCards[i+20] = new normalCard("blue",Integer.toString(i));

        }
        for(int i=0;i<10;i++)
        {
            allCards[i+30] = new normalCard("green",Integer.toString(i));


        }
        //generate effect cards for each color
        allCards[40] = new normalCard("red","reverse");
        allCards[41] = new normalCard("red","draw2");
        allCards[42] = new normalCard("red","skip");
        allCards[43] = new normalCard("yellow","reverse");
        allCards[44] = new normalCard("yellow","draw2");
        allCards[45] = new normalCard("yellow","skip");
        allCards[46] = new normalCard("blue","reverse");
        allCards[47] = new normalCard("blue","draw2");
        allCards[48] = new normalCard("blue","skip");
        allCards[49] = new normalCard("green","reverse");
        allCards[50] = new normalCard("green","draw2");
        allCards[51] = new normalCard("green","skip");

        for(int j=0;j< allCards.length;j++)
        {
            System.out.println(allCards[j]);
        }



       // player players[] = new player[4];
      //  normalCard hand1[]={};
        /*for(int i=0;i<noOfPlayers;i++)
        {
            players[i] = new player("Player "+Integer.toString(i),hand1);
        }*/


    }
}
