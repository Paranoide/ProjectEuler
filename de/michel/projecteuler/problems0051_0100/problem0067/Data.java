package de.michel.projecteuler.problems0051_0100.problem0067;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author micmeyer
 */
public class Data
{

    public static final String RAW_DATA;
    
    static
    {
        StringBuilder sb = new StringBuilder();

        String fileName = "Data";
        URL resource = Data.class.getResource(fileName);
        if (resource == null)
            throw new IllegalArgumentException(fileName + " could not be found.");
        File file = new File(resource.getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ( (line = br.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n");
            }
        }
        catch (IOException ioe)
        {
            
        }
        
        RAW_DATA = sb.toString();
    }
}
