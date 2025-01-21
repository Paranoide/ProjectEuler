package de.michel.projecteuler.problems0051_0100.problem0089;

import de.michel.projecteuler.util.FileReading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michel Meyer <micmeyer@uos.de>
 */
public class RomanNumeralsFileReader
{
    private static final String FILE_NAME = "roman.txt";

    public static List<String> getRomanNumerals()
    {
        File file = FileReading.getFile(RomanNumeralsFileReader.class, FILE_NAME);

        List<String> numerals = new ArrayList<>(1000);

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
                numerals.add(line);
        }
        catch (IOException ioe)
        {
            System.err.println(ioe.getMessage());
        }

        return numerals;
    }
}
