package Game;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;



public class testGame {
    public static String gameDetails="";
    public static void main(String[] args) throws Exception{

        String menuChoice = "";



            while(!menuChoice.equals("3"))
            {
                File inFile = new File("game_history.data");

                FileInputStream inStream = new FileInputStream(inFile);

                ObjectInputStream objectInStream = new ObjectInputStream(inStream);

                String gameHistory = (String) objectInStream.readObject();

                gameDetails += gameHistory;

                menuChoice = JOptionPane.showInputDialog("Welcome to uno! Please enter a choice\n\n1. New game\n\n2. Game History\n\n3. Quit");
                while(!choiceIsValid(menuChoice))
                {
                    menuChoice = JOptionPane.showInputDialog("Error - Invalid choice - Please enter a number between 1 and 3\n\n1. New game\n\n2. Game History\n\n3. Quit");
                }
                if (menuChoice.equals("1"))
                {
                    startGame();

                }
                else if (menuChoice.equals("2"))
                {

                        JOptionPane.showMessageDialog(null,gameHistory,"Game History",JOptionPane.INFORMATION_MESSAGE);
                }
                else if (menuChoice.equals("3"))
                {
                    System.exit(0);
                }
            }




    }
    public static void startGame() throws Exception {

        ArrayList<normalCard> placeholderHand = new ArrayList<normalCard>(5);


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






        playerCountAS = JOptionPane.showInputDialog("Please enter the number of players (2-5)");
        while(!menuChoiceIsValid(playerCountAS))
        {
            playerCountAS = JOptionPane.showInputDialog("Error - Please enter a number between 2 and 5");
        }

        int playerCount = Integer.parseInt(playerCountAS);

        ArrayList<normalCard>[] hands = new ArrayList[playerCount];

        player allPlayers[] = new player[playerCount];//Generate array to store players



        for (int i = 0; i < playerCount; i++) {
            hands[i] = new ArrayList<normalCard>();
            allPlayers[i] = new player("Player " + Integer.toString(i + 1), hands[i],0);//generates player objects
        }

        boolean skipPlayed = false;

        boolean gameOver = false;

        int turns = 0,cardChoice = 0,discardPileIndex =0;

        String cardChoiceAS ="";

        int deckpointer = 0;//keeps track of the top card of the deck

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
        int i=0;
        while(gameOver != true)
        {
            skipPlayed = false;


            //Loop through player array for each players turn

            for(i=0;i< allPlayers.length;i++)
            {
                //turn begin
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

                if(cardChoice==0)
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
                else if(allPlayers[i].getHand().get(cardChoice-1).getAttribute().equals("skip"))
                {
                    skipPlayed = true;

                    points = 0;

                    points = calculatePoints(allPlayers[i].getHand().get(cardChoice-1)); //Calculate points

                    allPlayers[i].setPoints(allPlayers[i].getPoints()+points); //update player points

                    discardPile.add(allPlayers[i].getHand().get(cardChoice-1)); //add card to discard pile
                    discardPileIndex++;

                    allPlayers[i].getHand().remove(cardChoice - 1);

                    if(allPlayers.length==2)
                    {
                        if(i==0)
                        {
                            i++;
                        }
                        else
                        {
                            i--;
                        }
                    }

                    else if(i+2<allPlayers.length)
                    {
                        i++;
                    }

                    else
                    {
                        i=0;
                    }
                }

                else if(allPlayers[i].getHand().get(cardChoice-1).getAttribute().equals("draw2"))
                {
                    if(deckpointer>55)
                    {
                        deckpointer = 0;
                    }
                    if (mainDeck.getCards()[deckpointer] == null)
                    {
                        deckpointer = 0;
                        mainDeck.populateDeck(allCards);
                    }

                    if(i==allPlayers.length-1)//check if last player in array to avoid arrayindexoutofboundsexception
                    {

                        allPlayers[i-(allPlayers.length-1)].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                        allPlayers[i-(allPlayers.length-1)].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                    }




                    else
                    {

                        allPlayers[i+1].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                        allPlayers[i+1].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                    }

                }
                else if(allPlayers[i].getHand().get(cardChoice-1).getAttribute().equals("draw4"))
                {
                    if(deckpointer>55)
                    {
                        deckpointer = 0;
                    }
                    if (mainDeck.getCards()[deckpointer] == null)
                    {
                        deckpointer = 0;
                        mainDeck.populateDeck(allCards);
                    }

                    if(i==allPlayers.length-1)//check if last player in array to avoid arrayindexoutofboundsexception
                    {

                        allPlayers[i-(allPlayers.length-1)].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                        allPlayers[i-(allPlayers.length-1)].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                        allPlayers[i-(allPlayers.length-1)].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                        allPlayers[i-(allPlayers.length-1)].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                    }




                    else
                    {

                        allPlayers[i+1].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                        allPlayers[i+1].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                        allPlayers[i+1].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                        allPlayers[i+1].getHand().add(mainDeck.getCards()[deckpointer]);
                        deckpointer++;
                    }

                }



                if(cardChoice!=0&&skipPlayed == false)//calculate points and move cards around at end of turn
                {

                    points = 0;

                    points = calculatePoints(allPlayers[i].getHand().get(cardChoice-1)); //Calculate points

                    allPlayers[i].setPoints(allPlayers[i].getPoints()+points); //update player points

                    discardPile.add(allPlayers[i].getHand().get(cardChoice-1)); //add card to discard pile
                    discardPileIndex++;

                        if(allPlayers[i].getHand().get(cardChoice-1).getAttribute().equals("reverse"))
                        {

                            allPlayers[i].getHand().remove(cardChoice-1); //remove card from hand
                            allPlayers = reversePlayed(allPlayers,i);
                            i=0;

                        }

                        else{

                            allPlayers[i].getHand().remove(cardChoice - 1);

                             }

                }

                if (allPlayers[i].getHand().size() == 0||allPlayers[i].getPoints()>= 135) //Check for winner
                {
                    JOptionPane.showMessageDialog(null,allPlayers[i] + allPlayers[i].getName()+ " Wins! Points: "+allPlayers[i].getPoints());


                    gameDetails += "\nWinner: "+allPlayers[i].getName() + " Points: "+Integer.toString(allPlayers[i].getPoints())+" Number of turns: "+Integer.toString(turns)+"\n";

                    File outFile = new File("game_history.data");

                    FileOutputStream outStream = new FileOutputStream(outFile);

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);

                    objectOutputStream.writeObject(gameDetails);

                    outStream.close();

                    gameOver = true;
                    break;

                }

                turns++;
            }
        } turns = 0;
    }

    public static player[] reversePlayed(player allPlayers[],int i)
    {
        player temp[] = new player[allPlayers.length];
        int length = allPlayers.length;
        if(allPlayers.length==5) {
            switch (i) {
                case 0:
                    temp[0] = allPlayers[0];
                    temp[1] = allPlayers[4];
                    temp[2] = allPlayers[3];
                    temp[3] = allPlayers[2];
                    temp[4] = allPlayers[1];
                    break;
                case 1:
                    temp[0] = allPlayers[1];
                    temp[1] = allPlayers[0];
                    temp[2] = allPlayers[4];
                    temp[3] = allPlayers[3];
                    temp[4] = allPlayers[2];

                    break;
                case 2:
                    temp[0] = allPlayers[2];
                    temp[1] = allPlayers[1];
                    temp[2] = allPlayers[0];
                    temp[3] = allPlayers[4];
                    temp[4] = allPlayers[3];

                    break;

                case 3:
                    temp[0] = allPlayers[3];
                    temp[1] = allPlayers[2];
                    temp[2] = allPlayers[1];
                    temp[3] = allPlayers[0];
                    temp[4] = allPlayers[4];

                    break;

                case 4:
                    temp[0] = allPlayers[4];
                    temp[1] = allPlayers[3];
                    temp[2] = allPlayers[2];
                    temp[3] = allPlayers[1];
                    temp[4] = allPlayers[0];
                    break;
            }

        }
        else if(allPlayers.length==4) {
            switch (i) {
                case 0:
                    temp[0] = allPlayers[0];
                    temp[1] = allPlayers[3];
                    temp[2] = allPlayers[2];
                    temp[3] = allPlayers[1];

                    break;
                case 1:
                    temp[0] = allPlayers[1];
                    temp[1] = allPlayers[0];
                    temp[2] = allPlayers[3];
                    temp[3] = allPlayers[2];


                    break;
                case 2:
                    temp[0] = allPlayers[2];
                    temp[1] = allPlayers[1];
                    temp[2] = allPlayers[0];
                    temp[3] = allPlayers[3];
                    break;

                case 3:
                    temp[0] = allPlayers[3];
                    temp[1] = allPlayers[2];
                    temp[2] = allPlayers[1];
                    temp[3] = allPlayers[0];
                    break;
            }
        }
        else if(allPlayers.length==3)
        {
            switch (i) {
                case 0:
                    temp[0] = allPlayers[0];
                    temp[1] = allPlayers[2];
                    temp[2] = allPlayers[1];
                    break;
                case 1:
                    temp[0] = allPlayers[1];
                    temp[1] = allPlayers[0];
                    temp[2] = allPlayers[2];
                    break;
                case 2:
                    temp[0] = allPlayers[2];
                    temp[1] = allPlayers[1];
                    temp[2] = allPlayers[0];

                    break;
            }
        }
        else
        {
            switch (i) {
                case 0:
                    temp[0] = allPlayers[0];
                    temp[1] = allPlayers[1];
                    break;
                case 1:
                    temp[0] = allPlayers[1];
                    temp[1] = allPlayers[0];

                    break;
            }
        }

        return temp;
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
    public static boolean menuChoiceIsValid(String choice)
    {

        boolean valid =false;
        for(int i=0;i<choice.length();i++)
        {
            if((Character.isDigit(choice.charAt(i)))&&(choice.length()==1))
            {
                int choiceInt = Integer.parseInt(choice);
                if(choiceInt >=2 && choiceInt <=5)
                {
                    valid = true;
                }
            }
        }

        return valid;
    }
    public static boolean choiceIsValid(String choice)
    {

        boolean valid =false;
        for(int i=0;i<choice.length();i++)
        {
            if((Character.isDigit(choice.charAt(i)))&&(choice.length()==1))
            {
                int choiceInt = Integer.parseInt(choice);
                if(choiceInt >=1 && choiceInt <=3)
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
        int handSize = hand.size();


        if(choice.length()==1&&choice.charAt(0)=='0')
        {
            return true;
        }
        else if(choice.equals(""))
            return false;

        else {
            for (int i = 0; i < choice.length(); i++) {


                if (!Character.isDigit(choice.charAt(i))) {
                    return false;

                    }
                 }

                }

                choiceInt = Integer.parseInt(choice);

                if(choiceInt >= 0&&choiceInt<=hand.size())
                {
                    if(discardPile.get(discardPile.size()-1).getColour().equals("black"))
                    {
                        valid = true;
                    }
                    else if((discardPile.get(discardPile.size()-1).getColour().equals(hand.get(choiceInt-1).getColour()))|| (discardPile.get(discardPile.size()-1).getAttribute().equals(hand.get(choiceInt-1).getAttribute())))
                        valid = true;
                    else if(hand.get(choiceInt-1).getColour().equals("black"))
                    {
                        valid = true;
                    }
                }

        return valid;

    }
}
