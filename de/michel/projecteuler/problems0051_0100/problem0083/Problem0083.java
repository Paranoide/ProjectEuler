package de.michel.projecteuler.problems0051_0100.problem0083;

import de.michel.projecteuler.problems0051_0100.problem0081.MatrixReader;
import de.michel.projecteuler.util.FileReading;

import java.io.File;
import java.util.TreeSet;

/**
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right,
 * by moving left, right, up, and down, is indicated in bold and is equal to 2297.
 * <table>
 * <tr><td><b>131</b></td><td>673</td><td><b>234</b></td><td><b>103</b></td><td><b>18</b></td></tr>
 * <tr><td><b>201</b></td><td><b>96</b></td><td><b>342</b></td><td>965</td><td><b>150</b></td></tr>
 * <tr><td>630</td><td>803</td><td>746</td><td><b>422</b></td><td><b>111</b></td></tr>
 * <tr><td>537</td><td>699</td><td>497</td><td><b>121</b></td><td>956</td></tr>
 * <tr><td>805</td><td>732</td><td>524</td><td><b>37</b></td><td><b>331</b></td></tr>
 * </table>
 * Find the minimal path sum, in matrix.txt, a 31K text file containing
 * a 80 by 80 matrix, from the top left to the bottom right by moving left, right, up, and down.
 *
 * @author micmeyer
 */
public class Problem0083
{
    private static final File MATRIX_FILE = FileReading.getFile(Problem0083.class, "matrix.txt");

    private static int[][] matrix;
    private static int[][] minMatrix;

    private static int rows;
    private static int cols;

    private static int currentSum = 0;

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        matrix = getMatrix();
        rows = matrix.length;
        cols = matrix[0].length;

        minMatrix = getMinMatrix();

        int minimum = findMinimum();

        System.out.println("Result: " + minimum);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static int findMinimum()
    {
//        findMinimumRec(0, 0);
        findMinimumBreadthFirst();

        return minMatrix[rows - 1][cols - 1];
    }

    /**
     * Brute force through the matrix and write down the
     * minimum into the minMatrix. These minima represent
     * the minimum sum from start to this entry.
     * <p>
     * If we arrive at an entry whose minimum is smaller
     * than our currentSum we don't have to continue on
     * this path as there was already a path that has a
     * smaller sum at this point.
     * <p>
     * In the end minMatrix will hold all minima from start
     * to each entry and the very last one is the minimum
     * we are looking for.
     *
     * @param row The row of the current path in the matrix
     * @param col The column of the current path in the matrix
     */
    private static void findMinimumRec(int row, int col)
    {
        if (row < 0 || col < 0 || row >= rows || col >= cols)
            return;

        /*
         * Did we already find a better path up until this entry?
         * If so, we can stop the current path right here.
         */
        if (currentSum + matrix[row][col] >= minMatrix[row][col])
            return;

        currentSum += matrix[row][col];

        minMatrix[row][col] = currentSum;

//        printArray(minMatrix); // debug
        // Move right
        findMinimumRec(row, col + 1);

        // Move left
        findMinimumRec(row, col - 1);

        // Move down
        findMinimumRec(row + 1, col);

        // Move up
        findMinimumRec(row - 1, col);

        currentSum -= matrix[row][col];
    }

    /**
     * Turns out that the breadth-first search is
     * way faster than the depth-first search.
     * <p>
     * That's because with BFS you put in more
     * smaller numbers earlier while in DFS larger
     * numbers are found early on which decrease
     * more slowly over time.
     * <p>
     * That is also why we use a TreeSet for our
     * workLoad: It orders our parameters by the
     * current sum it is holding, so that we can
     * process the smallest numbers first. This
     * results in much more early sub-tree
     * cutoffs.
     */
    private static void findMinimumBreadthFirst()
    {
        TreeSet<EntryParameters> workLoad = new TreeSet<>();

        workLoad.add(new EntryParameters(0, 0, 0));

        while (!workLoad.isEmpty())
        {
            EntryParameters params = workLoad.pollFirst();

            if (params.row < 0 || params.col < 0 || params.row >= rows || params.col >= cols)
                continue;

            int sum = params.sum + matrix[params.row][params.col];

            if (sum >= minMatrix[params.row][params.col])
                continue;

            minMatrix[params.row][params.col] = sum;

//            printArray(minMatrix); // debug
            workLoad.add(new EntryParameters(params.row + 1, params.col, sum));
            workLoad.add(new EntryParameters(params.row - 1, params.col, sum));
            workLoad.add(new EntryParameters(params.row, params.col + 1, sum));
            workLoad.add(new EntryParameters(params.row, params.col - 1, sum));
        }
    }

    private static void printArray(int[][] matrix)
    {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < matrix.length; row++)
        {
            int[] currRow = matrix[row];

            sb.append("|");
            for (int col = 0; col < currRow.length; col++)
            {
                int value = currRow[col];
                String valueStr;
                if (value == Integer.MAX_VALUE)
                    valueStr = "-";
                else
                    valueStr = String.valueOf(value);

                sb.append(String.format("%6s", valueStr));
                if (col < currRow.length - 1)
                    sb.append(", ");
            }
            sb.append("|\n");
        }

        System.out.println(sb.toString());
    }

    /**
     * Creates the min-matrix based on the previously initialized matrix.
     * Has all entries set to Integer.MAX_VALUE.
     *
     * @return The min-matrix as a 2-dim int array
     */
    private static int[][] getMinMatrix()
    {
        int[][] mMatrix = new int[rows][cols];
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                mMatrix[row][col] = Integer.MAX_VALUE;

        return mMatrix;
    }

    /**
     * Reads the matrix from file
     *
     * @return The matrix from file as a 2-dim int array
     */
    private static int[][] getMatrix()
    {
        MatrixReader matrixReader = new MatrixReader(MATRIX_FILE);
        return matrixReader.getMatrix();
    }

    /**
     * An object that holds the parameters for the
     * breadth-first search.
     */
    private static class EntryParameters implements Comparable<EntryParameters>
    {
        int row, col, sum;

        public EntryParameters(int row, int col, int sum)
        {
            this.row = row;
            this.col = col;
            this.sum = sum;
        }

        @Override
        public int compareTo(EntryParameters o)
        {
            int diff = this.sum - o.sum;

            if (diff != 0)
                return diff;

            diff = this.row - o.row;

            if (diff != 0)
                return diff;

            return this.col - o.col;
        }
    }
}
