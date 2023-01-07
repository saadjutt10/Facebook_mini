import java.io.Serializable;
import java.util.ArrayList;

public class User extends Person implements Serializable {
    ArrayList<User> friends = new ArrayList<>();
    ArrayList<String> interest = new ArrayList<>();
    private Address add;
    private String username;
    private String Password;
    ArrayList<FriendRequest> FrndReqs = new ArrayList<>();

    public User(String name, int age, String gender, String cnic, String pswrd, String un, Address add) {
        super(name, age, gender, cnic);
        this.add = add;
        this.Password = pswrd;
        this.username = un;
    }

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

    @Override
    public String toString() {
        return "User [ username=" + username
                + ", Password=" + Password + "]";
    }

}