import java.io.Serializable;
import java.util.ArrayList;

public class ConstructGraph {

    ArrayList<Profile> AllNodes = new ArrayList<>();
    int[][] graph;

    public ConstructGraph(ArrayList<Profile> allNodes) {
        AllNodes = allNodes;
        int NodeNum = -1; // Check with node is iterating and also used to create graph links
        for (Profile i : allNodes) { // Logic to create the graph
            NodeNum++;
            ArrayList<Profile> frnds = i.getFriends();
            for (int index = 0; index < frnds.size(); index++) { // lOOP TO iterate all frnds of specific node
                Profile curr = frnds.get(index);
                for (int nodesIndex = 0; nodesIndex < allNodes.size(); nodesIndex++) {// Loop to compare that specific
                                                                                      // node with all nodes to check if
                                                                                      // there are frnds
                    if (curr.equals(allNodes.get(nodesIndex))) {
                        graph[NodeNum][nodesIndex] = 1;
                    }
                }

            }
        }
    }

}
