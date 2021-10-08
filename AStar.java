import java.util.ArrayList;
import java.util.HashMap;

public class AStar {
    public ArrayList<Node> open;
    public ArrayList<Node> closed;
    public ArrayList<Node> nodes;
    public HashMap<Node,Double> shortestDistFromStart;
    public Node startNode;
    public Node goalNode;
    public Node currentNode;
    public int iterationSum;
    

    AStar(ArrayList<Node> _nodes,Node _startNode, Node _goalNode){
        open  = new ArrayList<>();
        shortestDistFromStart = new HashMap<>();
        open.add(_startNode);//starting node added to open nodes
        closed  = new ArrayList<>();
        nodes = _nodes;
        startNode = _startNode;
        goalNode = _goalNode;
        iterationSum=0;
        for (Node node : startNode.connectedNodes) {
            node.parent = startNode;
        }
    }

    ArrayList<Node> findShortestPath(){
        // starting step
        while(!open.isEmpty()&&!closed.contains(goalNode)){
            iterationSum++;
            if (currentNode==null) {
                currentNode=open.get(0);
            }
            for (Node node : open) {
                if (functionF(node)<functionF(currentNode)) {
                    currentNode=node;
                }
            }
            open.remove(currentNode);
            closed.add(currentNode);
            for (Node node : currentNode.connectedNodes) {
                if (!closed.contains(node)) {
                    if (!open.contains(node)) {
                        open.add(node);
                        node.parent = currentNode;
                        node.cost = new Cost(functionF(node), functionG(node), functionH(node));
                    }
                    else{
                        if(node.cost.g>(functionG(currentNode)+node.distance(currentNode))){
                            node.parent = currentNode;
                            node.cost = new Cost(functionF(node), functionG(node), functionH(node));
                        }
                    }

                }
            }
            currentNode = null;
            if (!Main.ONLINE) {
                Main.canvas.repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }
        System.out.print(functionG(goalNode)+"\t");
        // System.out.println();
        // System.out.println(iterationSum);
        return backtrackToStart(goalNode);
    }

    double functionF(Node node){
        return functionG(node)+functionH(node);
    }

    ArrayList<Node> backtrackToStart(Node node){
        Node iter = node;
        ArrayList<Node> list = new ArrayList<>();
        while (iter!=startNode) {
            list.add(iter);
            iter = iter.parent;
        }
        list.add(startNode);
        return list;
    }

    double functionG(Node node){
        double dist=0;
        Node iter=node;
        ArrayList<Node> visited = new ArrayList<>();
        if(node==startNode)
            return 0;
        while (iter.parent!=startNode) {
            if (iter.parent==null||visited.contains(iter)) {
                System.out.println("functionG error probably");
                return Double.POSITIVE_INFINITY;
            }
            dist+=iter.distance(iter.parent);
            visited.add(iter);
            iter = iter.parent;
        }
        dist += iter.distance(startNode);
        return dist;
    }
    double functionH(Node node){
        return node.eucledianDistance(goalNode);
    }

    void resetNodeData(){
        for (Node node : nodes) {
            node.cost=null;
            node.parent=null;
        }
    }


}
