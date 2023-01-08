import java.io.IOException;
import java.math.*;

public class GetDistance {
    public static final double R = 6372.8; // In kilometers


    public static double DistanceBtCities(String city1,String city2) throws IOException{
        	double coordinate1[]=GetCoordinates.get_Coordinates(city1);
        	double coordinate2[]=GetCoordinates.get_Coordinates(city2);
            return haversine(coordinate1[0], coordinate1[1], coordinate2[0], coordinate2[1]);
    }

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
    public static void main(String[] args) throws IOException {
        String s1="Sialkot";
        String s2="Islamabad";
        System.out.println("Distance bt "+s1+" and "+s2+" is :" + GetDistance.DistanceBtCities(s1, s2) +" Km");
    }
}
