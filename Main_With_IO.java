import java.io.*;
import java.util.ArrayList;

public class Main_With_IO {

    public static void ReadData(String file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                Profile temp = (Profile) ois.readObject();
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

    public static void AddNode(Profile node, String file) {
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

    public static ArrayList<Profile> getAllNodes(String file) {
        ArrayList<Profile> allNodes = new ArrayList<>();
        File f = new File(file);
        if (f.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                while (true) {
                    Profile node = (Profile) ois.readObject();
                    allNodes.add(node);
                }
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (EOFException e) {
                // System.out.println("EOFException");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found");
        }
        return allNodes;
    }

    public static Profile removeNode(Profile node, String file) {
        ArrayList<Profile> allNodes = getAllNodes(file);
        Profile temp = null;
        for (Profile i : allNodes) {
            if (node == i) {
                temp = i;
                allNodes.remove(i);
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                    for (Profile j : allNodes) {
                        oos.writeObject(j);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            else{
                System.out.println("No such node found");
            }
        }
        return temp;
    }
}
