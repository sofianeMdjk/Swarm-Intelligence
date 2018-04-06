package evol_algorithms;

import clause_management.ClauseSolver;
import solution_management.Solution;

import java.io.IOException;
import java.util.LinkedList;

public class SwarmSearch
{
    private ClauseSolver clauseSolver;
    private Solution solution;

    public SwarmSearch(String file) throws IOException {
        clauseSolver = new ClauseSolver(file);
        clauseSolver.loadEntry();
        solution = new Solution(clauseSolver.getNumberOfVariables());
    }


    //REMINDER : LOOP FROM 0 TO <= NUMOFVARS
    private int satisfiedClausesBySolution(Solution sol)
    {
        int litteral;
        LinkedList<Integer> clausesId = new LinkedList<>();
        LinkedList<Integer> temp = new LinkedList<>();
        for(int i=0 ; i<sol.cardinality() ; i++)
        {
            //For each bit (which is a litteral) get number of satisfied clauses
            litteral = (i+1);
            if(sol.get(i)==false)
                litteral *= -1;

            temp = clauseSolver.idOfCLausesSatisfiedByLitteral(litteral);

            //Or operation between clausesId and temp

            for(Integer x : temp)
            {
                if(!clausesId.contains(x))
                    clausesId.add(x);
            }
        }

        return clausesId.size();
    }

    public void startSearch()
    {
        int size = clauseSolver.getNumberOfVariables();
        Solution solution = new Solution(size);
        for(int i=0 ; i<size ; i++)
            solution.set(i);

        System.out.println(solution.getSize());
        solution.display();
        LinkedList<Solution> neighbors = solution.neighborhood();

        //System.out.println(this.satisfiedClausesBySolution(solution));
    }





}
