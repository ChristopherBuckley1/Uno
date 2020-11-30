package Game;

import java.util.Random;

public class deck {

    private normalCard[] cards;
    public deck(normalCard[] cards)
    {
        setCards(cards);
    }

    public normalCard[] getCards() {

        return cards;
    }

    public void setCards(normalCard[] cards) {
        this.cards = cards;
    }

    public normalCard[] shuffle(normalCard cards[])
    {
        //Shuffle algorithm from https://www.geeksforgeeks.org/shuffle-a-deck-of-cards-3/
        Random num = new Random();
        for(int i=0;i< cards.length;i++)
        {
            int r = i + num.nextInt(56-i);

            normalCard temp = cards[r];
            cards[r] = cards[i];
            cards[i] = temp;
        }

        return cards;
    }

    public void populateDeck(normalCard cards[])
    {
        for(int i=0;i<10;i++)
        {
            cards[i] = new normalCard("red",Integer.toString(i));

        }
        for(int i=0;i<10;i++)
        {
            cards[i+10] = new normalCard("yellow",Integer.toString(i));

        }
        for(int i=0;i<10;i++)
        {
            cards[i+20] = new normalCard("blue",Integer.toString(i));

        }
        for(int i=0;i<10;i++)
        {
            cards[i+30] = new normalCard("green",Integer.toString(i));


        }
        //generate effect cards for each color
        cards[40] = new normalCard("red","reverse");
        cards[41] = new normalCard("red","draw2");
        cards[42] = new normalCard("red","skip");
        cards[43] = new normalCard("yellow","reverse");
        cards[44] = new normalCard("yellow","draw2");
        cards[45] = new normalCard("yellow","skip");
        cards[46] = new normalCard("blue","reverse");
        cards[47] = new normalCard("blue","draw2");
        cards[48] = new normalCard("blue","skip");
        cards[49] = new normalCard("green","reverse");
        cards[50] = new normalCard("green","draw2");
        cards[51] = new normalCard("green","skip");
        cards[52] = new normalCard("black","wild");
        cards[53] = new normalCard("black","wild");
        cards[54] = new normalCard("black","draw4");
        cards[55] = new normalCard("black","draw4");
        this.cards = cards;
    }
}
