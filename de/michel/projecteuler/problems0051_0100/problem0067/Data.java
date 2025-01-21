package de.michel.projecteuler.problems0051_0100.problem0067;

import de.michel.projecteuler.util.FileReading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author micmeyer
 */
public class Data
{
    private static final String FILE_NAME = "Data";

    public static final String RAW_DATA;

    static
    {
        File file = FileReading.getFile(Data.class, FILE_NAME);

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n");
            }
        }
        catch (IOException ioe)
        {
            throw new RuntimeException(ioe);
        }

        RAW_DATA = sb.toString();
    }
}
