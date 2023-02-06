package de.michel.projecteuler.problem0084;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Just see https://projecteuler.net/problem=84
 * <p>
 * Technically this is a just a monopoly simulation where
 * the number of times for each square visited is counted.
 * <p>
 * The problem description gives you the rules and the
 * result for rolling with two 6-sided dice. The task is
 * to solve the same problem for rolling with two 4-sided
 * dice.
 *
 * @author micmeyer
 */
public class Problem0084
{
    private static final int NR_OF_DICE_SIDES = 4;

    private static final int NR_OF_COMMUNITY_CARDS = 16;
    private static final int COMMUNITY_ADV_TO_GO = 0;
    private static final int COMMUNITY_GO_TO_JAIL = 1;

    private static final int NR_OF_CHANCE_CARDS = 16;
    private static final int CHANCE_ADV_TO_GO = 0;
    private static final int CHANCE_GO_TO_JAIL = 1;
    private static final int CHANCE_GO_TO_C1 = 2;
    private static final int CHANCE_GO_TO_E3 = 3;
    private static final int CHANCE_GO_TO_H2 = 4;
    private static final int CHANCE_GO_TO_R1 = 5;
    private static final int CHANCE_GO_TO_NEXT_R_1 = 6;
    private static final int CHANCE_GO_TO_NEXT_R_2 = 7;
    private static final int CHANCE_GO_TO_NEXT_U = 8;
    private static final int CHANCE_GO_BACK_3_SQUARES = 9;

    private static final int SQUARE_COUNT = 40;

    private static final int SQUARE_INDEX_GO = 0;
    private static final int SQUARE_INDEX_COMMUNITY_1 = 2;
    private static final int SQUARE_INDEX_COMMUNITY_2 = 17;
    private static final int SQUARE_INDEX_COMMUNITY_3 = 33;
    private static final int SQUARE_INDEX_CHANCE_1 = 7;
    private static final int SQUARE_INDEX_CHANCE_2 = 22;
    private static final int SQUARE_INDEX_CHANCE_3 = 36;
    private static final int SQUARE_INDEX_JAIL = 10;
    private static final int SQUARE_INDEX_GO_TO_JAIL = 30;
    private static final int SQUARE_INDEX_R1 = 5;
    private static final int SQUARE_INDEX_R2 = 15;
    private static final int SQUARE_INDEX_R3 = 25;
    private static final int SQUARE_INDEX_R4 = 35;
    private static final int SQUARE_INDEX_U1 = 12;
    private static final int SQUARE_INDEX_U2 = 28;
    private static final int SQUARE_INDEX_C1 = 11;
    private static final int SQUARE_INDEX_E3 = 24;
    private static final int SQUARE_INDEX_H2 = 39;

    private static final int DOUBLE_COUNT_FOR_JAIL = 3;

    private static final long[] VISITED_SQUARES = new long[SQUARE_COUNT];

    private static Random random;

    private static CardPile communityCards;
    private static CardPile chanceCards;

    private static int playerSquareIndex = 0;

    private static int doubleCount = 0;

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        random = new Random(System.currentTimeMillis());

        communityCards = new CardPile(NR_OF_COMMUNITY_CARDS);
        chanceCards = new CardPile(NR_OF_CHANCE_CARDS);

        final long simulationTime = 5000;

        int dice1, dice2;

        while (System.currentTimeMillis() < (time + simulationTime))
        {
            dice1 = rollDie();
            dice2 = rollDie();

            if (dice1 == dice2)
                doubleCount++;
            else
                doubleCount = 0;

            /*
             * Rolling a double x times in a row
             * puts you in jail
             */
            if (doubleCount == DOUBLE_COUNT_FOR_JAIL)
            {
                playerSquareIndex = SQUARE_INDEX_JAIL;
                doubleCount = 0;
            }
            else
                movePlayerBy(dice1 + dice2);

            VISITED_SQUARES[playerSquareIndex]++;
        }

        System.out.println("Result: " + getResult());

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    /**
     * Move the player by the given squares AND execute
     * the action on the ending square.
     *
     * @param nrOfSquares
     */
    private static void movePlayerBy(int nrOfSquares)
    {
        int newSquare = (playerSquareIndex + nrOfSquares) % SQUARE_COUNT;
        while (newSquare < 0)
            newSquare += SQUARE_COUNT;

        movePlayerTo(newSquare);
    }

    /**
     * Move the player to the given square AND execute
     * the action on that square.
     *
     * @param square
     */
    private static void movePlayerTo(int square)
    {
        playerSquareIndex = square;

        switch (square)
        {
            case SQUARE_INDEX_CHANCE_1:
            case SQUARE_INDEX_CHANCE_2:
            case SQUARE_INDEX_CHANCE_3:
                pullChanceCard();
                break;
            case SQUARE_INDEX_COMMUNITY_1:
            case SQUARE_INDEX_COMMUNITY_2:
            case SQUARE_INDEX_COMMUNITY_3:
                pullCommunityCard();
                break;
            case SQUARE_INDEX_GO_TO_JAIL:
                playerSquareIndex = SQUARE_INDEX_JAIL;
        }
    }

    /**
     * Pulls a community chest card and moves the player
     * to the specified square if the card is a player-moving
     * card. The action on the ending square will NOT be
     * executed.
     */
    private static void pullCommunityCard()
    {
        int card = communityCards.pullCard();

        switch (card)
        {
            case COMMUNITY_ADV_TO_GO:
                playerSquareIndex = SQUARE_INDEX_GO;
                break;
            case COMMUNITY_GO_TO_JAIL:
                playerSquareIndex = SQUARE_INDEX_JAIL;
                break;
        }
    }

    /**
     * Pulls a chance card and moves the player
     * to the specified square if the card is a player-moving
     * card. The action on the ending square will NOT be
     * executed.
     */
    private static void pullChanceCard()
    {
        int card = chanceCards.pullCard();

        switch (card)
        {
            case (CHANCE_ADV_TO_GO):
                playerSquareIndex = SQUARE_INDEX_GO;
                break;
            case (CHANCE_GO_TO_JAIL):
                playerSquareIndex = SQUARE_INDEX_JAIL;
                break;
            case (CHANCE_GO_TO_C1):
                playerSquareIndex = SQUARE_INDEX_C1;
                break;
            case (CHANCE_GO_TO_E3):
                playerSquareIndex = SQUARE_INDEX_E3;
                break;
            case (CHANCE_GO_TO_H2):
                playerSquareIndex = SQUARE_INDEX_H2;
                break;
            case (CHANCE_GO_TO_R1):
                playerSquareIndex = SQUARE_INDEX_R1;
                break;
            case (CHANCE_GO_TO_NEXT_R_1):
            case (CHANCE_GO_TO_NEXT_R_2):
                movePlayerToNextRailway();
                break;
            case (CHANCE_GO_TO_NEXT_U):
                movePlayerToNextUtility();
                break;
            case (CHANCE_GO_BACK_3_SQUARES):
                playerSquareIndex -= 3;
                break;
        }
    }

    /**
     * Moves the player to the NEXT railway square. The
     * action on the ending square will NOT be executed.
     * (actually there is no action to be executed anyway,
     * but if there was the action would be ignored)
     */
    private static void movePlayerToNextRailway()
    {
        int playerPos = playerSquareIndex;

        while (playerPos != SQUARE_INDEX_R1 && playerPos != SQUARE_INDEX_R2 && playerPos != SQUARE_INDEX_R3 && playerPos != SQUARE_INDEX_R4)
            playerPos = (playerPos + 1) % SQUARE_COUNT;

        playerSquareIndex = playerPos;
    }

    /**
     * Moves the player to the NEXT utility square. The
     * action on the ending square will NOT be executed.
     * (actually there is no action to be executed anyway,
     * but if there was the action would be ignored)
     */
    private static void movePlayerToNextUtility()
    {
        int playerPos = playerSquareIndex;

        while (playerPos != SQUARE_INDEX_U1 && playerPos != SQUARE_INDEX_U2)
            playerPos = (playerPos + 1) % SQUARE_COUNT;

        playerSquareIndex = playerPos;
    }

    private static int rollDie()
    {
        return random.nextInt(NR_OF_DICE_SIDES) + 1;
    }

    private static String getStatistics()
    {
        List<SquareVisitInfo> infos = new ArrayList<>();

        int sum = 0;
        for (int t = 0; t < SQUARE_COUNT; t++)
        {
            long visits = VISITED_SQUARES[t];
            sum += visits;
            infos.add(new SquareVisitInfo(t, visits));
        }

        Collections.sort(infos);
        Collections.reverse(infos);

        DecimalFormat decFormat = new DecimalFormat("0.000");

        StringBuilder sb = new StringBuilder();

        for (SquareVisitInfo info : infos)
            sb.append(String.format("%2d: %8d (%s%%)\n", info.squareIndex, info.count, decFormat.format(100.0 * info.count / sum)));

        return sb.toString();
    }

    private static String getResult()
    {
        List<SquareVisitInfo> infos = new ArrayList<>();

        for (int t = 0; t < SQUARE_COUNT; t++)
            infos.add(new SquareVisitInfo(t, VISITED_SQUARES[t]));

        Collections.sort(infos);
        Collections.reverse(infos);

        SquareVisitInfo info1 = infos.get(0);
        SquareVisitInfo info2 = infos.get(1);
        SquareVisitInfo info3 = infos.get(2);

        return String.valueOf(info1.squareIndex) + String.valueOf(info2.squareIndex) + String.valueOf(info3.squareIndex);
    }

    private static class SquareVisitInfo implements Comparable<SquareVisitInfo>
    {
        int squareIndex;
        long count;

        public SquareVisitInfo(int squareIndex, long count)
        {
            this.squareIndex = squareIndex;
            this.count = count;
        }

        @Override
        public int compareTo(SquareVisitInfo o)
        {
            if (this.count - o.count < 0)
                return -1;
            if (this.count - o.count > 0)
                return 1;

            return 0;
        }
    }
}
