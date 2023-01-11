import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javafx.scene.canvas.GraphicsContext;

public class User extends Person {
    ArrayList<User> friends = new ArrayList<>();
    ArrayList<String> interest = new ArrayList<>();
    private Address add;
    private String username;
    private String Password;
    ArrayList<FriendRequest> FrndReqs = new ArrayList<>();
    ArrayList<User> BlockedUsers = new ArrayList<>();
    String imageDir;

    public User(ArrayList<User> list, String name, String lname, int age, String gender, String cnic, String pswrd,
            String un,
            Address add) {
        super(name, lname, age, gender, cnic);
        this.add = add;
        this.Password = pswrd;
        this.username = un;
    }

    // Getter and Setter

    public ArrayList<FriendRequest> getFrndReqs() {
        return FrndReqs;
    }

    public String getImageDir() {
        return imageDir;
    }

    public void setImageDir(String imageDir) {
        this.imageDir = imageDir;
    }

    public ArrayList<User> getBlockedUsers() {
        return BlockedUsers;
    }

    public void setFrndReqs(ArrayList<FriendRequest> frndReqs) {
        FrndReqs = frndReqs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    //// ****************************Methods for user*/
    public void AddNewFriend(User newfrnd) {
        // System.out.println(this);
        // System.out.println(newfrnd);
        if (this.friends.size() == 0) {
            System.out.println("True here ");
            this.friends.add(newfrnd);
            newfrnd.getFriends().add(this);
        }
        for (int i = 0; i < this.friends.size(); i++) {
            if (this.friends.get(i) == newfrnd) {
                System.out.println("Frnd Already exist ");
                return;
            }
        }
        this.friends.add(newfrnd);
        newfrnd.getFriends().add(this);
    }

    public void RemoveFriend(User del) {
        // System.out.println(this);
        // System.out.println(newfrnd);
        if (this.friends.size() == 0) {
            System.out.println("he got no frnds");
            return;
        }
        for (int i = 0; i < this.friends.size(); i++) {
            if (this.friends.get(i) == del) {
                this.friends.remove(del);
                del.getFriends().remove(this);
                System.out.println("Removed");
                return;
            }
        }
        System.out.println(" Not his frnd ");
    }

    public ArrayList<String> getInterest() {
        return interest;
    }

    public void AddNewInterest(String newint) {
        this.interest.add(newint);
    }

    public void RemoveInterest(String newint) {
        this.interest.remove(newint);
    }

    public void block(User temp,ArrayList<User> allNodes) throws IOException {
        for(User i : allNodes ){
            if(i.equals(this)){
                i.getBlockedUsers().add(temp);
                System.out.println(temp.getUsername());
                break;
            }
        }
        Main_With_IO.writeData(allNodes, Main.fileName);
    }

    public void unblock(User temp,ArrayList<User> allNodes) throws IOException {
        for(User i : allNodes ){
            if(i.equals(this)){
                i.getBlockedUsers().remove(temp);
                break;
            }
        }
        Main_With_IO.writeData(allNodes, Main.fileName);
    }

    public boolean isBlocked(User temp) {
        return BlockedUsers.contains(temp);
    }

    //////////// ********************Real Shit starts here************* */

    public boolean addFriend(ArrayList<User> allNodes, String nodeB) throws IOException {

        int A = 0;
        User tempA = null;
        int B = 0;
        User tempB = null;
        for (User i : allNodes) { // Findig Index of A node
            if (i.getUsername().equals(this.getUsername())) {
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
        Main_With_IO.writeData(allNodes, Main.fileName);// Updating data from file

        // ArrayList<ArrayList<Integer>> graph1 = new ArrayList<>(V);
        // ArrayList<User> tempList = Main_With_IO.getAllNodes(fileName);
        // allNodes = tempList;
        System.out.println("Friend added");
        for (User i : allNodes) {
            System.out.println(i.getUsername());
        }
        ConstructGraph temp = new ConstructGraph(allNodes);
        ArrayList<ArrayList<Integer>> graph = temp.constGraph(allNodes, Main.fileName, this);
        Main.setGraph(graph);

        return true;
    }

    public boolean removeFriend(ArrayList<User> allNodes, String nodeB) throws IOException {

        int A = 0;
        User tempA = null;
        int B = 0;
        User tempB = null;
        for (User i : allNodes) { // Findig Index of A node
            if (i.getUsername().equals(this.getUsername())) {
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
            if (Main.getGraph().get(A).get(B) == 1) {
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
        Main_With_IO.writeData(allNodes, Main.fileName);// Updating data from file
        // allNodes=Main_With_IO.getAllNodes(fileName);
        System.out.println("Friend is removed");
        ConstructGraph temp = new ConstructGraph(allNodes);
        ArrayList<ArrayList<Integer>> graph = temp.constGraph(allNodes, Main.fileName, this);
        Main.setGraph(graph);
        return true;
    }

    public ArrayList<User> getMutualFriendsList(User receiver) {
        ArrayList<User> list = new ArrayList<>();
        ArrayList<User> senderList = this.getFriends();
        ArrayList<User> receiverList = receiver.getFriends();

        System.out.println(this.getUsername() + " has " + senderList.size());
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

    public ArrayList<User> getMutualFriendsList(ArrayList<User> allNodes, User receiver) {// Using Graphs
        ArrayList<User> list = new ArrayList<>();
        int src = FindIndexInList(list, this);
        int rec = FindIndexInList(list, receiver);
        ArrayList<User> senderList = new ArrayList<>();
        ArrayList<User> receiverList = new ArrayList<>();

        for (int j = 0; j < receiverList.size(); j++) { // Making a list of all friends of two users
            if (Main.getGraph().get(src).get(j) == 1) {
                senderList.add(allNodes.get(j));
            }
        }
        for (int j = 0; j < receiverList.size(); j++) {
            if (Main.getGraph().get(rec).get(j) == 1) {
                receiverList.add(allNodes.get(j));
            }
        }
        // System.out.println(this.getUsername() + " has " + senderList.size());
        // System.out.println(receiver.getUsername() + " has " + receiverList.size());

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

    public boolean sendReq(ArrayList<User> allNodes, String receiver)
            throws NullPointerException, IOException {
        FriendRequest mf = new FriendRequest(this.getUsername(), this.getAge(), this.getGender());
        mf.getMutualFrnds();
        for (User i : allNodes) {
            if (i.getUsername().equals(receiver)) {
                // ArrayList<User> mutualfriends = getMutualFriendsList(sender, i);
                // mf.setMutualFrnds(mutualfriends);
                for (int j = 0; j < i.getFrndReqs().size(); j++) {
                    if (i.getFrndReqs().get(j).getSenderName().equals(this.getUsername())) {
                        System.out.println("Already sent bro wait krle thora");
                        return true;
                    }
                }
                i.getFrndReqs().add(mf);
                System.out.println("Request sent ");
                Main_With_IO.writeData(allNodes, Main.fileName);
                return true;
            }
        }
        System.out.println("User Not Found");
        return false;
    }

    public boolean acceptReq(ArrayList<User> allNodes, User sender)
            throws NullPointerException, IOException {
        ArrayList<FriendRequest> fr = this.getFrndReqs();

        for (FriendRequest i : fr) {
            if (i.getSenderName().equals(sender.getUsername())) {
                fr.remove(i);
                addFriend(allNodes, i.getSenderName());
                return true;
            }
        }
        System.out.println("NO Req found ");
        return false;
    }

    public static boolean userExists(ArrayList<User> allNodes, String user) {
        for (User i : allNodes) {
            if (i.getUsername().equals(user)) {
                return true;
            }
        }
        return false;
    }

    public static User getUser(ArrayList<User> allNodes, String user) {
        for (User i : allNodes) {
            if (i.getUsername().equals(user)) {
                return i;
            }
        }
        return null;
    }

    public static boolean correctPassword(ArrayList<User> allNodes, String pswd, String user) {
        User temp = getUser(allNodes, user);
        if (temp.getPassword().equals(pswd)) {
            return true;
        }
        return false;
    }

    //////////// **************Graph Algos and suggestions******************* */

    public static int FindIndexInList(ArrayList<User> list, User name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(name))
                return i;
        }
        return 0;
    }

    public ArrayList<User> searching_Breadth(ArrayList<User> list, String name) {
        ArrayList<User> temp_list = new ArrayList<>();
        Queue<User> que = new LinkedList<>();
        Stack<User> stk = new Stack<>();
        boolean checkList[] = new boolean[list.size()];
        int source = FindIndexInList(list, this);
        checkList[source] = true; // Making source visited
        int count = 1;// Variable to keep track of number of friends at lvl order
        que.add(this);
        while (!que.isEmpty()) {
            for (int j = 0; j < count; j++) {// Loop to indicate the number of times the inner loop needs to be executed
                count = 0; // to cover one lvl nodes
                User temp = que.poll();
                int position = FindIndexInList(list, temp);
                for (int i = 0; i < list.size(); i++) { // one for each row
                    if (position != i)
                        if (Main.getGraph().get(position).get(i) != 0) {
                            // cout << i << " :Outer true \n";
                            if (checkList[i] == false) // If already Visited just continue
                            {
                                checkList[i] = true; // Before pushing the element in queue change its visited check to
                                                     // true
                                que.add(list.get(i));
                                if (list.get(i).getName().equals(name)) {
                                    if (!isBlocked(list.get(i))) {
                                        stk.push(list.get(i));
                                    }
                                }
                                count++;
                            }
                        }
                }
            }
            if (count == 0) { // Incase there is no such friend of your friend that is not visited yet
                count = 1;
            }
            stk.push(null); // Pusing null each time one lvl
                            // is finished
        }
        System.out.println(stk.size());
        // int nullCount=0;
        for (User i : stk) {
            if (i!=null) {
                if (i.getUsername().equals("null")) {
                    System.out.println();
                } else {
                    System.out.println(i.getUsername());
                    temp_list.add(i);
                }
            }
        }

        return temp_list;
    }

    public ArrayList<User> distanceSuggestions(ArrayList<User> list) throws IOException {
        ArrayList<User> temp = new ArrayList<>();// Taking for 1000km
        for (int i = 0; i < list.size(); i++) {
            if(i!=FindIndexInList(list, this)){
                User user = list.get(i);
                String city = user.getAdd().getCity();
                String city2 = this.getAdd().getCity();
                double distance = GetDistance.DistanceBtCities(city, city2);
                if (distance <= 1000) {
                    if (!isBlocked(user)) {
                        temp.add(user);
                    }
                }
            }
            }
           
        return temp;
    }

    public ArrayList<User> friendsOfFriends(ArrayList<User> list) throws IOException {
        ArrayList<User> frnds = new ArrayList<>();
        boolean chklist[] = new boolean[list.size()];
        int src = FindIndexInList(list, this);
        System.out.println("Index of usr is :"+src);
        chklist[src] = true;
        for (int j = 0; j < list.size(); j++) { // Loop to get all friends using graph and storing them in list
            if (Main.getGraph().get(src).get(j) == 1) {
                frnds.add(list.get(j));
                chklist[j] = true;
            }
        }

        ArrayList<User> temp = new ArrayList<>();
        ArrayList<User> frndList=new ArrayList<>();
        for (int i = 0; i < frnds.size(); i++) { // To iterate all frnds of source
            // ArrayList<User> frndList = temp.get(i).getFriends();
            int indexOfFriend = FindIndexInList(list, frnds.get(i));// Getting the index of current frnd node w.r.t
                                                                    // allNodes(list)
            for (int fof = 0; fof < list.size(); fof++) {// Loop to get all friends of you friends
                if (Main.getGraph().get(indexOfFriend).get(fof) == 1) {
                    System.out.println("Adding in temp :" + list.get(fof).getUsername());
                    frndList.add(list.get(fof));
                }
            }
            for (int j = 0; j < frndList.size(); j++) {// To iterate all friends of source's friend
                int index = FindIndexInList(list, frndList.get(j));// Getting the index w.r.t allNodes
                if (chklist[index] == false) {// Checking if the node is already visited or not
                    chklist[index] = true;
                    // if (!isBlocked(frndList.get(j))) {// If the user is not blocked then add in list
                        System.out.println("Adding :" + frndList.get(j).getUsername());
                        temp.add(frndList.get(j));
                    // }
                }
            }
        }

        return temp;
    }

    @Override
    public String toString() {
        return "User [ username=" + username
                + ", Password=" + Password + "]";
    }

}