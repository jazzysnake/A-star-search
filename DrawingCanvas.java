import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.*;

public class DrawingCanvas extends JComponent{
    private int width;
    private int height;

    private ArrayList<DrawingNode> nodes;
    private ArrayList<Edge> edges;
    private AStar search;
    public DrawingCanvas(int w, int h,ArrayList<DrawingNode> _nodes,ArrayList<Edge> _edges,AStar _search){
        width= w;
        height= h;
        nodes = _nodes;
        edges=_edges;
        search = _search;
    }

    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double background = new Rectangle2D.Double(0,0,width, height);
        g2d.setColor(Color.black);
        g2d.fill(background);
        
        g2d.setColor(Color.yellow);
        for (Edge edge : edges) {
            g2d.drawLine((int)nodes.get(edge.end1.id).x,(int)nodes.get(edge.end1.id).y,
                (int)nodes.get(edge.end2.id).x, (int)nodes.get(edge.end2.id).y);
        }
        g2d.setColor(Color.cyan);
        for (Edge edge : edges) {
            if (search.closed.contains(edge.end1)&&search.closed.contains(edge.end2)) {
                g2d.drawLine((int)nodes.get(edge.end1.id).x,(int)nodes.get(edge.end1.id).y,
                    (int)nodes.get(edge.end2.id).x, (int)nodes.get(edge.end2.id).y);
            }
        }
        g2d.setColor(Color.red);
        for (DrawingNode node : nodes) {
            Rectangle2D.Double node_ = new Rectangle2D.Double(node.x-node.width/2,node.y-node.height/2,node.width,node.height);
            g2d.fill(node_);
        }
        g2d.setColor(Color.blue);
        for (Node node : search.closed) {
            DrawingNode dNode = nodes.get(node.id);
            Rectangle2D.Double node_ = new Rectangle2D.Double(dNode.x-dNode.width/2,dNode.y-dNode.height/2,dNode.width,dNode.height);
            g2d.fill(node_);
        }
        g2d.setColor(Color.pink);
        for (Node node : search.open) {
            DrawingNode dNode = nodes.get(node.id);
            Rectangle2D.Double node_ = new Rectangle2D.Double(dNode.x-dNode.width/2,dNode.y-dNode.height/2,dNode.width,dNode.height);
            g2d.fill(node_);
        }
        g2d.setColor(Color.green);
            DrawingNode dNode = nodes.get(search.startNode.id);
            Rectangle2D.Double node_ = new Rectangle2D.Double(dNode.x-dNode.width/2,dNode.y-dNode.height/2,dNode.width,dNode.height);
            g2d.fill(node_);
        DrawingNode dNode2 = nodes.get(search.goalNode.id);
            Rectangle2D.Double node2_ = new Rectangle2D.Double(dNode2.x-dNode2.width/2,dNode2.y-dNode2.height/2,dNode2.width,dNode2.height);
            g2d.fill(node2_);

    }
}
