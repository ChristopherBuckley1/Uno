package Game;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class testGame {
    public static void main(String[] args) {
        String playerCountAS ="";
        //generate deck
       normalCard allCards[] = new normalCard[56];
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
        allCards[52] = new normalCard("black","wild");
        allCards[53] = new normalCard("black","wild");
        allCards[54] = new normalCard("black","draw4");
        allCards[55] = new normalCard("black","draw4");

        deck mainDeck = new deck(allCards); //Generate deck

        mainDeck.shuffle(mainDeck.getCards());//Shuffle

        ArrayList<normalCard> discardPile = new ArrayList<normalCard>();//Generate discard pile

        player allPlayers[] = new player[5];//Generate array to store players

        ArrayList<normalCard> hand = new ArrayList<normalCard>();//Generate generic arraylist to insert into player objects

        ArrayList<normalCard>[] hands = new ArrayList[5];//Array to store auto-generated player hands, maximum of 5 players so i made the size 5

        String menuChoice = JOptionPane.showInputDialog("Welcome to uno! Please enter a choice\n\n1. New game\n\n2. Rules\n\n3. Quit");

        while(!choiceIsValid(menuChoice)) {
           menuChoice = JOptionPane.showInputDialog("Error - Invalid choice - Please enter a number between 1 and 5\n\n1. New game\n\n2. Rules\n\n3. Quit");
        }
        if(choiceIsValid(menuChoice))
        {
            if (menuChoice.equals("1")) {
                playerCountAS = JOptionPane.showInputDialog("Please enter the number of players (1-5)");
                while(!choiceIsValid(playerCountAS))
                {
                    playerCountAS = JOptionPane.showInputDialog("Error - Please enter a number between 1 and 5");
                }
                int playerCount = Integer.parseInt(playerCountAS);
                for (int i = 0; i < playerCount; i++) {
                    hands[i] = new ArrayList<>();
                    allPlayers[i] = new player("Player " + Integer.toString(i + 1), hands[i]);//generates player objects


                }
                int deckpointer = 0;//keeps track of the top card of the deck
                //deal cards to each player
                for (int i = 0; i < playerCount; i++) {
                    for (int j = 0; j < 7; j++) {
                        allPlayers[i].getHand().add(mainDeck.getCards()[deckpointer]);
                        mainDeck.getCards()[j] = null;
                        deckpointer++;
                    }
                    if (allPlayers[i] != null)
                        System.out.println(allPlayers[i]);


                }
            }
            else if (menuChoice.equals("2"))
            {

            }
            else if (menuChoice.equals("3"))
            {
                System.exit(0);
            }
        }



    }
    public static boolean choiceIsValid(String choice)
    {

        boolean valid =false;
        for(int i=0;i<choice.length();i++)
        {
            if((Character.isDigit(choice.charAt(i)))&&(choice.length()==1))
            {
                int choiceInt = Integer.parseInt(choice);
                if(choiceInt >=1 && choiceInt <=5)
                {
                    valid = true;
                }
            }
        }

        return valid;
    }
}
