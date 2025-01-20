package de.michel.projecteuler.problems0051_0100.problem0096;

public class SudokuSolver
{
    private final Sudoku sudoku;

    public SudokuSolver(Sudoku sudoku)
    {
        this.sudoku = sudoku;
    }

    public boolean solve()
    {
        return this.solveRec(0,0);
    }

    private boolean next(int row, int col)
    {
        int nextRow = row;
        int nextCol = col+1;
        if (nextCol >= 9)
        {
            nextRow++;
            nextCol = 0;
        }
        return this.solveRec(nextRow, nextCol);
    }

    private boolean solveRec(int row, int col)
    {
        if (row >= 9 || col >= 9)
            return true;

        if (!this.sudoku.isFree(row, col))
            return next(row, col);

        for (int digit = 1; digit <= 9; digit++)
        {
            if (this.sudoku.canBePlaced(row, col, digit))
            {
                this.sudoku.set(row, col, digit);

                boolean success = this.next(row, col);
                if (success)
                    return true;

                this.sudoku.remove(row, col);
            }
        }

        return false;
    }
}
