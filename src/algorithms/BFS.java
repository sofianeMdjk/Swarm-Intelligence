package algorithms;


import clause_management.TreeNode;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends SearchAlgos
{

    public BFS(String file) throws IOException {
        super(file);
        this.shuffleVariables();
    }

    @Override
    protected void manageOpen() {
        //FIFO Management
        this.open = new OpenList(0);
    }

    @Override
    protected int nodeWeight(TreeNode node) {
        return 0;
    }

    @Override
    protected void sortOpen() { };


   /* public int startSearch() {
        int nodeId=1;
        int numOfSatisfiedClauses = 0;


        //we start by putting the first elements in the Open List
        int varNumber = orderOfVariables.get(0);
        int varNegation = varNumber * -1;


        int numberOfSatisfiedClauses = satisfiedClausesByLitteral.get(varNumber).size();
        TreeNode firstNode = new TreeNode
                (nodeId++,varNumber,1,0,0,numberOfSatisfiedClauses,0);
        numberOfSatisfiedClauses = satisfiedClausesByLitteral.get(varNegation).size();
        TreeNode secondNode = new TreeNode
                (nodeId++,varNumber,-1,0,0,numberOfSatisfiedClauses,0);

        openQueu.add(firstNode);
        openQueu.add(secondNode);


        //Preparing timeout
        long startTime = System.currentTimeMillis()/1000;
        long timeSpent;

        while(!openQueu.isEmpty() && maxSatisfied<clauseSolver.getNumberOfClauses())
        {
                //Cheking how many clauses does the path satisfies

                //getting the first node from open
                TreeNode parent = openQueu.remove();

                //adding the parent to close


                int pathDepth = pathDepth(parent);

                if(pathDepth<clauseSolver.getNumberOfVariables()) {
                    closeQueu.put(parent.getNodeNumber(),parent);
                    //Checking how many clauses does it satisfy
                     numOfSatisfiedClauses = getNumberOfClausesFromPath(parent);
                    getMaxSatisfied(numOfSatisfiedClauses);


                    //Generating successor nodes
                    varNumber = getSuccessor(parent.getVarNumber());

                    //Adding the left Son to Open
                    numberOfSatisfiedClauses = satisfiedClausesByLitteral.get(varNumber).size();
                    TreeNode leftSon = new TreeNode
                            (nodeId++, varNumber, 1, 0, 0, numberOfSatisfiedClauses, parent.getNodeNumber());

                    openQueu.add(leftSon);

                    //Adding the right Son to Open
                    numberOfSatisfiedClauses = satisfiedClausesByLitteral.get(varNumber * -1).size();
                    TreeNode rightSon = new TreeNode
                            (nodeId++, varNumber, -1, 0, 0, numberOfSatisfiedClauses, parent.getNodeNumber());
                    openQueu.add(rightSon);


                    //Updating the parent nodes successors
                    parent.setNodeLeftSon(leftSon.getNodeNumber());
                    parent.setNodeRightSon(rightSon.getNodeNumber());

                }

                //timeout code
            timeSpent = System.currentTimeMillis()/1000 - startTime;
            if(timeSpent > 600)
                return this.maxSatisfied;
        }

        return this.maxSatisfied;

     }*/


}
