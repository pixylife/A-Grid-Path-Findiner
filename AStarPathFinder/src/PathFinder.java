import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sahan Thinusha on 3/30/2017.
 * IIT IT : 2014062
 * UOW ID : w1583051
 * M.K. Sahan Thinusha Wijayananda
 */
public class PathFinder {
    List<Node> openList = new ArrayList<>();
    List<Node> closedList = new ArrayList<>();
    List<Node> path = new ArrayList<>();
    Node start;
    Node end;
    int method;

    public PathFinder(Node start, Node end, int method) {
        this.start = start;
        this.end = end;
        this.method = method;
    }

    public void aStarSearch(boolean a[][]) {

        start.setMoveCost(0);

        if (method == 1) {
            start.setHeuristic(manhatanDistance(start.getX(), start.getY(), end.getX(), end.getY()));
        } else if (method == 2) {
            start.setHeuristic(euclideanDistance(start.getX(), start.getY(), end.getX(), end.getY()));
        } else {
            start.setHeuristic(chebyshevDistance(start.getX(), start.getY(), end.getX(), end.getY()));
        }
        start.setfValue(start.getHeuristic());
        closedList.add(start);

        try {
            findNeighbours(a, start);
        } catch (Exception e) {
        }
        while (true) {
            Node node = openList.get(0);
            for (int i = 1; i < openList.size(); i++) {
                if (node.getfValue()>openList.get(i).getfValue()) {
                    node = openList.get(i);
                }
            }

            openList.remove(node);
            closedList.add(node);


            try {
                findNeighbours(a, node);
            } catch (Exception e) {
                break;
            }
        }
/*
       for (Node n:closedList ) {
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.filledSquare(n.getY(),a.length-n.getX()-1, .5);

        }*/


    }


    public void findNeighbours(boolean[][] a, Node node) throws Exception {
        calculateCost(node.getX(), node.getY() - 1, a, node);   // down
        calculateCost(node.getX(), node.getY() + 1, a, node);   // up
        calculateCost(node.getX() - 1, node.getY(), a, node);   //left
        calculateCost(node.getX() + 1, node.getY(), a, node);   //right
        if(method!=1) {
            calculateCost(node.getX() - 1, node.getY() - 1, a, node);
            calculateCost(node.getX() + 1, node.getY() - 1, a, node);
            calculateCost(node.getX() + 1, node.getY() + 1, a, node);
            calculateCost(node.getX() - 1, node.getY() + 1, a, node);

        }

    /*    try {
            if ((a[node.getX()][node.getY() - 1] || a[node.getX() - 1][node.getY()]))
                calculateCost(node.getX() - 1, node.getY() - 1, a, node);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if ((a[node.getX()][node.getY() - 1] || a[node.getX() + 1][node.getY()]))
                calculateCost(node.getX() + 1, node.getY() - 1, a, node);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if ((a[node.getX()][node.getY() + 1] || a[node.getX() + 1][node.getY()]))
                calculateCost(node.getX() + 1, node.getY() + 1, a, node);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if ((a[node.getX()][node.getY() + 1] || a[node.getX() - 1][node.getY()]))
                calculateCost(node.getX() - 1, node.getY() + 1, a, node);
        } catch (ArrayIndexOutOfBoundsException e) {
        }*/


    }


    public void drawpath(boolean a[][]) {
        path.add(end);

        Node n = closedList.get(closedList.size() - 1);
        path.add(n);

        while (true) {
            n = n.getParent();
            path.add(n);
            if (n.equals(start)) break;
        }


       // Node s = path.get(0);
        //List<Node> shortestPath = new ArrayList<>();
      //  shortestPath.add(s);
    //    for (int i = 1; i < path.size(); i++) {
      /*    System.out.println(path.get(i));
                 if(!(s.getX()==path.get(i).getX() ||  s.getY()==path.get(i).getY())){
                     System.out.println(path.get(i));
                     if(path.get(i).getY()>s.getY() && path.get(i).getX()<s.getX()){
                         System.out.println("H");
                         topToBottomPathSmooth(a,path.get(i),shortestPath);
                    }else if(path.get(i).getY()<s.getY() && path.get(i).getX()<s.getX()){
                         System.out.println("S");
                         topToBottomPathSmooth(a,path.get(i),shortestPath);
                    }else if(path.get(i).getY()>s.getY() && path.get(i).getX()>s.getX()){
                         System.out.println("P");
                          bottomToTopPathSmooth(a,path.get(i),shortestPath);
                      }else {
                         System.out.println("D");
                         bottomToTopPathSmooth(a,path.get(i),shortestPath);
                      }

                }

*/

      //      s = path.get(i);
          //  shortestPath.add(s);
     //   }
        for (Node node : path) {
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.filledSquare(node.getY(), a.length - node.getX() - 1, .5);
        }

        for (Node node : path) {
            StdDraw.setPenColor(StdDraw.BLUE);
            try {
                StdDraw.line(node.getParent().getY(), a.length - node.getParent().getX() - 1, node.getY(), a.length - node.getX() - 1);
            } catch (Exception e) {
            }



        }


        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.filledCircle(start.getY(), a.length - start.getX() - 1, .5);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(start.getY(), a.length - start.getX() - 1, "A");
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(end.getY(), a.length - end.getX() - 1, .5);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(end.getY(), a.length - end.getX() - 1, "B");


    }
/*

    private void topToBottomPathSmooth(boolean [][]a ,Node path,List<Node> shortestPath){

            try {
                if (a[path.getX()+1][path.getY()]) {
                    shortestPath.add(new Node(path.getX()+1, path.getY()));
                }else {
                    shortestPath.add(new Node(path.getX(), path.getY()-1));
                }
            }catch (ArrayIndexOutOfBoundsException e){
                if (a[path.getX()][path.getY()-1]){
                    shortestPath.add(new Node(path.getX(), path.getY()-1));
                }
            }
    }

    private void bottomToTopPathSmooth(boolean [][]a ,Node path,List<Node> shortestPath){
        try {
            if (a[path.getX()-1][path.getY()]) {
                shortestPath.add(new Node(path.getX()-1, path.getY()));
            }else {
                shortestPath.add(new Node(path.getX(), path.getY()+1));
            }
        }catch (ArrayIndexOutOfBoundsException e){
            if (a[path.getX()][path.getY()+1]){
                shortestPath.add(new Node(path.getX(), path.getY()+1));
            }
        }
    }

*/

    public void calculateCost(int x, int y, boolean[][] a, Node parent) throws Exception {
        int N = a.length;
        if (x < 0 || x >= N) return;
        if (y < 0 || y >= N) return;
        if (!a[x][y]) return;

        Node n = new Node(x, y);
        double gValue;
        if (method == 1) {
            gValue = manhatanDistance(parent.getX(), parent.getY(), x, y);
        } else if (method == 2) {
            gValue = euclideanDistance(parent.getX(), parent.getY(), x, y);
        } else {
            gValue = chebyshevDistance(parent.getX(), parent.getY(), x, y);
        }
        if (n.equals(end)) {

            end.setParent(parent);
            end.setMoveCost(gValue + parent.getMoveCost());
            end.setParent(parent);
            throw new Exception("Goal Found");
        }

        if (!(openList.contains(n) || closedList.contains(n))) {
            if (method == 1) {
                n.setHeuristic(manhatanDistance(x, y, end.getX(), end.getY()));
            } else if (method == 2) {
                n.setHeuristic(euclideanDistance(x, y, end.getX(), end.getY()));
            } else {
                n.setHeuristic(chebyshevDistance(x, y, end.getX(), end.getY()));
            }
            n.setParent(parent);
            n.setMoveCost(gValue + parent.getMoveCost());
            n.setfValue(n.getMoveCost()+n.getHeuristic());
            openList.add(n);
        }

    }


    public int manhatanDistance(int x1, int y1, int x2, int y2) {
        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        return distance;
    }


    public double euclideanDistance(int x1, int y1, int x2, int y2) {
        double distance = round(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
        return distance;
    }

    public int chebyshevDistance(int x1, int y1, int x2, int y2) {
        int distance = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
        return distance;

    }

    public static double round(double value) {

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
