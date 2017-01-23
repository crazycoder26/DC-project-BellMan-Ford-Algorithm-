package algorithms;

import algorithms.BellmanFord;
import java.util.Scanner;
import java.io.*;

public class Driver {
	static int n = 0;
  public static void main(String[] args) throws IOException {
    String INPUT_FILE = "./connectivity.txt";

    // Read input files
    
    int leader = -1;
    int[][] connectivity = null;
    Scanner inputScanner = null;
    String result = null;
    try {
      inputScanner = new Scanner(new File(INPUT_FILE));
    } catch (FileNotFoundException e) {
      System.err.println(INPUT_FILE + " not found!");
      return;
    }

    try {
    	inputScanner = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
    	int i = 0;
    	int row =0;
    	String[] val = null;
        while (inputScanner.hasNext()) {
            result =  inputScanner.nextLine();
            if(i == 0)
            {
            	if(result.contains(",")){
            	val = result.split(",");
            	}
            	else 
            	{
            		System.err.println(INPUT_FILE + " should begin with the number of processes, followed by whitespace, followed by the leader process ID.");
            	      return;
            	}
            	if(result.matches(".*\\d.*")){
            		n = Integer.valueOf(val[0]);
                	leader = Integer.valueOf(val[1]);
            		} else{
            			System.err.println(INPUT_FILE + " should begin with the number of processes.");
            		      return;
            		}
            	
            	connectivity = new int[n][n];
            	i=1;
            }
            else
            {
            	String[] value = result.split(" ");
            		for(int col =0; col< n; col++)
            		{
            			int x = Integer.parseInt(value[col]);
            			connectivity[row][col] = x;
            			if(connectivity[row][col] < -1)
            			{
            				System.err.println(INPUT_FILE + " should only contain values that represent nonnegative edge weights or -1 to represent no edge");
            			    return;
            			}
            		}
            		row++;
            }
            
        }
    } catch (final IOException ex) {
        ex.printStackTrace();
    } 
    
    
    // Run Bellman-Ford and print tree
    BellmanFord bf = new BellmanFord(n, leader, connectivity);
    bf.start();
    bf.printTree();
  }
}
