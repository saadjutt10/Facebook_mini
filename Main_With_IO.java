import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main_With_IO implements Serializable {

    /*
     * public static void writeData(ArrayList<User> s, String file) {
     * try {
     * File f = new File(file);
     * ObjectOutputStream oos;
     * oos = new ObjectOutputStream(new FileOutputStream(f));
     * // System.out.println("Inside else");
     * oos.writeObject(s);
     * oos.close();
     * } catch (IOException e) {
     * System.out.println("IO Exception occured");
     * }
     * }
     */
    public static void writeData(ArrayList<User> s, String file) throws IOException {

        File f = new File(file);
        ObjectOutputStream oos;
        oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(s);
        oos.close();
    }

    public static void ReadData(String file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                User temp = (User) ois.readObject();
                System.out.println(temp.getName());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (EOFException e) {
            System.out.println("EOFException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static ArrayList<ArrayList<Integer>> addNode(String city, int streetNo, int houseNo, String name, int age,
            String gender, String cnic, String pswrd, String username,
            ArrayList<User> allNodes) throws IOException {
        Address AddA = new Address(city, streetNo, houseNo);
        User nodeA = new User(allNodes, name, age, gender, cnic, pswrd, username, AddA);
        allNodes.add(nodeA);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(++(Main.V));
        Main_With_IO.writeData(allNodes, Main.fileName);
        ConstructGraph temp = new ConstructGraph(allNodes);
        graph = temp.constGraph(allNodes, Main.fileName,
                new User(allNodes, name, age, gender, cnic, pswrd, username, AddA));
        return graph;
    }

    public static ArrayList<ArrayList<Integer>> addNode(User p, ArrayList<User> allNodes) throws IOException {
        allNodes.add(p);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(++(Main.V));
        Main_With_IO.writeData(allNodes, Main.fileName);
        ConstructGraph temp = new ConstructGraph(allNodes);
        graph = temp.constGraph(allNodes, Main.fileName, p);
        return graph;

    }

    public static ArrayList<ArrayList<Integer>> removeNode(User p, ArrayList<User> allNodes) throws IOException {
        allNodes.remove(p);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(--(Main.V));
        Main_With_IO.writeData(allNodes, Main.fileName);
        ConstructGraph temp = new ConstructGraph(allNodes);
        graph = temp.constGraph(allNodes, Main.fileName, p);
        return graph;
    }

    /*
     * public static ArrayList<User> getAllNodes(String file) {
     * ArrayList<User> allNodes = new ArrayList<>();
     * try {
     * ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
     * while (true) {
     * User s = (User) ois.readObject();
     * allNodes.add(s);
     * }
     * } catch (ClassNotFoundException e) {
     * System.out.println("ClassNotFoundException");
     * } catch (EOFException e) {
     * // System.out.println("EOFException");
     * } catch (IOException e) {
     * System.out.println("IOException");
     * }
     * return allNodes;
     * }
     */

    public static ArrayList<User> getAllNodes(String file) throws ClassNotFoundException, IOException {
        ArrayList<User> allNodes = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            // System.out.println(ois.readObject());  If you try to print it then it will be empty to be read
            @SuppressWarnings("unchecked")
            ArrayList<User> temp = (ArrayList<User>) ois.readObject();
            return allNodes = temp;
        } catch (EOFException e) {
            System.out.println("EOF Exception");
        }
        return allNodes;
    }

    public static User removeNode(User node, String file) throws ClassNotFoundException, IOException {
        ArrayList<User> allNodes = getAllNodes(file);
        User temp = null;
        for (User i : allNodes) {
            if (node == i) {
                temp = i;
                allNodes.remove(i);
                // When that oject is found we need to delte all other nodes relation with it
                for (User nodes : allNodes) {
                    nodes.RemoveFriend(temp);
                }
                try { // Writting back to file
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                    for (User j : allNodes) {
                        oos.writeObject(j);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            } else {
                System.out.println("No such node found");
            }
        }
        return temp;
    }

}
