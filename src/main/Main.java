package main;

import algorithms.AStar;
import algorithms.UniformCost;
import evol_algorithms.SwarmSearch;
import solution_management.Solution;

import java.io.IOException;
import java.util.BitSet;

public class Main {

    public static void main(String[] args) throws IOException {

            FileManager.createFile("./res/stats.txt");
            int satisfied = 0;
            String file = "./res/base2.cnf";
            String line = "";

            SwarmSearch search = new SwarmSearch(file);
            search.startSearch();
            /*System.out.println("--------Astar Algorithm----------\n");

            for (int i = 1; i <= 10; i++)
            {
                System.out.println("Test number : " + i+"\n");

                UniformCost algo = new UniformCost(file);
                satisfied=algo.startSearch();
                System.out.println("Number of satisfied clauses is : "+satisfied+"\n");
                line = i+", "+satisfied;
                FileManager.writeInFile(line);
            }*/

            FileManager.closeFile();
            //Command used to display the graph
            //Runtime.getRuntime().exec("python3 ./res/display_graph.py");*/


    }

}
