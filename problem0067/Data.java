package problem0067;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
        
        File file = new File("src/problem0067/Data");
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
