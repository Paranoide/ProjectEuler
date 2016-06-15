package problem0068;

import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 *
 * @author micmeyer
 */
public class Problem0068
{

    private static int[] graph;
    private static boolean[] inUse;
    private static List<String> solutions;

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        graph = new int[10];
        inUse = new boolean[10];

        solutions = new LinkedList<>();

        solve(0);

        System.out.println("Result: " + getMax());
        System.out.println(solutions.size());
        System.out.println(solutions);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static void solve(int index)
    {
        if (index == 3)
        {
            // Sums in general are always between 13 and 20:
            // A line that contains the 10 can have a minimum of 13 = 10 + 2 + 1
            // A line that contains the 1  can have a maximum of 20 = 1 + 10 + 9
            int s = lineSum(1);
            if (s < 13 || s > 20)
            {
                return;
            }
        }
        else if ( (index > 4) && (index % 2 == 1) )
        {
            // Every time a new line is completed we check if the sum of that
            // new line is the same as the line completed before.
            int lineIndex = index / 2;
            if (lineSum(lineIndex-1) != lineSum(lineIndex))
            {
                return;
            }
        }
        else if (index == 10)
        {
            // Again, we check if the sums fits. In case of that it does, we
            // create a solution and add it to the list as all lines have the
            // same sum and the graph is complete.
            int lineIndex = index / 2;
            if (lineSum(lineIndex-1) != lineSum(lineIndex))
            {
                return;
            }
            else
            {
                createSolution();
            }
        }
        
        // We usually start with the number 1 to put into the graph. But if it
        // is an external node we don't start with a lower node than the very
        // first node in the graph because then this node would be the new
        // starting node (as described in the problem). To create a solution we
        // always start with the very first node (i.e. graph[0]) and go
        // clockwise from there, so we have to make sure that node stays the
        // lowest node in the graph.
        int startN = 1;
        boolean external = isExternalNode(index);
        if (external)
        {
            startN = graph[0] + 1;
        }
        
        // We usually end at the number 10 to put into the graph. At index 0
        // there is no need to go higher than 6 because this node has to stay
        // the lowest node. If there was, for example, a 7 in this first node
        // there must be a lower number in one of the other external nodes (as
        // there are 5 external nodes but only the numbers 7, 8, 9 and 10 are
        // to give).
        // To fulfill the requirement of ending with a 16-digit solution there
        // must never be a 10 in the internal nodes; so this is checked, too.
        int maxN = 10;
        if (index == 0)
        {
            maxN = 6;
        }
        else if (!external)
        {
            maxN = 9;
        }
        
        // The rest is like an anagram solver; try to put the numbers from
        // 1 to 10 in any order into the graph and see if it works out.
        for (int n = startN; n <= maxN; n++)
        {
            if (!inUse[n - 1])
            {
                graph[index] = n;
                inUse[n - 1] = true;
                solve(index + 1);
                inUse[n - 1] = false;
            }
        }
    }

    private static boolean isExternalNode(int index)
    {
        switch (index)
        {
            case 0:
            case 3:
            case 5:
            case 7:
            case 9:
                return true;
            default:
                return false;
        }
    }
    
    private static void createSolution()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(graph[0]).append(graph[1]).append(graph[2]);
        sb.append(graph[3]).append(graph[2]).append(graph[4]);
        sb.append(graph[5]).append(graph[4]).append(graph[6]);
        sb.append(graph[7]).append(graph[6]).append(graph[8]);
        sb.append(graph[9]).append(graph[8]).append(graph[1]);
        System.out.println("Sum: " + lineSum(1));
        solutions.add(sb.toString());
    }
    
    private static int lineSum(int line)
    {
        int sum = 0;
        switch (line)
        {
            case 1:
                sum = graph[0] + graph[1] + graph[2];
                break;
            case 2:
                sum = graph[2] + graph[3] + graph[4];
                break;
            case 3:
                sum = graph[4] + graph[5] + graph[6];
                break;
            case 4:
                sum = graph[6] + graph[7] + graph[8];
                break;
            case 5:
                sum = graph[1] + graph[8] + graph[9];
                break;
        }
        return sum;
    }

    private static String getMax()
    {
        String max = null;
        for (String s : solutions)
        {
            if (max == null || s.compareTo(max) > 0)
            {
                max = s;
            }
        }
        return max;
    }

}
