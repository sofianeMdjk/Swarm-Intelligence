package clause_management;

import java.util.LinkedList;

public class TreeNode {

    //Node attributes
    private int nodeNumber;
    private int varNumber;
    private int nodeTruthValue;
    private int nodeLeftSon;
    private int nodeRightSon;
    private int satisfiedClauses;
    private int nodePredecessor;
    private int nodeWeight;



    //Constructor
    public TreeNode(int nodeNumber, int varNumber, int nodeTruthValue,
                    int nodeLeftSon, int nodeRightSon, int satisfiedClauses, int nodePredecessor)
    {
        this.nodeNumber = nodeNumber;
        this.varNumber = varNumber;
        this.nodeTruthValue = nodeTruthValue;
        this.nodeLeftSon = nodeLeftSon;
        this.nodeRightSon = nodeRightSon;
        this.satisfiedClauses = satisfiedClauses;
        this.nodePredecessor = nodePredecessor;
        this.nodeWeight = 0;
    }

    public TreeNode(int nodeNumber, int varNumber, int nodeTruthValue,
                    int nodeLeftSon, int nodeRightSon, int satisfiedClauses, int nodePredecessor, int nodeWeight)
    {
        this.nodeNumber = nodeNumber;
        this.varNumber = varNumber;
        this.nodeTruthValue = nodeTruthValue;
        this.nodeLeftSon = nodeLeftSon;
        this.nodeRightSon = nodeRightSon;
        this.satisfiedClauses = satisfiedClauses;
        this.nodePredecessor = nodePredecessor;
        this.nodeWeight = nodeWeight;
    }

    //Getters and Setters
    public int getNodeNumber()
    {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber)
    {
        this.nodeNumber = nodeNumber;
    }

    public int getVarNumber()
    {
        return varNumber;
    }

    public void setVarNumber(int varNumber)
    {
        this.varNumber = varNumber;
    }

    public int getNodeTruthValue()
    {
        return nodeTruthValue;
    }

    public void setNodeTruthValue(int nodeTruthValue)
    {
        this.nodeTruthValue = nodeTruthValue;
    }

    public int getNodeLeftSon()
    {
        return nodeLeftSon;
    }

    public void setNodeLeftSon(int nodeLeftSon)
    {
        this.nodeLeftSon = nodeLeftSon;
    }

    public int getNodeRightSon()
    {
        return nodeRightSon;
    }

    public void setNodeRightSon(int nodeRightSon)
    {
        this.nodeRightSon = nodeRightSon;
    }

    public int getSatisfiedClauses()
    {
        return satisfiedClauses;
    }

    public void setSatisfiedClauses(int satisfiedClauses)
    {
        this.satisfiedClauses = satisfiedClauses;
    }

    public int getNodePredecessor()
    {
        return nodePredecessor;
    }

    public void setNodePredecessor(int nodePredecessor)
    {
        this.nodePredecessor = nodePredecessor;
    }

    public int getNodeWeight()
    {
        return nodeWeight;
    }

    public void setNodeWeight(int nodeWeight)
    {
        this.nodeWeight = nodeWeight;
    }



    @Override
    public String toString() {
        return "TreeNode{" +
                "nodeNumber=" + nodeNumber +
                ", varNumber=" + varNumber +
                ", nodeTruthValue=" + nodeTruthValue +
                ", nodeLeftSon=" + nodeLeftSon +
                ", nodeRightSon=" + nodeRightSon +
                ", satisfiedClauses=" + satisfiedClauses +
                ", nodePredecessor=" + nodePredecessor +
                ", nodeWeight =" + nodeWeight +
                '}';
    }



}
