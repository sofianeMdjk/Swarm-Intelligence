package algorithms;

import clause_management.TreeNode;

import java.util.LinkedList;

public class OpenList
{
    private LinkedList<TreeNode> open = null;
    private int manageType;

    public OpenList(int manageType)
    {
        open = new LinkedList<>();
        this.manageType = manageType;
    }

    public void addNode(TreeNode node)
    {
        this.open.add(node);
    }

    public TreeNode removeNode()
    {
        TreeNode node = null;
        switch (this.manageType)
        {

            //FIFO
            case 0 :
            {
                node = open.removeFirst();
            }break;

            //LIFO
            case 1 :
            {
                node = open.removeLast();
            }break;

        }
        return node;
    }

    public LinkedList<TreeNode> getOpen() {
        return open;
    }

    public boolean isEmpty()
    {
        return open.isEmpty();
    }


}
