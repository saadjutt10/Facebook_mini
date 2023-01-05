import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.effect.DisplacementMap;

public class Main {

    static int V = 5;

    public static void displayMatrix(int mat[][]) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===================\n");
    }

    public static int[][] reconstructGraph(ArrayList<Profile> allNodes) {
        ConstructGraph g1 = new ConstructGraph(allNodes);
        int[][] graph = new int[allNodes.size()][allNodes.size()];
        graph = g1.RetrieveGraph();
        // displayMatrix(graph);
        return graph;
    }

    public static void main(String[] args) {

        // NodeA
        Address AddA = new Address("Islamabad", 3, 10);
        Profile nodeA = new Profile("Saad", 19, AddA);
        // NodeB
        Address AddB = new Address("Islamabad", 2, 10);
        Profile nodeB = new Profile("Ammar", 18, AddB);
        // NodeC
        Address AddC = new Address("Islamabad", 5, 10);
        Profile nodeC = new Profile("Ali", 19, AddC);
        // NodeD
        Address AddD = new Address("Islamabad", 31, 10);
        Profile nodeD = new Profile("Bakr", 21, AddD);
        // NodeE
        Address AddE = new Address("Islamabad", 13, 10);
        Profile nodeE = new Profile("Huzaifa", 20, AddE);
        String fileName = "Data.txt";

        // Adding Frnds of A
        nodeA.AddNewFriend(nodeE);
        nodeA.AddNewFriend(nodeB);
        // Adding Frnds of B
        nodeB.AddNewFriend(nodeD);
        // Adding Frnds of C
        nodeC.AddNewFriend(nodeE);
        // Adding Frnds of D
        nodeD.AddNewFriend(nodeC);

        // Reading data from file
        ArrayList<Profile> allNodes = new ArrayList<>();

        allNodes.add(nodeA);
        allNodes.add(nodeB);
        allNodes.add(nodeC);
        allNodes.add(nodeD);
        allNodes.add(nodeE);

        // Writting data to file
        Main_With_IO.writeData(allNodes, fileName);
        // allNodes = Main_With_IO.getAllNodes(fileName);
        // System.out.println("List of frnds created ");
        System.out.println(allNodes.get(0).getFriends());
        System.out.println("List of frnds created ");
        for (Profile i : allNodes.get(0).getFriends()) {
            System.out.println(i.getName());
        }
        ConstructGraph g1 = new ConstructGraph(allNodes); // Initialising the construct graph object
        int[][] graph = new int[allNodes.size()][allNodes.size()];
        // graph = g1.RetrieveGraph();
        graph = reconstructGraph(allNodes);
        displayMatrix(graph);

        /*
         * nodeA.RemoveFriend(nodeE);
         * 
         * graph = reconstructGraph(allNodes); // Every time a change happends in nodes
         * it needs to be reconstructed
         * displayMatrix(graph);
         * 
         * nodeE.AddNewFriend(nodeA);
         * graph = reconstructGraph(allNodes); // Every time a change happends in nodes
         * it needs to be reconstructed
         * displayMatrix(graph);
         */

    }

}
