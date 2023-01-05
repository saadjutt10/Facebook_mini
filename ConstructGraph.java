import java.io.Serializable;
import java.util.ArrayList;

public class ConstructGraph implements Serializable {

    ArrayList<Profile> AllNodes = new ArrayList<>();
    int[][] graph = new int[Main.V][Main.V];


    public ConstructGraph(ArrayList<Profile> list) {
        AllNodes = list;
        int NodeNum = -1;                       // Check which node is iterating and also used to create graph links
        for (Profile i : AllNodes) {            // Logic to create the graph
            NodeNum++;
            ArrayList<Profile> frnds = i.getFriends();
            for (int index = 0; index < frnds.size(); index++) { // lOOP TO iterate all frnds of specific node
                Profile curr = frnds.get(index);
                // System.out.println(curr);
                for (int nodesIndex = 0; nodesIndex < AllNodes.size(); nodesIndex++) {// Loop to compare that specific
                                                                                      // node with all nodes to check if
                                                                                      // there are frnds
                    if (curr.equals(AllNodes.get(nodesIndex))) {
                        System.out.println("One ");
                        graph[NodeNum][nodesIndex] = 1;
                    }
                    else{
                        System.out.println(curr);
                        System.out.println("Here");
                        System.out.println(AllNodes.get(nodesIndex));
                        System.out.println("========");
                    }
                }

            }

        }
    }

    public int[][] RetrieveGraph() {
        return graph;
    }

}
