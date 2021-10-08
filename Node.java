import java.util.ArrayList;

public class Node {
    public int id;

    public int x;
    public int y;

    public Cost cost;

    ArrayList<Node> connectedNodes;
    Node parent;

    public Node(int _id, int _x,int _y){
        x=_x;
        y=_y;
        id=_id;
        connectedNodes = new ArrayList<>();
    }

    public void addConnectedNodes(ArrayList<Edge> edges){
        for (Edge edge : edges) {
            if (edge.end1==this) {
                connectedNodes.add(edge.end2);
            }
            if (edge.end2==this) {
                connectedNodes.add(edge.end1);
            }
        }

    }
    public double distance(Node other){
        if (connectedNodes.contains(other)) {
            return eucledianDistance(other);
        }
        return Double.POSITIVE_INFINITY;
    }

    public double eucledianDistance(Node other){
        int dX= x-other.x;
        int dY= y-other.y;
        return Math.sqrt(dX*dX+dY*dY);
    }

    public void setParent(Node node){
        parent = node;
    }
}
