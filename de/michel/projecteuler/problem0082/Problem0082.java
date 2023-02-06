package de.michel.projecteuler.problem0082;

import de.michel.projecteuler.problem0081.MatrixReader;

/**
 *
 * The minimal path sum in the 5 by 5 matrix below, by starting in any cell in the left column
 * and finishing in any cell in the right column, and only moving up, down, and right, is
 * indicated in bold; the sum is equal to 994.
 * <table>
 * <tr><td>131</td><td>673</td><td><b>234</b></td><td><b>103</b></td><td><b>18</b></td></tr>
 * <tr><td><b>201</b></td><td><b>96</b></td><td><b>342</b></td><td>965</td><td>150</td></tr>
 * <tr><td>630</td><td>803</td><td>746</td><td>422</td><td>111</td></tr>
 * <tr><td>537</td><td>699</td><td>497</td><td>121</td><td>956</td></tr>
 * <tr><td>805</td><td>732</td><td>524</td><td>37</td><td>331</td></tr>
 * </table>
 * Find the minimal path sum, in matrix.txt, a 31K text file containing
 * a 80 by 80 matrix, from the left column to the right column.
 *
 * @author micmeyer
 */
public class Problem0082
{
    private static final String MATRIX_FILE_NAME = "src/de.michel.projecteuler.problem0082/matrix.txt";

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
        /*
         * Do it for each row in the first column as the starting entry
         */
        for (int row = 0; row < rows; row++)
            findMinimumRec(row, 0);

        int min = Integer.MAX_VALUE;

        /*
         * In the rightmost column will be all the minima of their ending
         * points. The smallest of these is the solution.
         */
        for (int row = 0; row < rows; row++)
            min = Math.min(min, minMatrix[row][cols - 1]);

        return min;
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
     * In the end minMatrix will hold all minima from the
     * leftmost column to the rightmost column.
     *
     * @param row The row of the current path in the matrix
     * @param col The column of the current path in the matrix
     */
    private static void findMinimumRec(int row, int col)
    {
        if (row < 0 || row >= rows || col >= cols)
            return;

        /*
         * Did we already find a better path up until this entry?
         * If so, we can stop the current path right here.
         */
        if (currentSum + matrix[row][col] >= minMatrix[row][col])
            return;

        currentSum += matrix[row][col];

        minMatrix[row][col] = currentSum;

        // No need to move up or down in the first column
        if (col > 0)
        {
            // Move up
            findMinimumRec(row - 1, col);

            // Move down
            findMinimumRec(row + 1, col);
        }

        // Move right
        findMinimumRec(row, col + 1);

        currentSum -= matrix[row][col];
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
        MatrixReader matrixReader = new MatrixReader(MATRIX_FILE_NAME);
        return matrixReader.getMatrix();
    }
}
