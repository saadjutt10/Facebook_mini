import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;

import javafx.scene.effect.DisplacementMap;

public class Main {

    static String fileName = "Data.txt";

    static int V;// No of nodes in graph

    public static ArrayList<ArrayList<Integer>> addNode(String city, int streetNo, int houseNo, String name, int age,
            String gender, String cnic, String pswrd, String username,
            ArrayList<User> allNodes) {
        Address AddA = new Address(city, streetNo, houseNo);
        User nodeA = new User(name, age, gender, cnic, pswrd, username, AddA);
        allNodes.add(nodeA);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(++V);
        Main_With_IO.writeData(allNodes, fileName);
        return graph = ConstructGraph.constGraph(allNodes);
    }

    public static ArrayList<ArrayList<Integer>> addNode(User p, ArrayList<User> allNodes) {
        allNodes.add(p);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(++V);
        Main_With_IO.writeData(allNodes, fileName);
        return graph = ConstructGraph.constGraph(allNodes);

    }

    public static ArrayList<ArrayList<Integer>> removeNode(User p, ArrayList<User> allNodes) {
        allNodes.remove(p);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(--V);
        Main_With_IO.writeData(allNodes, fileName);
        return graph = ConstructGraph.constGraph(allNodes);
    }

    public static ArrayList<ArrayList<Integer>> addFriend(ArrayList<ArrayList<Integer>> graph,
            ArrayList<User> allNodes, String nodeA,
            String nodeB) {

        int A = 0;
        User tempA = null;
        int B = 0;
        User tempB = null;
        for (User i : allNodes) { // Findig Index of A node
            if (i.getUsername().equals(nodeA)) {
                tempA = i;
                break;
            }
            A++;
        }
        for (User j : allNodes) { // Findig Index of B node
            if (j.getUsername().equals(nodeB)) {
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
            return graph;
        }

        System.out.println("Size of list is " + allNodes.size());
        Main_With_IO.writeData(allNodes, fileName);// Updating data from file

        ArrayList<ArrayList<Integer>> graph1 = new ArrayList<>(V);
        // ArrayList<User> tempList = Main_With_IO.getAllNodes(fileName);
        // allNodes = tempList;
        System.out.println("Friend added");
        return graph1 = ConstructGraph.constGraph(allNodes);
    }

    public static ArrayList<ArrayList<Integer>> removeFriend(ArrayList<ArrayList<Integer>> graph,
            ArrayList<User> allNodes, String nodeA,
            String nodeB) {

        int A = 0;
        User tempA = null;
        int B = 0;
        User tempB = null;
        for (User i : allNodes) { // Findig Index of A node
            if (i.getUsername().equals(nodeA)) {
                tempA = i;
                break;
            }
            A++;
        }
        for (User j : allNodes) { // Findig Index of B node
            if (j.getUsername().equals(nodeB)) {
                tempB = j;
                break;
            }
            B++;
        }

        if (allNodes.contains(tempA) && allNodes.contains(tempB)) {
            if (graph.get(A).get(B) == 1) {
                allNodes.get(A).RemoveFriend(tempB);
            } else {
                System.out.println("Already not friends Bro");
            }
        } else {
            System.out.println("One or both nodes are not present in our system");
            System.out.println(tempA);
            System.out.println(tempB);
            return graph;
        }

        System.out.println("Size of list is " + allNodes.size());
        Main_With_IO.writeData(allNodes, fileName);// Updating data from file
        // allNodes=Main_With_IO.getAllNodes(fileName);
        System.out.println("Friend is removed");
        return graph = ConstructGraph.constGraph(allNodes);
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

    public static ArrayList<ArrayList<Integer>> reconstructGraph(ArrayList<User> allNodes) {
        ConstructGraph g1 = new ConstructGraph(allNodes);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(allNodes.size());
        graph = g1.RetrieveGraph();
        // displayMatrix(graph);
        return graph;
    }

    public static ArrayList<User> getMutualFriendsList(User sender, User receiver) {
        ArrayList<User> list = new ArrayList<>();
        ArrayList<User> senderList = sender.getFriends();
        ArrayList<User> receiverList = receiver.getFriends();
        int indexA = 0; // index to iterate all friends of receiver to compare with sender friends
        int indexR = 0;
        System.out.println(sender.getUsername() + " has " + senderList.size());
        System.out.println(receiver.getUsername() + " has " + receiverList.size());
        for (int i = 0; i < receiverList.size(); i++) {
            for (int j = 0; j < receiverList.size(); j++) {
                if (receiverList.get(i).equals(senderList.get(j))) {
                    list.add(receiverList.get(i));
                }
            }
        }

        if (list.size() == 0) {
            System.out.println("No Mutual Friends Found");
        }
        return list;
    }

    public static ArrayList<User> sendReq(ArrayList<User> allNodes, User sender, String receiver)
            throws NullPointerException {
        FriendRequest mf = new FriendRequest(sender.getUsername(), sender.getAge(), sender.getGender());
        mf.getMutualFrnds();
        for (User i : allNodes) {
            if (i.getUsername().equals(receiver)) {
                // ArrayList<User> mutualfriends = getMutualFriendsList(sender, i);
                // mf.setMutualFrnds(mutualfriends);
                for (int j = 0; j < i.getFrndReqs().size(); j++) {
                    if (i.getFrndReqs().get(j).getSenderName().equals(sender.getUsername())) {
                        System.out.println("Already sent bro wait krle thora");
                        return allNodes;
                    }
                }
                i.getFrndReqs().add(mf);
                System.out.println("Request sent ");
                Main_With_IO.writeData(allNodes, fileName);
                return allNodes;
            }
        }
        System.out.println("User Not Found");
        return allNodes;
    }

    public static ArrayList<ArrayList<Integer>> acceptReq(ArrayList<ArrayList<Integer>> graph, ArrayList<User> allNodes,
            User sender, User receiver)
            throws NullPointerException {
        ArrayList<FriendRequest> fr = receiver.getFrndReqs();

        for (FriendRequest i : fr) {
            if (i.getSenderName().equals(sender.getUsername())) {
                fr.remove(i);
                return graph = addFriend(graph, allNodes, sender.getUsername(), i.getSenderName());
            }
        }
        System.out.println("NO Req found ");
        return graph;
    }

    public static void main(String[] args) {

        /*
         * // NodeA (String name, int age, String gender, String cnic, String
         * pswrd,String un, Address add)
         * Address AddA = new Address("Islamabad", 3, 10);
         * User nodeA =new User("Saad", 19, "Male", "34342432", "saad1", "saadjutt",
         * AddA);
         * // NodeB
         * Address AddB = new Address("Islamabad", 2, 10);
         * User nodeB =new User("Ali", 19, "Male", "34342432", "ali1", "ali1", AddB);
         * // NodeC
         * Address AddC = new Address("Islamabad", 5, 10);
         * User nodeC =new User("Bakr", 19, "Male", "34342432", "chirri", "bakr1",
         * AddC);
         * // NodeD
         * Address AddD = new Address("Islamabad", 31, 10);
         * User nodeD =new User("Ammar", 19, "Male", "34342432", "azy1", "azy1", AddD);
         * // NodeE
         * Address AddE = new Address("Islamabad", 13, 10);
         * User nodeE =new User("Huzaifa", 19, "Male", "34342432", "huzafa", "javy1",
         * AddE);
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
        ArrayList<User> allNodes = new ArrayList<>();

        /*
         * allNodes.add(nodeA);
         * allNodes.add(nodeB);
         * allNodes.add(nodeC);
         * allNodes.add(nodeD);
         * allNodes.add(nodeE);
         * 
         * // Writting data to file
         * Main_With_IO.writeData(allNodes, fileName);
         * System.out.println("Written NO problem");
         */

        // ****************************************************** Reading data from file

        allNodes = Main_With_IO.getAllNodes(fileName);
        V = allNodes.size();
        // System.out.println("List of frnds created ");
        // System.out.println(allNodes.get(0).getFriends());
        System.out.println("List of frnds created ");
        /*
         * for (User i : allNodes.get(0).getFriends()) {
         * System.out.println(i);
         * }
         */
        // System.out.println("List Tested ");
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(V);
        // graph = g1.RetrieveGraph();
        graph = ConstructGraph.constGraph(allNodes);
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
        System.out.println(allNodes.get(allNodes.size() - 1).getName());

        // ******************************************************Removing and Adding new
        // Nodes and Friends

        // graph = addNode("Pindi", 5, 11, "Saedi", 19, allNodes);
        // User p=allNodes.get(allNodes.size()-1);
        // displayMatrix(graph);
        // User p=new User("Abdul Rehman", 19, null);
        // graph=removeNode(allNodes.get(allNodes.size()-1), allNodes);
        // addNode(p, allNodes);
        graph = removeFriend(graph, allNodes, "saadjutt", "azy1");
        // graph = addFriend(graph, allNodes, "saadjutt", "azy1");
        displayMatrix(graph);
        // System.out.println(allNodes.get(allNodes.size()-3));

        // ******************************************************Sending and Receiving
        // reqs

        allNodes = sendReq(allNodes, allNodes.get(0), "azy1");
        graph = acceptReq(graph, allNodes, allNodes.get(0), allNodes.get(3));
        for (FriendRequest i : allNodes.get(3).getFrndReqs()) {
            System.out.println(i.getSenderName());
        }
    }

}
