package problem0022;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author micmeyer
 */
public class Problem0022
{
    public static void main(String[] args)
    {
        String[] names = readNames();
        List<String> namesList = java.util.Arrays.asList(names);
        
        Collections.sort(namesList);
        
        System.out.println(namesList);
    }
    
    
    
    
    private static int alphabeticalScore(String name)
    {
        int score = 0;
        for (int c = 0; c < name.length(); c++)
        {
            score += (name.charAt(c) - 'A' + 1);
        }
        return score;
    }
    
    private static String[] readNames()
    {
        String content = null;
        File file = new File("src/problem0022/names.txt");
        System.out.println(file.getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            content = br.readLine();
        }
        catch (IOException ioe)
        {
            System.err.println(ioe.getMessage());
        }
        
        String[] names = null;
        if (content != null)
        {
            names = content.replace("\"", "").split(",");
        }
        return names;
    }
}
