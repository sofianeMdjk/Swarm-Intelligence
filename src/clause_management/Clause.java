package clause_management;

import java.util.LinkedList;

public class Clause
{
    //This class transform a string from the .cnf file to a formal clause

    private LinkedList<Integer> clause = null;


    public Clause()
    {
        clause = new LinkedList<>();
    }

    private void addToClause(int val)
    {
        if(this.clause != null)
            clause.add(val);
    }

    public Clause toClause(String line)
    {
        String splitResult[] = line.split("\\s+");

        //Transforming the string tab into a clause
        //excluding the last character (the 0)
        int size = splitResult.length-1;
        for(int i=0; i<size;i++)
            this.addToClause(Integer.parseInt(splitResult[i]));

        return this;
    }

    @Override
    public String toString() {
            String print = "";
            for(int i =0; i<clause.size();i++)
                print+=clause.get(i)+" ";

            return print;
    }

    public int getValueOfIndex(int i)
    {
        return this.clause.get(i);
    }
    public int getSize()
    {
        return clause.size();
    }
}
