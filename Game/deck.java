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
}
