import java.io.Serializable;

public class Address implements Comparable<Address>,Serializable {
    private String city;
    private int streetNo;
    private int houseNo;

    public Address(String city, int streetNo, int houseNo) {
        this.city = city;
        this.streetNo = streetNo;
        this.houseNo = houseNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }
    



    @Override
    public int compareTo(Address o) {
        if (this.city.equals(o.getCity())) {
            if (this.streetNo - o.getStreetNo() > -10 && this.streetNo - o.getStreetNo() < 10) { // 10 houses away
                return 1; //Good to be added in friends reco
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Address [city=" + city + ", streetNo=" + streetNo + ", houseNo=" + houseNo 
                + "]";
    }


}
