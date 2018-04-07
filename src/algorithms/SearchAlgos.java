package algorithms;


import clause_management.ClauseSolver;
import clause_management.TreeNode;

import java.io.IOException;
import java.util.*;

public abstract class SearchAlgos
{

    private LinkedList<TreeNode> generatedPath = null;
    //We use a hashmap for the close list so we can get

    protected OpenList open = null;
    protected HashMap<Integer,TreeNode> closeQueu = new HashMap<>();
    protected HashMap<Integer,LinkedList<Integer>> satisfiedClausesByLitteral=new HashMap<>();
    protected ClauseSolver clauseSolver ;
    protected LinkedList<Integer> orderOfVariables = new LinkedList<>();
    protected int maxSatisfied = 0;

    protected SearchAlgos(String file) throws IOException {
        clauseSolver = new ClauseSolver(file);
        clauseSolver.loadEntry();
        this.loadSatsfiedClauses();
    }

    protected abstract void manageOpen();
    protected abstract int nodeWeight(TreeNode node);
    protected abstract void sortOpen();


    protected void loadSatsfiedClauses()
    {
        int size = clauseSolver.getNumberOfVariables();
        for(int i=1;i<=size;i++)
        {
            int varNegation = i* -1;
            //adding the variable and its negation
            satisfiedClausesByLitteral.put(i,clauseSolver.idOfCLausesSatisfiedByLitteral(i)) ;
            satisfiedClausesByLitteral.put(varNegation,clauseSolver.idOfCLausesSatisfiedByLitteral(varNegation)) ;
        }

    }


    protected void displaySatisfiedClauses()
    {

        Iterator it = satisfiedClausesByLitteral.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }


    protected void shuffleVariables()
    {
        int numberOfVariables = clauseSolver.getNumberOfVariables();
        for(int i=1; i<=numberOfVariables; i++)
        {
            orderOfVariables.add(i);
        }

        Collections.shuffle(orderOfVariables);
    }


    protected int getSuccessor(int varNumber)
    {
        int index = this.orderOfVariables.indexOf(varNumber);
        index++;
        return orderOfVariables.get(index);
    }


    private TreeNode getPredecessorNodeFromClose(int nodeID)
    {
        return closeQueu.get(nodeID);
    }

    private LinkedList<TreeNode> constructPathFromPred(TreeNode node)
    {
        generatedPath = new LinkedList<>();
        generatedPath.add(node);
        int predecessor = node.getNodePredecessor();
        while(predecessor != 0)
        {
            TreeNode treeNode = getPredecessorNodeFromClose(predecessor);
            generatedPath.add(treeNode);
            predecessor = treeNode.getNodePredecessor();
        }
        return generatedPath;
    }

    protected int getNumberOfClausesFromPath(TreeNode node)
    {
        LinkedList<TreeNode> path = constructPathFromPred(node);
        LinkedList<Integer> clauseIds = new LinkedList<>();
        int size = path.size();
        int variable;
        int clauseNumber = clauseSolver.getNumberOfClauses();
        for(int i=0 ; i<size ; i++)
        {
            variable = path.get(i).getVarNumber() * path.get(i).getNodeTruthValue();
            LinkedList<Integer> satisfied = clauseSolver.idOfCLausesSatisfiedByLitteral(variable);

            for(Integer x : satisfied)
            {
                if(!clauseIds.contains(x))
                    clauseIds.add(x);
            }
            if(clauseIds.size() == clauseNumber)
                return clauseNumber;
        }
        return clauseIds.size();
    }

    protected int pathDepth(TreeNode node)
    {
        LinkedList<TreeNode> path = constructPathFromPred(node);
        return path.size();
    }


    //Method used to return the maximum satisfied clause
    protected boolean getMaxSatisfied (int newSatisfied)
    {
        if(this.maxSatisfied<newSatisfied) {
            this.maxSatisfied = newSatisfied;
            return true;
        }
            return false;
    }


   public int startSearch() {
        int nodeId=1;
        int numOfSatisfiedClauses;

        //
        manageOpen();

        //we start by putting the first elements in the Open List
        int varNumber = orderOfVariables.get(0);
        int varNegation = varNumber * -1;

        //Creating the first 2 nodes
        int numberOfSatisfiedClauses = satisfiedClausesByLitteral.get(varNumber).size();
        TreeNode firstNode = new TreeNode
                (nodeId++,varNumber,1,0,0,numberOfSatisfiedClauses,0,numberOfSatisfiedClauses);
        //Addind a weight to the node
        firstNode.setNodeWeight(nodeWeight(firstNode));

        numberOfSatisfiedClauses = satisfiedClausesByLitteral.get(varNegation).size();
        TreeNode secondNode = new TreeNode
                (nodeId++,varNumber,-1,0,0,numberOfSatisfiedClauses,0,numberOfSatisfiedClauses);
        //Addind a weight to the node
        secondNode.setNodeWeight(nodeWeight(secondNode));

        open.addNode(firstNode);
        open.addNode(secondNode);

        //Preparing timeout
        long startTime = System.currentTimeMillis()/1000;
        long timeSpent;

        while(!open.isEmpty() && maxSatisfied<clauseSolver.getNumberOfClauses())
        {
            //Ordering Open before using it
            sortOpen();

            //getting the first node from open
            TreeNode parent = open.removeNode();

            //adding the parent to close
            closeQueu.put(parent.getNodeNumber(),parent);

            int pathDepth = pathDepth(parent);

            if(pathDepth<clauseSolver.getNumberOfVariables())
            {

                //Checking how many clauses does it satisfy
                numOfSatisfiedClauses = getNumberOfClausesFromPath(parent);
                if(getMaxSatisfied(numOfSatisfiedClauses))
                     System.out.println("Satisfies : "+maxSatisfied);

                //Generating successor nodes
                varNumber = getSuccessor(parent.getVarNumber());

                //Adding the left Son to Open
                numberOfSatisfiedClauses = satisfiedClausesByLitteral.get(varNumber).size();
                TreeNode leftSon = new TreeNode
                        (nodeId++, varNumber, 1, 0, 0, numberOfSatisfiedClauses, parent.getNodeNumber());
                //Calculating f function
                int weight = nodeWeight(leftSon);
                leftSon.setNodeWeight(weight);
                open.addNode(leftSon);

                //Adding the right Son to Open
                numberOfSatisfiedClauses = satisfiedClausesByLitteral.get(varNumber * -1).size();
                TreeNode rightSon = new TreeNode
                        (nodeId++, varNumber, -1, 0, 0, numberOfSatisfiedClauses, parent.getNodeNumber());

                //Calculating f function
                weight = nodeWeight(rightSon);
                rightSon.setNodeWeight(weight);
                open.addNode(rightSon);


                //Updating the parent nodes successors
                parent.setNodeLeftSon(leftSon.getNodeNumber());
                parent.setNodeRightSon(rightSon.getNodeNumber());

            }

            //timeout code
            timeSpent = System.currentTimeMillis()/1000 - startTime;
            if(timeSpent > 30)
                return this.maxSatisfied;
        }

        return this.maxSatisfied;

    }





}
