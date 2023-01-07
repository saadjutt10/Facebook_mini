import java.io.Serializable;
import java.util.ArrayList;

public class FriendRequest implements Serializable{
    private String senderName;
    private int senderAge;
    private String senderGender;
    private ArrayList<User> mutualFrnds;
    private boolean reqStatus ;
    public FriendRequest(String senderUserName, int senderAge, String senderGender) {
        this.senderName = senderUserName;
        this.senderAge = senderAge;
        this.senderGender = senderGender;
        this.mutualFrnds = new ArrayList<>();
        this.reqStatus = false;
    }
    public String getSenderName() {
        return senderName;
    }
 
    public int getSenderAge() {
        return senderAge;
    }
    public String getSenderGender() {
        return senderGender;
    }
    public ArrayList<User> getMutualFrnds() {
        return mutualFrnds;
    }
    public void setMutualFrnds(ArrayList<User> mutualFrnds) {
        this.mutualFrnds = mutualFrnds;
    }
    public boolean getReqStatus() {
        return reqStatus;
    }
    public void setReqStatus(boolean reqStatus) {
        this.reqStatus = reqStatus;
    }
    @Override
    public String toString() {
        return "FriendRequest [senderName=" + senderName + ", senderAge=" + senderAge + ", senderGender=" + senderGender
                + ", mutualFrnds=" + mutualFrnds + ", reqStatus=" + reqStatus + "]";
    }

    
    
}
