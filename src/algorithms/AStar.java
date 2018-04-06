package algorithms;


import clause_management.TreeNode;

import java.io.IOException;

public class AStar extends EvaluationFunction
{

    public AStar(String file) throws IOException
    {
        super(file);
        this.shuffleVariables();
    }

    @Override
    protected void manageOpen() {
        //Sort Open Management
        open = new OpenList(0);
    }

    @Override
    protected int nodeWeight(TreeNode node) {
        return f(node);
    }

    @Override
    protected int h(TreeNode node)
    {
        int litteral = node.getVarNumber() * node.getNodeTruthValue();
        return clauseSolver.litteralFrequencyApparition(litteral);
    }

}
