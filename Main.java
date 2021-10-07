import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main {
    public static final boolean ONLINE = false;


public static void main(String[] args) {
    String delim;
    if (ONLINE) delim = "\t";
    else delim = " ";

    try {
        Scanner sc = new Scanner(new File("tests/test1.txt"));
        
        int p= Integer.parseInt(sc.nextLine());
        System.out.println(p);
        int n= Integer.parseInt(sc.nextLine());
        System.out.println(n);
        int e= Integer.parseInt(sc.nextLine());
        System.err.println(e);
        System.out.println("+"+ sc.nextLine());


        ArrayList<Node> nodes= new ArrayList<>();
        ArrayList<Edge> edges= new ArrayList<>();

        for (int i = 0; i < p; i++) {
            String line = sc.nextLine();
            System.out.println(line);
        }
        System.out.println(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String tokens[] = line.split(delim);
            nodes.add(new Node(i, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
            System.out.println(line);
        }
        System.out.println(sc.nextLine());
        for (int i = 0; i < e; i++) {
            String line = sc.nextLine();
            String tokens[] = line.split(delim);
            edges.add(new Edge(nodes.get(Integer.parseInt(tokens[0])),
                nodes.get(Integer.parseInt(tokens[0]))));
            System.out.println(line);
        }

        sc.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (NoSuchElementException e) {
        System.out.println("END");
    }
}
}