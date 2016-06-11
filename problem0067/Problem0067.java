package problem0067;

import java.util.List;
import problem0067.Tree.Node;

/**
 *
 *
 *
 * @author micmeyer
 */
public class Problem0067
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

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
                int leftMax = e.getLeftChild().getSubTreeMax();
                int rightMax = e.getRightChild().getSubTreeMax();
                int newMax = e.getSubTreeMax() + Math.max(leftMax, rightMax);
                e.setSubTreeMax(newMax);
            });
        }

        System.out.println("Max: " + tree.getRoot().getSubTreeMax());

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

}
