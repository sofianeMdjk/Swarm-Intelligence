package solution_management;

import java.util.BitSet;
import java.util.LinkedList;

public class Solution
{
    private BitSet bitset = null;


    public Solution(int size)
    {
        bitset = new BitSet(size);
    }

    public Solution(int size, int integerValue)
    {
        bitset = new BitSet(size);
        bitset = Bits.convert(integerValue);
    }

    public int getSize()
    {
        return bitset.length();
    }
    public int cardinality()
    {
        return bitset.cardinality();
    }

    public Boolean get(int index)
    {
        return bitset.get(index);
    }

    public void set(int index)
    {
        bitset.set(index);
    }
    public void set (int index, Boolean truthValue)
    {
        bitset.set(index,truthValue);
    }

    public void flip(int index)
    {
        bitset.flip(index);
    }

    public void assign(Solution sol)
    {
        for(int i=0 ; i<sol.getSize() ; i++)
            bitset.set(i,sol.get(i));
    }


    public static long convert(BitSet bits) {
        long value = 0L;
        for (int i = 0; i < bits.length(); ++i) {
            value += bits.get(i) ? (1L << i) : 0L;
        }
        return value;
    }


    public int distance(Solution oSolution)
    {
        //SOlution must obviously have the size
        if(this.getSize() != oSolution.getSize())
            return -1;

        int hammingDistance = 0;
        //Itterate through solutions
        int size = this.getSize();
        for(int i=0 ; i<size ; i++)
        {
            if(! this.get(i).equals(oSolution.get(i)))
                hammingDistance++;
        }
        return hammingDistance ;
    }

    public int similarity(Solution oSolution)
    {
        return this.getSize() - this.distance(oSolution);
    }

    public int diversity(LinkedList<Solution> solutions)
    {
        int minimumDistance = 0;
        int size = solutions.size();
        if(solutions!=null && solutions.size()>0)
            minimumDistance = this.distance(solutions.get(0));

        for(int i=1; i<size ; i++)
        {
            int newDistance = this.distance(solutions.get(i));
            if(newDistance < minimumDistance)
                minimumDistance = newDistance;
        }
        return minimumDistance;
    }

    public LinkedList<Solution> neighborhood ()
    {
        LinkedList<Solution> neighbors = new LinkedList<>();
        Solution temp=null;
        for(int i=0; i<this.getSize(); i++)
        {
            temp = new Solution(this.cardinality());
            temp.assign(this);

            //Flip the value of the bit
            temp.flip(i);

            neighbors.add(temp);
        }
        return neighbors;
    }

    public void display()
    {
        System.out.println(this.bitset);
    }
}
