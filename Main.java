public class Main {
    public static void main(String[] args) {

        /*
         * // NodeA (String name, int age, String gender, String cnic, String
         * pswrd,String un, Address add)
         * Address AddA = new Address("Islamabad", 3, 10);
         * User nodeA =new User("Saad", 19, "Male", "34342432", "saad1", "saadjutt",
         * AddA);
         * // NodeB
         * Address AddB = new Address("Islamabad", 2, 10);
         * User nodeB =new User("Ali", 19, "Male", "34342432", "ali1", "ali1", AddB);
         * // NodeC
         * Address AddC = new Address("Islamabad", 5, 10);
         * User nodeC =new User("Bakr", 19, "Male", "34342432", "chirri", "bakr1",
         * AddC);
         * // NodeD
         * Address AddD = new Address("Islamabad", 31, 10);
         * User nodeD =new User("Ammar", 19, "Male", "34342432", "azy1", "azy1", AddD);
         * // NodeE
         * Address AddE = new Address("Islamabad", 13, 10);
         * User nodeE =new User("Huzaifa", 19, "Male", "34342432", "huzafa", "javy1",
         * AddE);
         * 
         * // Adding Frnds of A
         * nodeA.AddNewFriend(nodeE);
         * nodeA.AddNewFriend(nodeB);
         * // Adding Frnds of B
         * nodeB.AddNewFriend(nodeD);
         * // Adding Frnds of C
         * nodeC.AddNewFriend(nodeE);
         * // Adding Frnds of D
         * nodeD.AddNewFriend(nodeC);
         */

        // Adding nodes to list and writting list to file
    

        /*
         * allNodes.add(nodeA);
         * allNodes.add(nodeB);
         * allNodes.add(nodeC);
         * allNodes.add(nodeD);
         * allNodes.add(nodeE);
         * 
         * // Writting data to file
         * Main_With_IO.writeData(allNodes, fileName);
         * System.out.println("Written NO problem");
         */

        // ****************************************************** Reading data from file

       
        // System.out.println("List of frnds created ");
        // System.out.println(allNodes.get(0).getFriends());
        System.out.println("List of frnds created ");
        /*
         * for (User i : allNodes.get(0).getFriends()) {
         * System.out.println(i);
         * }
         */
        // System.out.println("List Tested ");
        // graph = g1.RetrieveGraph();


        /*
         * allNodes.get(0).RemoveFriend(allNodes.get(4));
         * 
         * graph = reconstructGraph(allNodes); // Every time a change happends in nodes
         * it needs to be reconstructed
         * displayMatrix(graph);
         * 
         * allNodes.get(0).AddNewFriend(allNodes.get(4));
         * graph = reconstructGraph(allNodes); // Every time a change happends in nodes
         * it needs to be reconstructed
         * displayMatrix(graph);
         */
        

        // ******************************************************Removing and Adding new
        // Nodes and Friends

        // graph = addNode("Pindi", 5, 11, "Saedi", 19, allNodes);
        // User p=allNodes.get(allNodes.size()-1);
        // displayMatrix(graph);
        // User p=new User("Abdul Rehman", 19, null);
        // graph=removeNode(allNodes.get(allNodes.size()-1), allNodes);
        // addNode(p, allNodes);
        graph = removeFriend(graph, allNodes, "saadjutt", "azy1");
        // graph = addFriend(graph, allNodes, "saadjutt", "azy1");
        displayMatrix(graph);
        // System.out.println(allNodes.get(allNodes.size()-3));

        // ******************************************************Sending and Receiving
        // reqs

        allNodes = sendReq(allNodes, allNodes.get(0), "azy1");
        graph = acceptReq(graph, allNodes, allNodes.get(0), allNodes.get(3));
        for (FriendRequest i : allNodes.get(3).getFrndReqs()) {
            System.out.println(i.getSenderName());
        }
    }
}
