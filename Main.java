import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.effect.DisplacementMap;

public class Main {

    static String fileName = "Data.txt";

    static int V;// No of nodes in graph

    public static ArrayList<ArrayList<Integer>> addNode(String city, int streetNo, int houseNo, String name, int age,
            ArrayList<Profile> allNodes) {
        Address AddA = new Address(city, streetNo, houseNo);
        Profile nodeA = new Profile(name, age, AddA);
        allNodes.add(nodeA);
        ConstructGraph g1 = new ConstructGraph(allNodes); // Initialising the construct graph object
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(++V);
        Main_With_IO.writeData(allNodes, fileName);
        return graph = reconstructGraph(allNodes);
    }

    public static ArrayList<ArrayList<Integer>> addNode(Profile p, ArrayList<Profile> allNodes) {
        allNodes.add(p);
        ConstructGraph g1 = new ConstructGraph(allNodes); // Initialising the construct graph object
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(++V);
        Main_With_IO.writeData(allNodes, fileName);
        return graph = reconstructGraph(allNodes);

    }

    public static ArrayList<ArrayList<Integer>> removeNode(Profile p, ArrayList<Profile> allNodes) {
        allNodes.remove(p);
        ConstructGraph g1 = new ConstructGraph(allNodes); // Initialising the construct graph object
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(--V);
        Main_With_IO.writeData(allNodes, fileName);
        return graph = reconstructGraph(allNodes);
    }

    public static void addFriend(ArrayList<ArrayList<Integer>> graph, ArrayList<Profile> allNodes, String nodeA,
            String nodeB) {

        int A = 0;
        Profile tempA = null;
        int B = 0;
        Profile tempB = null;
        for (Profile i : allNodes) { // Findig Index of A node
            if (i.getName().equals(nodeA)) {
                tempA = i;
                break;
            }
            A++;
        }
        for (Profile j : allNodes) { // Findig Index of B node
            if (j.getName().equals(nodeB)) {
                tempB = j;
                break;
            }
            B++;
        }

        if (allNodes.contains(tempA) && allNodes.contains(tempB)) {

            allNodes.get(A).AddNewFriend(tempB);
        } else {
            System.out.println("One or both nodes are not present in our system");
            System.out.println(tempA);
            System.out.println(tempB);
            return;
        }

        System.out.println("Size of list is " + allNodes.size());
        Main_With_IO.writeData(allNodes, fileName);// Updating data from file
        ArrayList<Profile> tempList = Main_With_IO.getAllNodes(fileName);
        allNodes = tempList;
        graph = reconstructGraph(allNodes);
    }

    public static void removeFriend(ArrayList<ArrayList<Integer>> graph, ArrayList<Profile> allNodes, String nodeA,
            String nodeB) {

        int A = 0;
        Profile tempA = null;
        int B = 0;
        Profile tempB = null;
        for (Profile i : allNodes) { // Findig Index of A node
            if (i.getName().equals(nodeA)) {
                tempA = i;
                break;
            }
            A++;
        }
        for (Profile j : allNodes) { // Findig Index of B node
            if (j.getName().equals(nodeB)) {
                tempB = j;
                break;
            }
            B++;
        }

        if (allNodes.contains(tempA) && allNodes.contains(tempB)) {

            allNodes.get(A).RemoveFriend(tempB);
        } else {
            System.out.println("One or both nodes are not present in our system");
            System.out.println(tempA);
            System.out.println(tempB);
            return;
        }

        System.out.println("Size of list is " + allNodes.size());
        Main_With_IO.writeData(allNodes, fileName);// Updating data from file
        allNodes=Main_With_IO.getAllNodes(fileName);
        graph = reconstructGraph(allNodes);
    }

    public static void displayMatrix(ArrayList<ArrayList<Integer>> mat) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(mat.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("===================\n");
    }

    public static ArrayList<ArrayList<Integer>> reconstructGraph(ArrayList<Profile> allNodes) {
        ConstructGraph g1 = new ConstructGraph(allNodes);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(allNodes.size());
        graph = g1.RetrieveGraph();
        // displayMatrix(graph);
        return graph;
    }

    public static void main(String[] args) {
        /*
         * // NodeA
         * Address AddA = new Address("Islamabad", 3, 10);
         * Profile nodeA = new Profile("Saad", 19, AddA);
         * // NodeB
         * Address AddB = new Address("Islamabad", 2, 10);
         * Profile nodeB = new Profile("Ammar", 18, AddB);
         * // NodeC
         * Address AddC = new Address("Islamabad", 5, 10);
         * Profile nodeC = new Profile("Ali", 19, AddC);
         * // NodeD
         * Address AddD = new Address("Islamabad", 31, 10);
         * Profile nodeD = new Profile("Bakr", 21, AddD);
         * // NodeE
         * Address AddE = new Address("Islamabad", 13, 10);
         * Profile nodeE = new Profile("Huzaifa", 20, AddE);
         * 
         * // Adding Frnds of A
         * nodeA.AddNewFriend(nodeE);
         * nodeA.AddNewFriend(nodeB);
         * // Adding Frnds of B
         * nodeB.AddNewFriend(nodeD);
         * // Adding Frnds of C
         * nodeC.AddNewFriend(nodeE);
         * // Adding Frnds of D
         * nodeD.AddNewFriend(nodeC);
         */
        // Adding nodes to list and writting list to file
        ArrayList<Profile> allNodes = new ArrayList<>();
        /*
         * allNodes.add(nodeA);
         * allNodes.add(nodeB);
         * allNodes.add(nodeC);
         * allNodes.add(nodeD);
         * allNodes.add(nodeE);
         * 
         * // Writting data to file
         * Main_With_IO.writeData(allNodes, fileName)
         */;

        // ****************************************************** Reading data from file

        allNodes = Main_With_IO.getAllNodes(fileName);
        V = allNodes.size();
        // System.out.println("List of frnds created ");
        // System.out.println(allNodes.get(0).getFriends());
        System.out.println("List of frnds created ");
        /*
         * for (Profile i : allNodes.get(0).getFriends()) {
         * System.out.println(i);
         * }
         */
        // System.out.println("List Tested ");
        ConstructGraph g1 = new ConstructGraph(allNodes); // Initialising the construct graph object
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(V);
        // graph = g1.RetrieveGraph();
        graph = reconstructGraph(allNodes);
        displayMatrix(graph);

        /*
         * allNodes.get(0).RemoveFriend(allNodes.get(4));
         * 
         * graph = reconstructGraph(allNodes); // Every time a change happends in nodes
         * it needs to be reconstructed
         * displayMatrix(graph);
         * 
         * allNodes.get(0).AddNewFriend(allNodes.get(4));
         * graph = reconstructGraph(allNodes); // Every time a change happends in nodes
         * it needs to be reconstructed
         * displayMatrix(graph);
         */

        // ******************************************************Removing and Adding new
        // Nodes */
        // graph = addNode("Pindi", 5, 11, "Saedi", 19, allNodes);
        // Profile p=allNodes.get(allNodes.size()-1);
        // displayMatrix(graph);
        // Profile p=new Profile("Abdul Rehman", 19, null);
        graph=removeNode(allNodes.get(allNodes.size()-1), allNodes);
        // addNode(p, allNodes);
        // addFriend(graph, allNodes, "Saad", "Ali");     ////Havving issue here Not updating graph when method is called but is updated when re run the program
        displayMatrix(graph);
        // System.out.println(allNodes.get(allNodes.size()-3));
    }

}
