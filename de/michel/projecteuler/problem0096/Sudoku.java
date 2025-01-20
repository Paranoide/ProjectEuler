package de.michel.projecteuler.problem0096;

public class Sudoku
{
    private final int[][] cells = new int[9][9];

    public Sudoku(int[][] initialCells)
    {
        for (int col = 0; col < 9; col++)
            for (int row = 0; row < 9; row++)
                this.cells[row][col] = initialCells[row][col];
    }

    public int get(int row, int col)
    {
        return this.cells[row][col];
    }

    public void set(int row, int col, int digit)
    {
        this.cells[row][col] = digit;
    }

    public void remove(int row, int col)
    {
        this.cells[row][col] = 0;
    }

    public boolean isFree(int row, int col)
    {
        return this.get(row, col) == 0;
    }

    public boolean canBePlaced(int row, int col, int digit)
    {
        if (!this.canBePlacedInBlock(row, col, digit))
            return false;

        if (!this.canBePlacedInRow(row, digit))
            return false;

        if (!this.canBePlacedInColumn(col, digit))
            return false;

        return true;
    }

    public boolean canBePlacedInRow(int row, int digit)
    {
        for (int c = 0; c < 9; c++)
            if (this.get(row, c) == digit)
                return false;

        return true;
    }


    public boolean canBePlacedInColumn(int col, int digit)
    {
        for (int r = 0; r < 9; r++)
            if (this.get(r, col) == digit)
                return false;

        return true;
    }


    public boolean canBePlacedInBlock(int row, int col, int digit)
    {
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;

        for (int r = startRow; r < startRow + 3; r++)
            for (int c = startCol; c < startCol + 3; c++)
                if (this.get(r, c) == digit)
                    return false;

        return true;
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        String ls = System.lineSeparator();

        for (int yBlock = 0; yBlock < 3; yBlock++)
        {
            sb.append("+---------+---------+---------+").append(ls);

            for (int row = yBlock * 3; row < yBlock * 3 + 3; row++)
            {
                for (int xBlock = 0; xBlock < 3; xBlock++)
                {
                    sb.append("|");
                    for (int col = xBlock * 3; col < xBlock * 3 + 3; col++)
                        sb.append(" ").append(this.cells[row][col]).append(" ");
                }
                sb.append("|").append(ls);
            }
        }

        sb.append("+---------+---------+---------+");

        return sb.toString();
    }
}
