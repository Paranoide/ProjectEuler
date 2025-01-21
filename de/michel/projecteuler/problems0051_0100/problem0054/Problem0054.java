package de.michel.projecteuler.problems0051_0100.problem0054;

import de.michel.projecteuler.util.FileReading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The file, poker.txt, contains one-thousand random hands dealt to two players.
 * Each line of the file contains ten cards (separated by a single space): the
 * first five are Player 1's cards and the last five are Player 2's cards. You
 * can assume that all hands are valid (no invalid characters or repeated
 * cards), each player's hand is in no specific order, and in each hand there is
 * a clear winner.<br/>
 * <br/>
 * How many hands does Player 1 win?
 *
 * @author micmeyer
 */
public class Problem0054
{
    private static final String FILE_NAME = "poker.txt";

    private static enum CardColor
    {

        HEARTS, DIAMONDS, SPADES, CLUBS
    }

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        String[][] hands = readHands();
        int count = 0;

        for (String[] match : hands)
        {
            if (getHandScore(match[0]) > getHandScore(match[1]))
            {
                count++;
            }
        }

        System.out.println("Count: " + count);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static long getHandScore(String hand)
    {
        long score = -1;

        String[] cards = hand.split(" ");
        int[] values = getValues(cards);
        CardColor[] colors = getColors(cards);

        doubleQuickSort(values, colors);

        long mult = 10000000000L;

        // STRAIGHT FLUSH ------------------------------------------------------
        boolean straight = true, flush = true;
        for (int t = 0; t < 4; t++)
        {
            if (values[t] != values[t + 1] + 1)
            {
                straight = false;
            }

            if (colors[t] != colors[t + 1])
            {
                flush = false;
            }
        }

        if (straight && flush)
        {
            score = 9 * mult;
            mult /= 100;

            for (int t = 0; t < 5; t++)
            {
                score += values[t] * mult;
                mult /= 100;
            }

            return score;
        }
        // ---------------------------------------------------------------------

        // FOUR OF A KIND ------------------------------------------------------
        boolean four = false;

        for (int t = 0; t < 2 && !four; t++)
        {
            four = true;
            for (int t2 = t; t2 < t + 3; t2++)
            {
                if (values[t2] != values[t2 + 1])
                {
                    four = false;
                }
            }
        }

        if (four)
        {
            score = 8 * mult;
            mult /= 100;
            int fourOfAKindValue = values[2];
            int otherValue
                    = (values[0] == fourOfAKindValue ? values[4] : values[0]);

            for (int t = 0; t < 4; t++)
            {
                score += fourOfAKindValue * mult;
                mult /= 100;
            }
            score += otherValue;

            return score;
        }
        // ---------------------------------------------------------------------

        // FULL HOUSE ----------------------------------------------------------
        if (values[0] == values[1] && values[3] == values[4])
        {
            byte dir = 0;
            if (values[2] == values[1])
            {
                dir = -1;
            }
            else if (values[2] == values[3])
            {
                dir = 1;
            }

            if (dir != 0)
            {
                score = 7 * mult;
                mult /= 100;

                for (int t = 0; t < 3; t++)
                {
                    score += values[2 + dir] * mult;
                    mult /= 100;
                }

                for (int t = 0; t < 2; t++)
                {
                    score += values[2 - dir] * mult;
                    mult /= 100;
                }

                return score;
            }
        }
        // ---------------------------------------------------------------------

        // FLUSH ---------------------------------------------------------------
        if (flush)
        {
            score = 6 * mult;
            mult /= 100;
            for (int t = 0; t < 5; t++)
            {
                score += values[t] * mult;
                mult /= 100;
            }
            return score;
        }
        // ---------------------------------------------------------------------

        // STRAIGHT ------------------------------------------------------------
        if (straight)
        {
            score = 5 * mult;
            mult /= 100;
            for (int t = 0; t < 5; t++)
            {
                score += values[t] * mult;
                mult /= 100;
            }
            return score;
        }
        // ---------------------------------------------------------------------

        // THREE OF A KIND -----------------------------------------------------
        boolean three = false;
        int tThree = 0;
        for (; tThree < 3 && !three; tThree++)
        {
            three = true;
            for (int t2 = tThree; t2 < tThree + 2; t2++)
            {
                if (values[t2] != values[t2 + 1])
                {
                    three = false;
                }
            }
        }

        if (three)
        {
            score = 4 * mult;
            mult /= 100;
            int threeOfAKindValue = values[2];

            for (int t = 0; t < 3; t++)
            {
                score += threeOfAKindValue * mult;
                mult /= 100;
            }

            switch (tThree)
            {
                case 1:
                    score += values[3] * mult;
                    score += values[4];
                    break;
                case 2:
                    score += values[0] * mult;
                    score += values[4];
                    break;
                case 3:
                    score += values[0] * mult;
                    score += values[1];
                    break;
                default:
                    score = -2;
            }

            return score;
        }
        // ---------------------------------------------------------------------

        // TWO PAIRS -----------------------------------------------------------
        byte pairCount = 0;
        int tPair1 = -1, tPair2 = -1;
        for (int t = 0; t < 4; t++)
        {
            if (values[t] == values[t + 1])
            {
                if (tPair1 < 0)
                {
                    tPair1 = t;
                }
                else
                {
                    tPair2 = t;
                }
                pairCount++;
            }
        }

        if (pairCount == 2)
        {
            score = 3 * mult;
            mult /= 100;

            score += values[tPair1] * mult;
            mult /= 100;
            score += values[tPair1] * mult;
            mult /= 100;
            score += values[tPair2] * mult;
            mult /= 100;
            score += values[tPair2] * mult;
            mult /= 100;

            score += values[8 - 2 * (tPair1 + tPair2)];

            return score;
        }
        // ---------------------------------------------------------------------

        // ONE PAIR ------------------------------------------------------------
        if (pairCount == 1)
        {
            score = 2 * mult;
            mult /= 100;

            score += values[tPair1] * mult;
            mult /= 100;
            score += values[tPair1] * mult;
            mult /= 100;

            for (int t = 0; t < 3; t++)
            {
                if (t != tPair1)
                {
                    t += 2;
                }
                score += values[t] * mult;
                mult /= 100;
            }
            return score;
        }
        // ---------------------------------------------------------------------

        // HIGH CARD -----------------------------------------------------------
        score = mult;
        mult /= 100;
        for (int t = 0; t < 5; t++)
        {
            score += values[t] * mult;
            mult /= 100;
        }
        // ---------------------------------------------------------------------
        return score;
    }

    private static int[] getValues(String[] cards)
    {
        int[] values = new int[cards.length];
        char val;
        for (int t = 0; t < cards.length; t++)
        {
            val = cards[t].charAt(0);
            if (val >= '2' && val <= '9')
            {
                values[t] = (int) (val - '0');
            }
            else
            {
                switch (val)
                {
                    case 'T':
                        values[t] = 10;
                        break;
                    case 'J':
                        values[t] = 11;
                        break;
                    case 'Q':
                        values[t] = 12;
                        break;
                    case 'K':
                        values[t] = 13;
                        break;
                    case 'A':
                        values[t] = 14;
                        break;
                }

            }
        }
        return values;
    }

    private static CardColor[] getColors(String[] cards)
    {
        CardColor[] colors = new CardColor[cards.length];

        for (int t = 0; t < cards.length; t++)
        {
            char color = cards[t].charAt(1);
            switch (color)
            {
                case 'H':
                    colors[t] = CardColor.HEARTS;
                    break;
                case 'D':
                    colors[t] = CardColor.DIAMONDS;
                    break;
                case 'S':
                    colors[t] = CardColor.SPADES;
                    break;
                case 'C':
                    colors[t] = CardColor.CLUBS;
                    break;
            }
        }

        return colors;
    }

    private static void doubleQuickSort(int[] values, CardColor[] colors)
    {
        doubleQuickSort(values, 0, values.length - 1, colors);
    }

    private static void doubleQuickSort(int[] values, int start, int end, CardColor[] colors)
    {
        int i = start;                          // index of left-to-right scan
        int k = end;                            // index of right-to-left scan

        if (end - start >= 1)                   // check that there are at least two elements to sort
        {
            int pivot = values[start];       // set the pivot as the first element in the partition

            while (k > i)                   // while the scan indices from left and right have not met,
            {
                while (values[i] >= pivot && i <= end && k > i)  // from the left, look for the first
                {
                    i++;                                    // element greater than the pivot
                }
                while (values[k] < pivot && k >= start && k >= i) // from the right, look for the first
                {
                    k--;                                        // element not greater than the pivot
                }
                if (k > i)                                       // if the left seekindex is still smaller than
                {
                    swap(values, i, k, colors);                      // the right index, swap the corresponding elements
                }
            }
            swap(values, start, k, colors);          // after the indices have crossed, swap the last element in
            // the left partition with the pivot 
            doubleQuickSort(values, start, k - 1, colors); // quicksort the left partition
            doubleQuickSort(values, k + 1, end, colors);   // quicksort the right partition
        }
        // if there is only one element in the partition, do not do any sorting

        // the array is sorted, so exit
    }

    private static void swap(int[] values, int index1, int index2, CardColor[] colors)
// pre: array is full and index1, index2 < array.length
// post: the values at indices 1 and 2 have been swapped
    {
        int temp = values[index1];           // store the first value in a temp
        values[index1] = values[index2];      // copy the value of the second into the first
        values[index2] = temp;               // copy the value of the temp into the second

        CardColor tmp = colors[index1];
        colors[index1] = colors[index2];
        colors[index2] = tmp;
    }

    private static String[][] readHands()
    {
        File file = FileReading.getFile(Problem0054.class, FILE_NAME);

        String[][] hands = new String[1000][2];

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            int index = 0;

            while (index < 1000 && (line = br.readLine()) != null)
            {
                hands[index][0] = line.substring(0, 14);
                hands[index][1] = line.substring(15);
                index++;
            }
        }
        catch (IOException ioe)
        {
            System.err.println(ioe.getMessage());
        }

        return hands;
    }

}
