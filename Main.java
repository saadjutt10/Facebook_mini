import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.effect.DisplacementMap;

public class Main {

    static int V ;

    public static void displayMatrix(ArrayList<ArrayList<Integer>> mat) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(mat.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("===================\n");
    }


    public static  ArrayList<ArrayList<Integer>> reconstructGraph(ArrayList<Profile> allNodes) {
        ConstructGraph g1 = new ConstructGraph(allNodes);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(allNodes.size());
        graph = g1.RetrieveGraph();
        // displayMatrix(graph);
        return graph;
    }

    public static void main(String[] args) {

      /*   // NodeA
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
     

        // Adding Frnds of A
        nodeA.AddNewFriend(nodeE);
        nodeA.AddNewFriend(nodeB);
        // Adding Frnds of B
        nodeB.AddNewFriend(nodeD);
        // Adding Frnds of C
        nodeC.AddNewFriend(nodeE);
        // Adding Frnds of D
        nodeD.AddNewFriend(nodeC); */

//Adding nodes to list and writting list to file
         /*      allNodes.add(nodeA);
        allNodes.add(nodeB);
        allNodes.add(nodeC);
        allNodes.add(nodeD);
        allNodes.add(nodeE); */

        // Writting data to file
        // Main_With_IO.writeData(allNodes, fileName);


        //******************************************************/ Reading data from file

        ArrayList<Profile> allNodes = new ArrayList<>();
        String fileName = "Data.txt";
  
        allNodes = Main_With_IO.getAllNodes(fileName);
        V=allNodes.size();
        // System.out.println("List of frnds created ");
        // System.out.println(allNodes.get(0).getFriends());
        System.out.println("List of frnds created ");
        /*for (Profile i : allNodes.get(0).getFriends()) {
            System.out.println(i);
        } */
        System.out.println("List Tested  ");
        ConstructGraph g1 = new ConstructGraph(allNodes); // Initialising the construct graph object
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(V);
        // graph = g1.RetrieveGraph();
        graph = reconstructGraph(allNodes);
        displayMatrix(graph);

        
          allNodes.get(0).RemoveFriend(allNodes.get(4));
          
          graph = reconstructGraph(allNodes); // Every time a change happends in nodes it needs to be reconstructed
          displayMatrix(graph);
          
          allNodes.get(0).AddNewFriend(allNodes.get(4));
          graph = reconstructGraph(allNodes); // Every time a change happends in nodes it needs to be reconstructed
         displayMatrix(graph);
        

    }

}
