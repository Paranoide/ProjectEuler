package de.michel.projecteuler.problem0018;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author micmeyer
 */
public class Tree
{

    private Node root;

    private Node[] allNodes;

    public Tree(String data)
    {
        String[] rows = data.split("\n");
        int rowLen = rows.length;
        allNodes = new Node[rowLen * (rowLen + 1) / 2];

        int nodeIndex = 0;

        String rootNode = rows[0];
        int rootNodeValue = Integer.parseInt(rootNode);
        this.root = new Node(rootNodeValue);
        this.allNodes[nodeIndex] = this.root;
        nodeIndex++;

        for (int row = 1; row < rowLen; row++)
        {
            String rowStr = rows[row];
            String[] rowValues = rowStr.split(" ");

            for (String rowValue : rowValues)
            {
                int value = Integer.parseInt(rowValue);
                Node newNode = new Node(value);
                newNode.depth = row + 1;
                this.allNodes[nodeIndex] = newNode;
                nodeIndex++;
            }
        }

        // connect children
        for (int t = 0; t < this.allNodes.length; t++)
        {
            Node node = this.allNodes[t];
            int depth = node.depth;

            if (t + depth >= this.allNodes.length)
            {
                break;
            }

            node.setLeftChild(this.allNodes[t + depth]);
            node.setRightChild(this.allNodes[t + depth + 1]);
        }
    }
    
    public Node getRoot()
    {
        return this.root;
    }
    
    public List<Node> getAllNodes()
    {
        return Arrays.asList(this.allNodes);
    }

    public static class Node
    {

        private int value;
        private int subTreeMax;

        private Node[] children;
        private Node parent;

        private int depth;

        public Node(int value)
        {
            this.value = value;
            this.subTreeMax = value;
            this.children = new Node[2];
            this.depth = 1;
        }

        public void setLeftChild(Node left)
        {
            this.children[0] = left;
            left.setParent(this);
            left.depth = this.depth + 1;
        }

        public void setRightChild(Node right)
        {
            this.children[1] = right;
            right.setParent(this);
            right.depth = this.depth + 1;
        }

        public void setParent(Node parent)
        {
            this.parent = parent;
        }
        
        public int getValue()
        {
            return value;
        }

        public void setValue(int value)
        {
            this.value = value;
        }

        public int getSubTreeMax()
        {
            return subTreeMax;
        }

        public void setSubTreeMax(int subTreeMax)
        {
            this.subTreeMax = subTreeMax;
        }

        public Node getLeftChild()
        {
            return children[0];
        }
        
        public Node getRightChild()
        {
            return children[1];
        }

        public int getDepth()
        {
            return depth;
        }

        public void setDepth(int depth)
        {
            this.depth = depth;
        }

        @Override
        public String toString()
        {
            String l = this.children[0] == null ? "-" : this.children[0].value + "";
            String r = this.children[1] == null ? "-" : this.children[1].value + "";
            return String.format("%d: %d -> [%s  %s]", this.depth, this.value, l, r);
        }
    }
}
