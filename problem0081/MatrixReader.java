package problem0081;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    public MatrixReader(String fileName)
    {
        this.file = new File(fileName);
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
        MatrixReader mReader = new MatrixReader("src/problem0081/matrix_small.txt");

        int[][] matrix = mReader.getMatrix();
        for (int[] line : matrix)
            System.out.println(Arrays.toString(line));
    }
}
