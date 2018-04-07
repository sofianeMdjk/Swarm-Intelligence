package algorithms;

import clause_management.TreeNode;

import java.io.IOException;
import java.util.Stack;

public class DFS extends SearchAlgos
{

    public DFS(String file) throws IOException
    {
        super(file);
        this.shuffleVariables();
    }

    @Override
    protected void manageOpen() {
        //LIFO management
        this.open = new OpenList(1);
    }

    @Override
    protected int nodeWeight(TreeNode node) {
        return 0;
    }

    @Override
    protected void sortOpen() { }


}
