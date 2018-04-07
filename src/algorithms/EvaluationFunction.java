package algorithms;

import clause_management.TreeNode;

import java.io.IOException;
import java.util.*;

public abstract class EvaluationFunction extends SearchAlgos
{

    protected EvaluationFunction(String file) throws IOException
    {
        super(file);
    }

    //the g function returns the number of clauses satisfied with each path
    protected int g(TreeNode node)
    {
        return getNumberOfClausesFromPath(node);
    }

    //This method is abstract so you can define it as you like in each informed algo class
    protected int h(TreeNode node)
    {
        return 0;
    }

    protected int f(TreeNode node)
    {
        return g(node) + h(node);
    }


    protected void sortOpen()
    {
        Collections.sort(open.getOpen(), new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return o2.getNodeWeight() - o1.getNodeWeight();
            }
        });
    }
}
