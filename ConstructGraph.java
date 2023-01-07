import java.io.Serializable;
import java.util.ArrayList;

public class ConstructGraph implements Serializable {

    ArrayList<User> AllNodes = new ArrayList<>();
    ArrayList<ArrayList<Integer>> graph;

    public void InitialisingGraph(ArrayList<ArrayList<Integer>> mat) {
        for (int i = 0; i < Main.V; i++) {
            mat.add(new ArrayList<>(Main.V));
            for (int j = 0; j < Main.V; j++) {
                mat.get(i).add(0);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> constGraph(ArrayList<User> list) {

        ConstructGraph g1 = new ConstructGraph(list); // Initialising the construct graph object
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(Main.V);
        // graph = g1.RetrieveGraph();
        graph = Main.reconstructGraph(list);
        return graph;

    }

    public ConstructGraph(ArrayList<User> list) {
        graph = new ArrayList<>(Main.V);
        InitialisingGraph(graph);
        AllNodes = list;
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

    public ArrayList<ArrayList<Integer>> RetrieveGraph() {
        return graph;
    }

}
