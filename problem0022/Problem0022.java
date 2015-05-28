package problem0022;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *
 * Using names.txt, a 46K text file
 * containing over five-thousand first names, begin by sorting it into
 * alphabetical order. Then working out the alphabetical value for each name,
 * multiply this value by its alphabetical position in the list to obtain a name
 * score.<br/>
 * <br/>
 * For example, when the list is sorted into alphabetical order, COLIN, which is
 * worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN
 * would obtain a score of 938 × 53 = 49714.<br/>
 * <br/>
 * What is the total of all the name scores in the file?
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

        long sum = 0;
        for (int t = 0; t < namesList.size(); t++)
        {
            String name = namesList.get(t);
            sum += (t + 1) * alphabeticalScore(name);
        }

        System.out.println(sum);

    }

    private static long alphabeticalScore(String name)
    {
        long score = 0;
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
