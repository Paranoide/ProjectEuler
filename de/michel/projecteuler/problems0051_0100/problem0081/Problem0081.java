package de.michel.projecteuler.problems0051_0100.problem0081;

import java.io.File;
import java.net.URL;

/**
 *
 * In the 5 by 5 matrix below, the minimal path sum from the top left to
 * the bottom right, by only moving to the right and down, is indicated
 * in bold and is equal to 2427.
 * <table>
 * <tr><td><b>131</b></td><td>673</td><td>234</td><td>103</td><td>18</td></tr>
 * <tr><td><b>201</b></td><td><b>96</b></td><td><b>342</b></td><td>965</td><td>150</td></tr>
 * <tr><td>630</td><td>803</td><td><b>746</b></td><td><b>422</b></td><td>111</td></tr>
 * <tr><td>537</td><td>699</td><td>497</td><td><b>121</b></td><td>956</td></tr>
 * <tr><td>805</td><td>732</td><td>524</td><td><b>37</b></td><td><b>331</b></td></tr>
 * </table>
 * Find the minimal path sum, in matrix.txt, a 31K text file containing
 * a 80 by 80 matrix, from the top left to the bottom right by only
 * moving right and down.
 *
 * @author micmeyer
 */
public class Problem0081
{
    private static final File MATRIX_FILE;

    static
    {
        String fileName = "matrix.txt";

        URL resource = Problem0081.class.getResource(fileName);
        if (resource == null)
            throw new IllegalArgumentException(fileName + " could not be found.");

        MATRIX_FILE = new File(resource.getFile());
    }

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
        findMinimumRec(0, 0);

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
        if (row >= rows || col >= cols)
            return;

        /*
         * Did we already find a better path up until this entry?
         * If so, we can stop the current path right here.
         */
        if (currentSum + matrix[row][col] >= minMatrix[row][col])
            return;

        currentSum += matrix[row][col];

        minMatrix[row][col] = currentSum;

        findMinimumRec(row, col + 1);
        findMinimumRec(row + 1, col);

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
        MatrixReader matrixReader = new MatrixReader(MATRIX_FILE);
        return matrixReader.getMatrix();
    }
}
