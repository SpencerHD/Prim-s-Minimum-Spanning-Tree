import java.io.*;
import java.util.Scanner;

public class homework3 {
	
	// Total number of nodes
    private static final int nodes = 6;
	
	public static void main (String[] args) throws FileNotFoundException {
    	
        homework3 tree = new homework3();
        
        Scanner input = new Scanner(new File("prob2input.txt"));
    	
        // Initialize a graph to take the data from file
        int [][] graph = new int[nodes][nodes];
        
        input = new Scanner(new File("prob2input.txt"));
       
        // Read in the matrix from a file
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
            	if (input.hasNextInt()) {
            	  graph[i][j] = input.nextInt();
                }
            }
         }
        
        //Print the taken matrix
        System.out.println("The taken matrix from file:");
        for (int i = 0; i < graph.length; i++) {
        	for (int j = 0; j < graph.length; j++) {
        		System.out.print(graph[i][j] + " ");
        	}
        	System.out.println();
        }
        System.out.println();
        
        System.out.println("The Mimimum Spanning Tree using Prim's Algorithm:");
        System.out.println("-------------------------------------------------");
        
        // Print the solution 
        tree.prim(graph); 
    }  
    
	// Find the minimum value for the node with the nodes not yet included in the tree
    int min(int values[], Boolean includedNodes[]) 
    { 
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int i = 0; i < nodes; i++) {
            if (includedNodes[i] == false && values[i] < min) 
            { 
                min = values[i]; 
                min_index = i; 
            } 
        }
        return min_index; 
    } 
  
    // Print the outcome
    void print(int newGraph[], int n, int graph[][], int finalGraph[][]) {
    	for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
            		System.out.print(finalGraph[i][j] + " ");
            }
            System.out.println();
        }
    } 
  
    // Find the minimum spanning tree
    void prim(int graph[][]) { 
        int newGraph[] = new int[nodes]; 
        int[][] finalGraph = new int[nodes][nodes];
  
        int values[] = new int[nodes]; 
  
        // Show nodes not included in graph
        Boolean includedNodes[] = new Boolean[nodes]; 
  
        // Set all keys to be infinite
        for (int i = 0; i < nodes; i++) { 
        	
        	values[i] = Integer.MAX_VALUE; 
            includedNodes[i] = false; 
            
        } 
  
        // Start from the first node
        values[0] = 0; 
        newGraph[0] = 0;
  
        for (int count = 0; count < nodes - 1; count++) { 
        	
            int u = min(values, includedNodes); 
  
            includedNodes[u] = true; 
            
            for (int v = 0; v < nodes; v++) {

                if (graph[u][v] != 0 && includedNodes[v] == false && graph[u][v] < values[v]) { 
                	
                	newGraph[v] = u; 
                	values[v] = graph[u][v];
                    
                }
            }
        }
        
        // Only use the minimum values for each node, drop all other values
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
            	if (graph[i][j] == graph[i][newGraph[i]]) {
            		finalGraph[i][j] = graph[i][j];
            	}
            	else if (graph[i][j] == graph[j][newGraph[j]]) {
            		finalGraph[i][j] = graph[i][j];
            	}
            	else if (graph[i][j] == graph[i][newGraph[j]]) {
            		finalGraph[i][j] = 0;
            	}
            }
        }
        
        // Print the new matrix
        print(newGraph, nodes, graph, finalGraph); 
    }
}