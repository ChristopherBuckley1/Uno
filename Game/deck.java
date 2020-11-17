package Game;

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

    public void shuffle(normalCard[] deck)
    {

    }
}
