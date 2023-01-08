import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;

import javafx.scene.effect.DisplacementMap;

public class UserOperations {
    static int V;// No of nodes in graph
    private static ArrayList<User> allNodes = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> graph = new ArrayList<>(V);
    // private String fileName = "Data.txt";
    private static String fileName;
    private User you;

    public UserOperations(String fileName, User u) {
        this.fileName = fileName;
        V = allNodes.size();
        allNodes = Main_With_IO.getAllNodes(fileName);
        graph = reconstructGraph(allNodes);
        you = u;
    }

 
    public static ArrayList<User> getAllNodes() {
        return allNodes;
    }
   
    public static void setAllNodes(ArrayList<User> allNodes) {
        UserOperations.allNodes = allNodes;
    }
   
    public ArrayList<ArrayList<Integer>> getGraph() {
        return graph;
    }

    public void setGraph(ArrayList<ArrayList<Integer>> graph) {
        this.graph = graph;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        UserOperations.fileName = fileName;
    }



    public User getYou() {
        return you;
    }



    public void setYou(User you) {
        this.you = you;
    }



    public static ArrayList<ArrayList<Integer>> addNode(String city, int streetNo, int houseNo, String name, int age,
            String gender, String cnic, String pswrd, String username,
            ArrayList<User> allNodes) {
        Address AddA = new Address(city, streetNo, houseNo);
        User nodeA = new User(name, age, gender, cnic, pswrd, username, AddA);
        allNodes.add(nodeA);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(++V);
        Main_With_IO.writeData(allNodes, fileName);
        ConstructGraph temp = new ConstructGraph(allNodes);
        graph = temp.constGraph(allNodes, fileName, new User(name, age, gender, cnic, pswrd, username, AddA));
        return graph;
    }

    public static ArrayList<ArrayList<Integer>> addNode(User p, ArrayList<User> allNodes) {
        allNodes.add(p);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(++V);
        Main_With_IO.writeData(allNodes, fileName);
        ConstructGraph temp = new ConstructGraph(allNodes);
        graph = temp.constGraph(allNodes, fileName,p);
        return graph;

    }

    public static ArrayList<ArrayList<Integer>> removeNode(User p, ArrayList<User> allNodes) {
        allNodes.remove(p);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(--V);
        Main_With_IO.writeData(allNodes, fileName);
        ConstructGraph temp = new ConstructGraph(allNodes);
        graph = temp.constGraph(allNodes, fileName,p);
        return graph;
    }

    public boolean addFriend(String nodeB) {

        int A = 0;
        User tempA = null;
        int B = 0;
        User tempB = null;
        for (User i : allNodes) { // Findig Index of A node
            if (i.getUsername().equals(you.getUsername())) {
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
            return false;
        }

        System.out.println("Size of list is " + allNodes.size());
        Main_With_IO.writeData(allNodes, fileName);// Updating data from file

        // ArrayList<ArrayList<Integer>> graph1 = new ArrayList<>(V);
        // ArrayList<User> tempList = Main_With_IO.getAllNodes(fileName);
        // allNodes = tempList;
        System.out.println("Friend added");
        ConstructGraph temp = new ConstructGraph(allNodes);
        graph = temp.constGraph(allNodes, fileName, you);
        return true;
    }

    public boolean removeFriend(String nodeB) {

        int A = 0;
        User tempA = null;
        int B = 0;
        User tempB = null;
        for (User i : allNodes) { // Findig Index of A node
            if (i.getUsername().equals(you.getUsername())) {
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
            return false;
        }

        System.out.println("Size of list is " + allNodes.size());
        Main_With_IO.writeData(allNodes, fileName);// Updating data from file
        // allNodes=Main_With_IO.getAllNodes(fileName);
        System.out.println("Friend is removed");
        ConstructGraph temp = new ConstructGraph(allNodes);
        graph = temp.constGraph(allNodes, fileName,you);
        return true;
    }

    public void displayMatrix() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(graph.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("===================\n");
    }

    public ArrayList<ArrayList<Integer>> reconstructGraph(ArrayList<User> list) throws NullPointerException {
        ConstructGraph g1 = new ConstructGraph(list);
        graph = new ArrayList<>(allNodes.size());
        graph = g1.RetrieveGraph();
        return graph;
        // displayMatrix(graph);
    }

    public ArrayList<User> getMutualFriendsList(User receiver) {
        ArrayList<User> list = new ArrayList<>();
        ArrayList<User> senderList = you.getFriends();
        ArrayList<User> receiverList = receiver.getFriends();
        int indexA = 0; // index to iterate all friends of receiver to compare with sender friends
        int indexR = 0;
        System.out.println(you.getUsername() + " has " + senderList.size());
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

    public boolean sendReq(String receiver)
            throws NullPointerException {
        FriendRequest mf = new FriendRequest(you.getUsername(), you.getAge(), you.getGender());
        mf.getMutualFrnds();
        for (User i : allNodes) {
            if (i.getUsername().equals(receiver)) {
                // ArrayList<User> mutualfriends = getMutualFriendsList(sender, i);
                // mf.setMutualFrnds(mutualfriends);
                for (int j = 0; j < i.getFrndReqs().size(); j++) {
                    if (i.getFrndReqs().get(j).getSenderName().equals(you.getUsername())) {
                        System.out.println("Already sent bro wait krle thora");
                        return true;
                    }
                }
                i.getFrndReqs().add(mf);
                System.out.println("Request sent ");
                Main_With_IO.writeData(allNodes, fileName);
                return true;
            }
        }
        System.out.println("User Not Found");
        return false;
    }

    public boolean acceptReq(User sender)
            throws NullPointerException {
        ArrayList<FriendRequest> fr = you.getFrndReqs();

        for (FriendRequest i : fr) {
            if (i.getSenderName().equals(sender.getUsername())) {
                fr.remove(i);
                addFriend(i.getSenderName());
                return true;
            }
        }
        System.out.println("NO Req found ");
        return false;
    }

}
