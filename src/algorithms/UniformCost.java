package algorithms;


import clause_management.TreeNode;

import java.io.IOException;

public class UniformCost extends EvaluationFunction
{

    public UniformCost(String file) throws IOException
    {
        super(file);
        this.shuffleVariables();

    }

    @Override
    protected void manageOpen() {
        //Sort Open Management
        this.open = new OpenList(0);
    }

    @Override
    protected int nodeWeight(TreeNode node) {
        return f(node);
    }
}
