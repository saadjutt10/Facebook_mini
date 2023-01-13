import java.io.*;
import java.net.*;
import java.util.*;

public class GetCoordinates {


  public static double[] get_C(StringBuilder response){
  // Getting Longitude and Latitude
    // Use a Scanner to parse the response string
    Scanner scanner = new Scanner(response.toString());
    // Scanner sc=new Scanner(System.in);
    // sc.nextLine();
    // System.out.println(scanner);
    // Skip the first line
    // scanner.nextLine();

    // Find the latitude and longitude lines
    double latitude=0;
    double longitude=0;
    while (scanner.hasNextLine()) {
      String temp_line = scanner.nextLine();
      if (temp_line.contains("\"lat\"")) {
        // Split the line and get the latitude value
        String[] parts = temp_line.split(":");
        latitude = Double.parseDouble(parts[1].trim().replace(",", ""));

        // System.out.println("Latitude: " + latitude);
      } else if (temp_line.contains("\"lng\"")) {
        // Split the line and get the longitude value
        String[] parts = temp_line.split(":");
        longitude = Double.parseDouble(parts[1].trim());
        // System.out.println("Longitude: " + longitude);
      }
    }

    // Close the scanner
    scanner.close();
    double list[]=new double[2];
    list[0]=latitude;
    list[1]=longitude;
    // System.out.println("Latitude is :"+ latitude);
    // System.out.println("longitude is :"+ longitude);
    return list;
  }
  
  public static double[] get_Coordinates(String city) throws IOException{
    
    // Encode the city name for use in the URL
    city = URLEncoder.encode(city, "UTF-8");

    // Set up the URL and API key
    String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city
        + "&key=AIzaSyAaR_Zj1P9mz27EDFyAlddmcxoR7gGAAIA";
    URL url = new URL(urlString);

    // Open a connection to the URL
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    // Set the request method to GET
    conn.setRequestMethod("GET");

    // Read the response from the server
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    StringBuilder response = new StringBuilder();

    while ((line = in.readLine()) != null) {
      response.append(line);
      response.append("\n");
    }
    
    // System.out.println(response);
    in.close();
    return get_C(response);
  }


}