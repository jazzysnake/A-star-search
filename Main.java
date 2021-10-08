import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;


public class Main {
    public static final boolean ONLINE = false;
    public static DrawingCanvas canvas;

public static void main(String[] args) {
    String delim;
    if (ONLINE) delim = "\t";
    else delim = " ";
    Scanner sc;    

    try {
        if (ONLINE)
            sc = new Scanner(System.in);
        else
            sc = new Scanner(new File("tests/test1.txt"));
        
        // reading metadata
        int p= Integer.parseInt(sc.nextLine());
        int n= Integer.parseInt(sc.nextLine());
        int e= Integer.parseInt(sc.nextLine());
        sc.nextLine();

        ArrayList<Node> nodes= new ArrayList<>();
        ArrayList<Edge> edges= new ArrayList<>();
        int[][] paths=new int[p][2];
        // reading destination and starting points
        for (int i = 0; i < p; i++) {
            String line = sc.nextLine();
            String tokens[] = line.split(delim);
            paths[i][0]=Integer.parseInt(tokens[0]);
            paths[i][1]=Integer.parseInt(tokens[1]);
        }
        sc.nextLine();
        // reading coords
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String tokens[] = line.split(delim);
            nodes.add(new Node(i, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
        }
        sc.nextLine();
        //reading edges
        for (int i = 0; i < e; i++) {
            String line = sc.nextLine();
            String tokens[] = line.split(delim);
            edges.add(new Edge(nodes.get(Integer.parseInt(tokens[0])),
                nodes.get(Integer.parseInt(tokens[1]))));
        }
        //closing scanner
        sc.close();

        // setting up datastructure
        for (Node node : nodes) {
            node.addConnectedNodes(edges);
        }
        if(ONLINE){
            for (int i = 0; i < paths.length; i++) {
                AStar search = new AStar(nodes, nodes.get(paths[i][0]),nodes.get(paths[i][1]));
                search.findShortestPath();
            }
        }


        if (!ONLINE) {
            int maxX=nodes.get(0).x, maxY=nodes.get(0).y,
            minX=nodes.get(0).x,minY=nodes.get(0).y;
            int maxAbs=0;
            ArrayList<DrawingNode> graphicNodes= new ArrayList<>();
            
            for (Node node : nodes) {
                graphicNodes.add(new DrawingNode(node));
                if (maxX<node.x) maxX=node.x;
                if (maxY<node.y) maxY=node.y;
                if (minY>node.y) minY=node.y;
                if (minX>node.x) minX=node.x;
                if (maxAbs < Math.abs(maxX)) maxAbs= Math.abs(maxX);
                if (maxAbs < Math.abs(maxY)) maxAbs= Math.abs(maxY);
                if (maxAbs < Math.abs(minY)) maxAbs= Math.abs(minY);
                if (maxAbs < Math.abs(minX)) maxAbs= Math.abs(minX);
            }
            maxAbs += minX<minY ? Math.abs(minX):Math.abs(minY);
            int width = 800;
            int height = 800;
            for (DrawingNode node : graphicNodes) {
                node.x += Math.abs(minX);
                node.y += Math.abs(minY);
                node.y/=maxAbs;
                node.x/=maxAbs;
                node.x*=width*.9;
                node.y*=height*.9;
                node.x+=width*.1;
                node.y+=height*.1;
            }
            
            JFrame frame = new JFrame();
            frame.setSize(width,height);
            frame.setTitle("Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            for (int i = 0; i < paths.length; i++) {
                AStar search = new AStar(nodes, nodes.get(paths[i][0]),nodes.get(paths[i][1]));
                canvas = new DrawingCanvas(width, height,graphicNodes,edges,search);
                frame.add(canvas);
                frame.setVisible(true);
                search.findShortestPath();
                frame.remove(canvas);
            }
            
        } 
        
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (NoSuchElementException e) {
        System.out.println("END");
    }
}
}