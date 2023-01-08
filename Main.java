import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Main implements Serializable {

    static String fileName = "Data.csv";
    static int V=0;// No of nodes in graph
    private static ArrayList<ArrayList<Integer>> graph;
    
    public static ArrayList<ArrayList<Integer>> getGraph() {
        return graph;
    }

    public static void setGraph(ArrayList<ArrayList<Integer>> temp) {
        graph = temp;
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        // User(ArrayList<User> list,String name, int age, String gender, String cnic,
        // String pswrd, String un, Address add)

        ArrayList<User> allNodes = new ArrayList<>();
        Address ad1 = new Address("sialkot", 22, 10);
        User s1 = new User(allNodes, "Saad", 19, "Male", "23323", "saadstar1", "saadjutt", ad1);

        allNodes.add(s1);
        Address ad2 = new Address("Islamabad", 20, 10);
        User s2 = new User(allNodes, "Ali", 19, "Male", "3213", "alli2", "ali1", ad2);

        allNodes.add(s2);
        Address ad3 = new Address("Gujranwala", 22, 10);
        User s3 = new User(allNodes, "Ammar", 19, "Male", "23323", "azy1", "ammar1", ad3);

        allNodes.add(s3);
        Address ad4 = new Address("Shakargar", 22, 10);
        User s4 = new User(allNodes, "Huzaifa", 19, "Male", "23323", "huz1", "huzaifa1", ad4);

        allNodes.add(s4);
        Address ad5 = new Address("Multan", 22, 10);
        User s5 = new User(allNodes, "Bakr", 19, "Male", "23323", "bakr2", "bakr1", ad5);

        allNodes.add(s5);
        System.out.println(V);
        // Adding friends
        allNodes.get(0).addFriend(allNodes, "bakr1");
        allNodes.get(1).addFriend(allNodes, "ammar1");
        allNodes.get(3).addFriend(allNodes, "bakr1");
        allNodes.get(2).addFriend(allNodes, "saadjutt1");
        allNodes.get(1).addFriend(allNodes, "huzaifa1");

        Main_With_IO.writeData(allNodes, fileName);
        // ArrayList<User> allNodes=Main_With_IO.getAllNodes(fileName);
        // we'll get user Profile from the login window
        // Let's say the user trying to login is first from the list
        // User user1=allNodes.get(0);
        // user1.displayMatrix();

        // ArrayList<User> allNodes=Main_With_IO.getAllNodes(fileName);
        
        V=allNodes.size();
        graph= new ArrayList<>(V);
        graph=ConstructGraph.reconstructGraph(allNodes);
        // System.out.println("File read successfully");
        // allNodes.get(0).displayMatrix();
        // Main_With_IO.writeData(allNodes, fileName);
        ConstructGraph.displayMatrix();
        System.out.println("Going dark");
        for (User i : allNodes.get(0).getFriends()) {
        System.out.println(i.getUsername());
        }

        // User login=new User(allNodes, fileName, 0, fileName, fileName, fileName,
        // fileName, null);
    }
}
