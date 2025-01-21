package de.michel.projecteuler.problems0051_0100.problem0096;

import de.michel.projecteuler.util.FileReading;

import java.io.File;
import java.util.List;

/**
 * @author micmeyer
 */
public class Problem0096
{
    private static final String FILE_NAME = "p096_sudoku.txt";

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        File sudokuFile = FileReading.getFile(Problem0096.class, FILE_NAME);

        List<Sudoku> sudokus = SudokuLoader.loadFrom(sudokuFile);

        int result = 0;

        for (Sudoku sudoku : sudokus)
        {
            SudokuSolver solver = new SudokuSolver(sudoku);

            boolean success = solver.solve();
            if (!success)
                throw new RuntimeException("No success!");

            result += getFirst3DigitsAsNumber(sudoku);
        }

        System.out.println("Result: " + result);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static int getFirst3DigitsAsNumber(Sudoku sudoku)
    {
        return sudoku.get(0, 0) * 100
                + sudoku.get(0, 1) * 10
                + sudoku.get(0, 2);
    }
}
