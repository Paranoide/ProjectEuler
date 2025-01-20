package de.michel.projecteuler.problems0051_0100.problem0084;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Michel Meyer <micmeyer@uos.de>
 */
public class CardPile
{
    private final Integer[] cards;

    private final int cardCount;

    private int currIndex = 0;

    public CardPile(int cardCount)
    {
        List<Integer> cardList = new ArrayList<>();
        for (int t = 0; t < cardCount; t++)
            cardList.add(t);

        Collections.shuffle(cardList);

        this.cards = cardList.toArray(new Integer[cardCount]);
        this.cardCount = cardCount;
    }

    public int pullCard()
    {
        int card = this.cards[this.currIndex];
        this.nextIndex();
        return card;
    }

    private void nextIndex()
    {
        this.currIndex = (this.currIndex + 1) % this.cardCount;
    }
}
