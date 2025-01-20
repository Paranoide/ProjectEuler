package de.michel.projecteuler.problems0051_0100.problem0081;

import de.michel.projecteuler.problems0001_0050.problem0022.Problem0022;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Michel Meyer <micmeyer@uos.de>
 */
public class MatrixReader
{
    private final File file;

    public MatrixReader(File file)
    {
        this.file = file;
    }

    public int[][] getMatrix()
    {
        List<String> lines = readFileLines();

        int[][] matrix = new int[lines.size()][];
        int index = 0;
        for (String line : lines)
            matrix[index++] = parseLine(line);

        return matrix;
    }

    private List<String> readFileLines()
    {
        List<String> lines = new LinkedList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(this.file)))
        {
            while ((line = br.readLine()) != null)
                lines.add(line);
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }

        return lines;
    }

    private static int[] parseLine(String line)
    {
        String[] values = line.split(",");
        int[] lineValues = new int[values.length];

        for (int t = 0; t < values.length; t++)
            lineValues[t] = Integer.parseInt(values[t]);

        return lineValues;
    }

    public static void main(String[] args)
    {
        String fileName = "matrix_small.txt";
        URL resource = MatrixReader.class.getResource(fileName);
        if (resource == null)
            throw new IllegalArgumentException(fileName + " could not be found.");
        File file = new File(resource.getFile());

        MatrixReader mReader = new MatrixReader(file);

        int[][] matrix = mReader.getMatrix();
        for (int[] line : matrix)
            System.out.println(Arrays.toString(line));
    }
}
