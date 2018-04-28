package problem0089;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michel Meyer <micmeyer@uos.de>
 */
public class RomanNumeralsFileReader
{
    public static List<String> getRomanNumerals()
    {
        String fileName = "src/problem0089/roman.txt";
        File file = new File(fileName);

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
