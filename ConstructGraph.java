import java.io.Serializable;
import java.util.ArrayList;

public class ConstructGraph implements Serializable {

    ArrayList<User> AllNodes = new ArrayList<>();
    ArrayList<ArrayList<Integer>> graph=new ArrayList<>();

    public void InitialisingGraph(ArrayList<ArrayList<Integer>> mat) {
        System.out.println("Size :" + AllNodes.size());
        for (int i = 0; i < AllNodes.size(); i++) {
            System.out.println("Outer ");
            graph.add(new ArrayList<>(AllNodes.size()));
            for (int j = 0; j < AllNodes.size(); j++) {
                graph.get(i).add(0);
                // System.out.println("Inner");
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> reconstructGraph(ArrayList<User> list) throws NullPointerException {
        ConstructGraph g1 = new ConstructGraph(list);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(list.size());
        graph = g1.RetrieveGraph();
        return graph;
        // displayMatrix(graph);
    }

    public ArrayList<ArrayList<Integer>> constGraph(ArrayList<User> list, String fn, User u) {

        ConstructGraph g1 = new ConstructGraph(list); // Initialising the construct graph object
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(Main.V);
        // graph = g1.RetrieveGraph();
        graph = reconstructGraph(list);
        return graph;

    }

    public ConstructGraph(ArrayList<User> list) {
        System.out.println(list.size());
        AllNodes = list;
        InitialisingGraph(graph);
        System.out.println("Row :" + graph.size());
        System.out.println("Col :" + graph.get(0).size());
        int NodeNum = -1; // Check which node is iterating and also used to create graph links
        for (User i : AllNodes) { // Logic to create the graph
            NodeNum++;
            ArrayList<User> frnds = i.getFriends();
            for (int index = 0; index < frnds.size(); index++) { // lOOP TO iterate all frnds of specific node
                User curr = frnds.get(index);
                // System.out.println(curr);
                for (int nodesIndex = 0; nodesIndex < AllNodes.size(); nodesIndex++) {// Loop to compare that specific
                                                                                      // node with all nodes to check if
                                                                                      // there are frnds
                    if (curr.equals(AllNodes.get(nodesIndex))) {
                        // System.out.println("One ");
                        // graph[NodeNum][nodesIndex] = 1;
                        graph.get(NodeNum).set(nodesIndex, 1);
                    } else {/*
                             * System.out.println(curr);
                             * System.out.println("Here");
                             * System.out.println(AllNodes.get(nodesIndex));
                             * System.out.println("========");
                             */
                    }
                }

            }

        }
    }

    public static void displayMatrix() {
        for (int i = 0; i < Main.V; i++) {
            for (int j = 0; j < Main.V; j++) {
                System.out.print(Main.getGraph().get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("===================\n");
    }

    public ArrayList<ArrayList<Integer>> RetrieveGraph() {
        return graph;
    }

}
