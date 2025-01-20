package de.michel.projecteuler.problems0051_0100.problem0096;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SudokuLoader
{
    private static final String LINE_REGEX = "\\d{9}";

    public static List<Sudoku> loadFrom(File file)
    {
        List<Sudoku> sudokus = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                if (line.matches(LINE_REGEX))
                {
                    List<String> oneSudoku = new ArrayList<>();

                    oneSudoku.add(line);
                    for (int otherLines = 0; otherLines < 8; otherLines++)
                        oneSudoku.add(br.readLine());

                    Sudoku sudoku = loadFrom(oneSudoku);
                    sudokus.add(sudoku);
                }
            }
        }
        catch (IOException ioe)
        {
            throw new RuntimeException(ioe);
        }

        return sudokus;
    }


    public static Sudoku loadFrom(List<String> rows)
    {
        if (rows.size() != 9)
            throw new IllegalArgumentException("rows.size() == " + rows.size() + " != 9");

        int[][] fields = new int[9][];

        for (int i = 0; i < 9; i++)
        {
            String row = rows.get(i);

            if (row.length() != 9)
                throw new IllegalArgumentException("row.length() == " + row.length() + " != 9 (content: " + row + ")");

            int[] rowArray = row.chars().map(c -> c - '0').toArray();

            for (int digit : rowArray)
                if (digit < 0 || digit > 9)
                    throw new IllegalArgumentException("digit == " + digit + " but must be between 0 and 9");

            fields[i] = rowArray;
        }

        return new Sudoku(fields);
    }
}
