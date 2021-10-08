public class DrawingNode{
    public int id;

    public double x;
    public double y;
    public double width=10;
    public double height=10;

    public DrawingNode(Node node){
        x=node.x;
        y=node.y;
        id=node.id;
    }
}
