package de.michel.projecteuler.problem0018;

import java.util.List;
import de.michel.projecteuler.problem0018.Tree.Node;

/**
 *
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
<pre>
          3
         7 4
        2 4 6
       8 5 9 3
</pre>
 * That is, <code>3 + 7 + 4 + 9 = 23</code>.<br/>
 * Find the maximum total from top to bottom of the triangle below:
<pre>
                             75
                           95  64
                         17  47  82
                       18  35  87  10
                     20  04  82  47  65
                   19  01  23  75  03  34
                 88  02  77  73  07  63  67
               99  65  04  28  06  16  70  92
             41  41  26  56  83  40  80  70  33
           41  48  72  33  47  32  37  16  94  29
         53  71  44  65  25  43  91  52  97  51  14
       70  11  33  28  77  73  17  78  39  68  17  57
     91  71  52  38  17  14  91  43  58  50  27  29  48
   63  66  04  68  89  53  67  30  73  16  69  87  40  31
 04  62  98  27  23  09  70  98  73  93  38  53  60  04  23
</pre>
 * NOTE: As there are only 16384 routes, it is possible to solve this problem 
 * by trying every route. However, Problem 67, is the same challenge with a
 * triangle containing one-hundred rows; it cannot be solved by brute force,
 * and requires a clever method! ;o)
 * 
 * @author micmeyer
 */
public class Problem0018
{
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        Tree tree = new Tree(Data.RAW_DATA);
        
        List<Node> allNodesList = tree.getAllNodes();
        
        int maxDepth = allNodesList.stream().mapToInt(Node::getDepth).max().orElse(0);

        // Determine the maximum of each node for their subtree
        // from bottom to top, starting with the second last "line" (depth)
        // as the nodes on the last line don't have any children.
        // Finally the root node will have the maximum for the whole tree.
        for (int t = maxDepth - 1; t > 0; t--)
        {
            final int t_ = t;
            allNodesList.stream().filter(e -> e.getDepth() == t_).forEach(e ->
            {
                int leftMax =  e.getLeftChild() .getSubTreeMax();
                int rightMax = e.getRightChild().getSubTreeMax();
                int newMax = e.getSubTreeMax() + Math.max(leftMax, rightMax);
                e.setSubTreeMax(newMax);
            });
        }
        
        System.out.println("Max: " + tree.getRoot().getSubTreeMax());
    }
}
