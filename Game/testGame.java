package Game;
import javax.swing.*;
import java.util.ArrayList;


public class testGame {
    public static void main(String[] args) {

        String menuChoice = "";

            while(!menuChoice.equals("3"))
            {
                menuChoice = JOptionPane.showInputDialog("Welcome to uno! Please enter a choice\n\n1. New game\n\n2. Rules\n\n3. Quit");
                while(!choiceIsValid(menuChoice))
                {
                    menuChoice = JOptionPane.showInputDialog("Error - Invalid choice - Please enter a number between 1 and 5\n\n1. New game\n\n2. Rules\n\n3. Quit");
                }
                if (menuChoice.equals("1"))
                {
                    startGame();
                }
                else if (menuChoice.equals("2"))
                {
                    //placeholder
                }
                else if (menuChoice.equals("3"))
                {
                    System.exit(0);
                }
            }




    }
    public static void startGame(){

        ArrayList<normalCard> placeholderHand = new ArrayList<normalCard>(5);
        player winner = new player("winner",placeholderHand,0);

        String playerCountAS ="";
        //generate deck
        normalCard allCards[] = new normalCard[56];
        //generate numeric cards for each color


        deck mainDeck = new deck(allCards); //Generate deck

        mainDeck.populateDeck(allCards);

        mainDeck.shuffle(mainDeck.getCards());//Shuffle

        ArrayList<normalCard> discardPile = new ArrayList<normalCard>();//Generate discard pile

        discardPile.add(mainDeck.getCards()[mainDeck.getCards().length-1]);
        mainDeck.getCards()[mainDeck.getCards().length-1]=null;

//end of card generation///////////////////////////////////////////////////////////////////




        playerCountAS = JOptionPane.showInputDialog("Please enter the number of players (1-5)");
        while(!choiceIsValid(playerCountAS))
        {
            playerCountAS = JOptionPane.showInputDialog("Error - Please enter a number between 1 and 5");
        }

        int playerCount = Integer.parseInt(playerCountAS);
        ArrayList<normalCard>[] hands = new ArrayList[playerCount];
        player allPlayers[] = new player[playerCount];//Generate array to store players



        for (int i = 0; i < playerCount; i++) {
            hands[i] = new ArrayList<normalCard>();
            allPlayers[i] = new player("Player " + Integer.toString(i + 1), hands[i],0);//generates player objects
        }



        boolean gameOver = false;

        int turns = 0,cardChoice = 0,discardPileIndex =0;

        String cardChoiceAS ="";

        int deckpointer = 0;//keeps track of the top card of the deck



        while(gameOver != true)
        {
            //turn begin

            //Dealing cards at start of game
            if(turns == 0)
            {
                for (int i = 0; i < playerCount; i++)
                {
                    for (int j = 0; j < 7; j++)
                    {
                        allPlayers[i].getHand().add(mainDeck.getCards()[deckpointer]);
                        mainDeck.getCards()[j] = null;
                        deckpointer++;
                    }
                }
            }
            //Loop through player array for each players turn
            for(int i=0;i<playerCount;i++)
            {
                cardChoiceAS = JOptionPane.showInputDialog(allPlayers[i].getName()+" Points: "+allPlayers[i].getPoints()+" \n"+
                        "Top Card: "+discardPile.get(discardPileIndex)+"\n"+allPlayers[i].getName()+
                        " - Please select a card\n\n0: Draw a card"+"\n "+  allPlayers[i].toString());



                while(!cardChoiceValid(allPlayers[i].getHand(),cardChoiceAS,discardPile))
                {
                    cardChoiceAS = JOptionPane.showInputDialog(allPlayers[i].getName()+
                            " - ERROR - Please a draw card or select a valid matching card between 1 and "+
                            allPlayers[i].getHand().size()+"\nTop Card: "+discardPile.get(discardPile.size()-1)+
                            "\n"+allPlayers[i].getName()+"\n0: Draw a card\n"+allPlayers[i].toString());
                }



                cardChoice = Integer.parseInt(cardChoiceAS);

                System.out.println(discardPile);

                int points;

                if(cardChoice!=0)//calculate points
                {

                    points = 0;

                    points = calculatePoints(allPlayers[i].getHand().get(cardChoice-1));

                    allPlayers[i].setPoints(allPlayers[i].getPoints()+points);

                    discardPile.add(allPlayers[i].getHand().get(cardChoice-1));

                    allPlayers[i].getHand().remove(cardChoice-1);

                    discardPileIndex++;
                }


                if(cardChoiceAS.equals("0"))
                {
                    if(deckpointer>55)
                    {
                        deckpointer = 0;
                    }
                    if (mainDeck.getCards()[deckpointer] == null)
                    {
                        deckpointer = 0;
                        mainDeck.populateDeck(allCards);
                        allPlayers[i].getHand().add(mainDeck.getCards()[deckpointer]);
                    }
                    else
                        {

                            allPlayers[i].getHand().add(mainDeck.getCards()[deckpointer]);
                             deckpointer++;

                        }

                }




                if (allPlayers[i].getHand().size() == 0||allPlayers[i].getPoints()>= 135) //Check for winner
                {
                    JOptionPane.showMessageDialog(null,allPlayers[i] + allPlayers[i].getName()+ " Wins! Points: "+allPlayers[i].getPoints());

                    gameOver = true;

                }

                turns++;
            }
        } turns = 0;
    }

    public static int calculatePoints(normalCard card)
    {
        int points = 0;
        if(card.getAttribute().equals("skip")||card.getAttribute().equals("draw2")||card.getAttribute().equals("reverse")||card.getAttribute().equals("wild")||card.getAttribute().equals("draw4"))
            points = 20;
        else
            points = Integer.parseInt(card.getAttribute());

        return points;
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
    public static boolean cardChoiceValid(ArrayList<normalCard> hand, String choice, ArrayList<normalCard> discardPile)
    {
        boolean valid=false;
        int choiceInt =0;

        if (choice.equals("0"))
        {
            return true;
        }
        for(int i=0;i<choice.length();i++)
        {
            if(Character.isDigit(choice.charAt(i)))
            {
                choiceInt = Integer.parseInt(choice);
                if(choiceInt >= 0&&choiceInt<=hand.size())
                {
                    if(discardPile.get(discardPile.size()-1).getColour().equals("black"))
                    {
                        valid = true;
                    }
                    else if((discardPile.get(discardPile.size()-1).getColour()==hand.get(choiceInt-1).getColour())|| (discardPile.get(discardPile.size()-1).getAttribute().equals(hand.get(choiceInt-1).getAttribute())))
                    valid = true;
                    else if(hand.get(choiceInt-1).getColour().equals("black"))
                    {
                        valid = true;
                    }
                }
            }

            else
            {
                valid = false;
            }
        }
        return valid;

    }
}
