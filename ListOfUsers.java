import java.io.Serializable;
import java.util.ArrayList;

public class ListOfUsers implements Serializable {
    private ArrayList<User> list;

    public ListOfUsers(ArrayList<User> list) {
        this.list = list;
    }

    public ArrayList<User> getList() {
        return list;
    }

    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    public void add(User s){
        list.add(s);
    }
    public void remove(User s){
        list.remove(s);
    }
}
