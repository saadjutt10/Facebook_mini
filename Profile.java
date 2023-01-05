import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable{
    private String name;
    private int age;
    ArrayList<Profile> friends = new ArrayList<>();
    ArrayList<String> interest = new ArrayList<>();
    private Address add;

    public Profile(String name, int age, Address add) {
        this.name = name;
        this.age = age;
        this.add = add;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }

    public ArrayList<Profile> getFriends() {
        return friends;
    }

    public void AddNewFriend(Profile newfrnd) {
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

    public void RemoveFriend(Profile del) {
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
        return "Profile [name=" + name + ", age=" + age +/*  ", friends=" + friends + ", interest=" + interest +  */", add="
                + add + "]";
    }

}