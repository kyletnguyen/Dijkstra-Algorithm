/*-
 *****************************************
 * Group 2
 * Kyle Nguyen
 * 
 * COMP 282
 * Spring 2018
 * Dr. Wen-Chin Hsu
 * M/W 9:30 A.M - 10:45 A.M
 * 
 * Project 2: 
 * 
 * Group2_Driver.java
 * Version 4.0
 * 
 * The program works as expected as it
 * reads in a file given the file is
 * properly formatted such that the 
 * node's name is a character and the
 * distance between them is an integer.
 * 
 * It creates a graph based on the file
 * and calculates the shortest path 
 * between two nodes.
 * 
 * Following all the specifications,
 * it creates a table where the table
 * does not display paths of distance 0
 * nor does it display nodes that are
 * unreachable.
 ****************************************/
public class Group2_Driver {
	public static void main(String[] args) {
		Group2_Graph mahGraph = new Group2_Graph(new Group2_ReadFile("city3.txt")); // Pass in text file to be read for 1rst graph
		Group2_Graph mahGraph2 = new Group2_Graph(new Group2_ReadFile("city3.txt"));// Pass in text file to be read for 2nd graph

		mahGraph.shortestPath(mahGraph.getVertex(0)); // Get the shortest path starting at "a"
		mahGraph2.shortestPath(mahGraph2.getVertex(1)); // Get the shortest path starting at "b"

		displayEverything(mahGraph, mahGraph2); // Display the paths to all the nodes given both graphs
		System.out.println();
		displayTable(mahGraph, mahGraph2); // Display the table to nodes that have a definite distance/path
	}

	public static void displayEverything(Group2_Graph mahGraph, Group2_Graph mahGraph2) {
		System.out.println("Starting at vertex \"a\". . .");
		for (Group2_Vertex v : mahGraph.getGraph()) {
			if (v.getDist() == Integer.MAX_VALUE)
				System.out.format("%s%s\t%s\t%s\t", "Vertex:  ", v.toString(), "Dist:  INF", "Path:  ");

			else
				System.out.format("%s%s\t%s%d\t%s\t", "Vertex:  ", v.toString(), "Dist:  ", v.getDist(), "Path:  ");

			for (Group2_Vertex pathvert : v.getPath()) {
				System.out.print(pathvert);
			}

			if (v.getDist() == Integer.MAX_VALUE)
				System.out.println("No path");

			else
				System.out.println("" + v.toString());
		}

		System.out.println();
		System.out.println();

		System.out.println("Starting at vertex \"b\". . .");
		for (Group2_Vertex v : mahGraph2.getGraph()) {
			if (v.getDist() == Integer.MAX_VALUE)
				System.out.format("%s%s\t%s\t%s\t", "Vertex:  ", v.toString(), "Dist:  INF", "Path:  ");

			else
				System.out.format("%s%s\t%s%d\t%s\t", "Vertex:  ", v.toString(), "Dist:  ", v.getDist(), "Path:  ");

			for (Group2_Vertex pathvert : v.getPath()) {
				System.out.print(pathvert);
			}

			if (v.getDist() == Integer.MAX_VALUE)
				System.out.println("No path");

			else
				System.out.println("" + v.toString());
		}
	}

	public static void displayTable(Group2_Graph mahGraph, Group2_Graph mahGraph2) {
		System.out.println("------------------------------------------------------------------------------");
		System.out.format("| %s%44s     |", "   Using \"a\" as the origin", "Using \"b\" as the origin");
		System.out.println("\n|                                                                            |");
		System.out.format("| %s%13s%9s%22s%13s%9s  |\n", " Vertex", "Distance", "Path", "Vertex", "Distance", "Path");
		System.out.println("------------------------------------------------------------------------------");

		int indexG1 = 0; // Index for graph 1
		int indexG2 = 0; // Index for graph 2
		boolean fixLine = false;

		while (!(indexG1 == mahGraph.getGraph().size() && indexG2 == mahGraph2.getGraph().size())) { // While either graph has elements to loop through
			if (indexG1 < mahGraph.getGraph().size()) { // If the current index for graph1 is smaller than the size of the graph
				while ((mahGraph.getVertex(indexG1).getDist() == 0) // While the current index is not itself or has an unreachable path increment the counter
						|| (mahGraph.getVertex(indexG1).getDist() == Integer.MAX_VALUE)) {
					indexG1 += 1;

					if (indexG1 == mahGraph.getGraph().size()) // Check after incrementing whether you've reached the end of the list
						break; // The index is equal to the size of the list, so break
				}

				if (indexG1 == mahGraph.getGraph().size()) { // If the index is equal to the size of the list break
					break;
				}

				else {
					System.out.format("%6s%13d", mahGraph.getVertex(indexG1).toString(),
							mahGraph.getVertex(indexG1).getDist()); // Display the vertex at the current index and display it's distance
					System.out.print("\t   ");

					for (Group2_Vertex pathV : mahGraph.getVertex(indexG1).getPath()) { // Display the corresponding path of the current index
						System.out.format("%s", pathV);
					}

					System.out.print(mahGraph.getVertex(indexG1).toString() + "    "); // Display the current node (destination node) in the path
					indexG1 += 1; // Increment the counter

					if (indexG2 == mahGraph2.getGraph().size()) { // If there are no elements left in the other graph
						System.out.println();
					}
				}
			}

			if (indexG2 < mahGraph2.getGraph().size()) { // If the current index for graph2 is smaller than the size of the graph
				while ((mahGraph2.getVertex(indexG2).getDist() == 0)
						|| (mahGraph2.getVertex(indexG2).getDist() == Integer.MAX_VALUE)) { // While the current index is not itself or has an unreachable path increment the counter
					indexG2 += 1;

					if (indexG2 == mahGraph2.getGraph().size()) // Check after incrementing whether you've reached the end of the list
						break; // Break if you do
				}

				if (indexG2 == mahGraph2.getGraph().size()) { // Check to see if you've reached the end of the list
					break; // Break if you do
				}

				else {
					if (indexG1 == mahGraph.getGraph().size()) { // Finished first graph
						if (!fixLine) { // Add the data from where the first graph finished
							System.out.format("\t\t %s%13d", mahGraph2.getVertex(indexG2).toString(),
									mahGraph2.getVertex(indexG2).getDist());

							System.out.print("\t       ");
							fixLine = true; // Toggle to true since we've fixed the only line that would cause problems in the table format
						}

						else {
							System.out.format("\t\t\t\t\t%10s%13d", mahGraph2.getVertex(indexG2).toString(),
									mahGraph2.getVertex(indexG2).getDist()); // Display the data since the first graph finished

							System.out.print("\t       ");
						}
					}

					else {
						System.out.format("\t\t %s%13d", mahGraph2.getVertex(indexG2).toString(),
								mahGraph2.getVertex(indexG2).getDist()); // Display the data while there are elements in both graphs

						System.out.print("\t       ");
					}

					for (Group2_Vertex pathV : mahGraph2.getVertex(indexG2).getPath()) { // Display the path for the current node
						System.out.format("%s", pathV);
					}

					System.out.print(mahGraph2.getVertex(indexG2).toString());  // Display the current node (destination node) in the path
					indexG2 += 1; // Increment the counter

					System.out.println();
				}
			}
		}
	}
}