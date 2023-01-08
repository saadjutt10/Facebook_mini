import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;

import javafx.scene.canvas.GraphicsContext;

public class User extends Person {
    ArrayList<User> friends = new ArrayList<>();
    ArrayList<String> interest = new ArrayList<>();
    private Address add;
    private String username;
    private String Password;
    ArrayList<FriendRequest> FrndReqs = new ArrayList<>();

    public User(ArrayList<User> list, String name, int age, String gender, String cnic, String pswrd, String un,
            Address add) {
        super(name, age, gender, cnic);
        this.add = add;
        this.Password = pswrd;
        this.username = un;
    }

    // Getter and Setter

    public ArrayList<FriendRequest> getFrndReqs() {
        return FrndReqs;
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
        int indexA = 0; // index to iterate all friends of receiver to compare with sender friends
        int indexR = 0;
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

   
    //////////// **************Graph Algos and suggestions******************* */

    public int FindIndexInList(ArrayList<User> list, User name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(name))
                return i;
        }
        return 0;
    }

    public ArrayList<User> searching_Breadth(ArrayList<User> list, String name) {
        Queue<User> que;
        boolean checkList[] = new boolean[list.size()];
        int source = FindIndexInList(list, this);
        checkList[source] = true; // Making source visited
        que.add(this);
        while (!que.isEmpty()) {
            User temp = que.poll();
            int position= FindIndexInList(list, temp);
            for (int i = 0; i < list.size(); i++) {
                if (source != i)
                    if (Main.getGraph().get(position).get(i) != 0) {
                        // cout << i << " :Outer true \n";
                        if (checkList[i] == false) // If already Visited just continue
                        {
                            // cout << "inner true \n";
                            checkList[i] = true; // Before pushing the element in stack change its visited check to true
                            que.add(list.get(i));
                        }
                    }
            }
        }
    }

    public ArrayList<User> distanceSuggestions(ArrayList<User> list) throws IOException {
        ArrayList<User> temp = new ArrayList<>();// Taking for 1000km
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String city = user.getAdd().getCity();
            String city2 = this.getAdd().getCity();
            double distance = GetDistance.DistanceBtCities(city, city2);
            if (distance <= 1000) {
                temp.add(user);
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