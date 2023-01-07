import java.io.*;
import java.util.ArrayList;

public class Main_With_IO implements Serializable {

    public static void writeData(ArrayList<User> s, String file) {
        try {
            File f = new File(file);
            ObjectOutputStream oos;

            oos = new ObjectOutputStream(new FileOutputStream(f));
            // System.out.println("Inside else");
            oos.writeObject(s);
            oos.close();
        } catch (IOException e) {
            System.out.println("IO Exception occured");
        }
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

    public static void AddNode(User node, String file) {
        try {
            File d = new File(file);
            ObjectOutputStream oos;
            if (d.exists()) {
                oos = new CustomObjStream(new FileOutputStream(d));

            } else {
                oos = new ObjectOutputStream(new FileOutputStream(d));
            }
            oos.writeObject(node);
            oos.close();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    public static ArrayList<User> getAllNodes(String file) {
        ArrayList<User> allNodes = new ArrayList<>();
        File f = new File(file);
        if (f.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                allNodes = (ArrayList<User>) ois.readObject();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                System.out.println("ClassNotFoundException ");
                e.printStackTrace();
            } catch (EOFException e) {
                System.out.println("EOFException ");
                // System.out.println("EOFException");
            } catch (IOException e) {
                System.out.println("IOException ");
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found");
        }
        return allNodes;
    }

    public static User removeNode(User node, String file) {
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
