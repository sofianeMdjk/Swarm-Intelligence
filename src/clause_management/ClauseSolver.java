package clause_management;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.LinkedList;

public class ClauseSolver
{
    //This class handles solving the clauses

    private String entryFile;
    private String entryInformation;
    private LinkedList<Clause> entryContent=null;
    private int numberOfVariables = 0;
    private int numberOfClauses = 0;


    public ClauseSolver(String file)
    {
        this.entryFile = file;
    }

    public int getNumberOfVariables()
    {
        return this.numberOfVariables;
    }

    public int getNumberOfClauses() {
        return numberOfClauses;
    }

    public LinkedList<Clause> getClauses()
    {
        return this.entryContent;
    }


    public void loadEntry() throws IOException {
        BufferedReader br = this.openFile();
        br = this.readFileHeader(br);

        String line;

            Clause clause;
        entryContent = new LinkedList<>();
        line = br.readLine().trim();
        while (! line.equals("%") )
        {
            clause = new Clause();
            clause.toClause(line);
            this.entryContent.add(clause);
            line = br.readLine();
        }
    }

    public void displayEntryContent()
    {
        int size = entryContent.size();
        for (int i=0 ; i<size ; i++)
        {
            System.out.println(entryContent.get(i));
        }
    }

    private BufferedReader openFile() throws FileNotFoundException
    {
        BufferedReader br = new BufferedReader(new FileReader(this.entryFile));

        return br;
    }

    private BufferedReader readFileHeader(BufferedReader br) throws IOException
    {
        //Reading the 7 first lines for now (will be fixed later i'm adding it as a ticket)
        for(int i=0 ; i<7; i++)
            br.readLine();

        //Reading the information Line
        this.entryInformation = br.readLine();

        //Reading  the clauses
        String entryInfosSplitted[] = entryInformation.split("\\s+");
        numberOfVariables = Integer.parseInt(entryInfosSplitted[2]);
        numberOfClauses = Integer.parseInt(entryInfosSplitted[3]);
        return br;
    }

    public int litteralFrequencyApparition(int litteral)
    {
        int numberOfAppearances = 0;
        for(int i=0 ; i<numberOfClauses ; i++)
        {
            Clause c = entryContent.get(i);
            for(int j=0; j<c.getSize() ; j++)
                if(c.getValueOfIndex(j)==litteral)
                    numberOfAppearances++;
        }
        return numberOfAppearances;
    }

    //Check if a litteral satsfies a clauseç

    private boolean litteralSatisfiesClause(Clause c, int nodeValue)
    {

        int clauseSize = c.getSize();
        //Parsing the clause
        for (int i=0 ; i<clauseSize; i++)
        {
            //If the litteral exists in the clause
            if(c.getValueOfIndex(i) == nodeValue)
                return true;
        }
        return false;
    }

    public LinkedList<Integer> idOfCLausesSatisfiedByLitteral(int litteral)
    {
        //This function returns for each litteral the list of the clauses it satisfies
        LinkedList<Integer> list = new LinkedList<>();
        int size = entryContent.size();

        //parse the list of clauses
        for (int i=0 ; i<size; i++)
        {
            if(this.litteralSatisfiesClause(entryContent.get(i),litteral))
                list.add(i);
        }

        return list;
    }

}
