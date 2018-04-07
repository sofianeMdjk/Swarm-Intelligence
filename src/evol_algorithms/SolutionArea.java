package evol_algorithms;

import solution_management.Solution;

import java.lang.reflect.Array;
import java.util.*;

public abstract class SolutionArea
{
    private HashMap<Solution,Integer> danceTable;
    private LinkedList<Solution> tabooList ;
    private Solution Sref;
    private int numberOfBees;
    private int flip;

    public SolutionArea(int bees,int flipParam)
    {
        danceTable = new HashMap<>();
        tabooList = new LinkedList<>();
    }

    //Gets the best solution from dance and adds it to the taboo table
    protected void fetchDance()
    {
        Sref = this.maxDanceValue();
        tabooList.add(Sref);

    }

    private Solution maxDanceValue()
    {
        Solution maxKey = null;
        int maxValue = 0;
        for (Map.Entry<Solution,Integer> entry : danceTable.entrySet())
        {
            if(entry.getValue()>maxValue)
            {
                maxKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        return maxKey;
    }

    //Diversity from Sref and the taboo list
    public int srefDiversity()
    {
        return Sref.diversity(tabooList);
    }

    //Generate solutions from Sref and flip
    public LinkedList<Solution> solutionsFromSref(int n)
    {
        LinkedList<Solution> newSolutions = new LinkedList<>();
        Solution temp ;
        int distance = (int)(n/flip);
        int h=0;
        int p;
        if(Sref!=null)
        {
            //Generating a solution for each bee
            for (int i = 0; i < numberOfBees; i++)
            {
                temp = Sref;
                p = 0;
                    do
                    {
                        temp.flip(flip * p);
                        p++;
                    }while(flip*p<=n);

                    newSolutions.add(temp);

            }
        }
        return newSolutions;
    }

}
