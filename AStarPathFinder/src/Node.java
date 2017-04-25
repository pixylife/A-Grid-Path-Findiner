/**
 * Created by Sahan Thinusha on 3/30/2017.
 * IIT IT : 2014062
 * UOW ID : w1583051
 * M.K. Sahan Thinusha Wijayananda
 */
public class Node {
    private double heuristic;
    private double moveCost;
    private double fValue;
    private int x;
    private int y;
    private Node parent;


    public Node() {

    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    public double getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(double moveCost) {
        this.moveCost = moveCost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getfValue() {
        return fValue;
    }

    public void setfValue(double fValue) {
        this.fValue = fValue;
    }

    @Override
    public boolean equals(Object obj) {
        Node n = (Node) obj;
        if (n.getX() == x && n.getY() == y) {

            return true;
        } else {
            return false;
        }

    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        //String p = (parent!=null)?" || "+parent.toString()+ " || ":"Root";
        return x + " " + y + " " + moveCost + " " + heuristic + " ";
    }


}
