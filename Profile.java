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

    public void setFriends(ArrayList<Profile> friends) {
        this.friends = friends;
    }

    public ArrayList<String> getInterest() {
        return interest;
    }

    public void setInterest(ArrayList<String> interest) {
        this.interest = interest;
    }

}