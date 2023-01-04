import java.util.ArrayList;

public class Profile {
    private String name;
    private int age;
    ArrayList<Profile> friends = new ArrayList<>();
    ArrayList<String> interest = new ArrayList<>();
    private Address add;

    public Profile(String name, int age, Address add) {
        this.name = name;
        this.age = age;
        this.friends = null;
        this.interest = null;
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
        this.friends.add(newfrnd);
    }

    public void RemoveFriend(Profile newfrnd) {
        this.friends.remove(newfrnd);
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
        return "Profile [name=" + name + ", age=" + age + ", friends=" + friends + ", interest=" + interest + ", add="
                + add + "]";
    }

    
}