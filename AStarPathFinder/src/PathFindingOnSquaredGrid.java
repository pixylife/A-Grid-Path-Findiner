import java.util.Scanner;

/*************************************************************************
 *  Author: Dr E Kapetanios
 *  Last update: 22-02-2017
 *
 *************************************************************************/


/**
 * Modified by Sahan Thinusha on 3/30/2017.
 * IIT IT : 2014062
 * UOW ID =w1583051
 * M.K. Sahan Thinusha Wijayananda
 */
public class PathFindingOnSquaredGrid {

    // given an N-by-N matrix of open cells, return an N-by-N matrix
    // of cells reachable from the top
    public static boolean[][] flow(boolean[][] open) {
        int N = open.length;

        boolean[][] full = new boolean[N][N];
        for (int j = 0; j < N; j++) {
            flow(open, full, 0, j);
        }

        return full;
    }

    // determine set of open/blocked cells using depth first search
    public static void flow(boolean[][] open, boolean[][] full, int i, int j) {
        int N = open.length;

        // base cases
        if (i < 0 || i >= N) return;    // invalid row
        if (j < 0 || j >= N) return;    // invalid column
        if (!open[i][j]) return;        // not an open cell
        if (full[i][j]) return;         // already marked as open

        full[i][j] = true;

        flow(open, full, i + 1, j);   // down
        flow(open, full, i, j + 1);   // right
        flow(open, full, i, j - 1);   // left
        flow(open, full, i - 1, j);   // up
    }

    // does the system percolate?
    public static boolean percolates(boolean[][] open) {
        int N = open.length;

        boolean[][] full = flow(open);
        for (int j = 0; j < N; j++) {
            if (full[N - 1][j]) return true;
        }

        return false;
    }


    // does the system percolate vertically in a direct way?
    public static boolean percolatesDirect(boolean[][] open) {
        int N = open.length;

        boolean[][] full = flow(open);
        int directPerc = 0;
        for (int j = 0; j < N; j++) {
            if (full[N - 1][j]) {
                // StdOut.println("Hello");
                directPerc = 1;
                int rowabove = N - 2;
                for (int i = rowabove; i >= 0; i--) {
                    if (full[i][j]) {
                        //StdOut.println("i: " + i + " j: " + j + " " + full[i][j]);
                        directPerc++;
                    } else break;
                }
            }
        }

        // StdOut.println("Direct Percolation is: " + directPerc);
        if (directPerc == N) return true;
        else return false;
    }

    // draw the N-by-N boolean matrix to standard draw
    public static void show(boolean[][] a, boolean which) {
        int N = a.length;
        StdDraw.setXscale(-1, N);
        ;
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] == which)
                    StdDraw.square(j, N - i - 1, .5);
                else StdDraw.filledSquare(j, N - i - 1, .5);
    }

    // draw the N-by-N boolean matrix to standard draw, including the points A (x1, y1) and B (x2,y2) to be marked by a circle
    public static void show(boolean[][] a, boolean which, int x1, int y1, int x2, int y2) {
        int N = a.length;
        StdDraw.setXscale(-1, N);
        ;
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] == which)
                    if ((i == x1 && j == y1) || (i == x2 && j == y2)) {
                        StdDraw.circle(j, N - i - 1, .5);
                    } else StdDraw.square(j, N - i - 1, .5);
                else StdDraw.filledSquare(j, N - i - 1, .5);
    }

    // return a random N-by-N boolean matrix, where each entry is
    // true with probability p
    public static boolean[][] random(int N, double p) {
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = StdRandom.bernoulli(p);
        return a;
    }


    // test client
    public static void main(String[] args) {
        // boolean[][] open = StdArrayIO.readBoolean2D();

        // The following will generate a 10x10 squared grid with relatively few obstacles in it
        // The lower the second parameter, the more obstacles (black cells) are generated


        boolean[][] randomlyGenMatrix = random(10, 0.6);
  // boolean[][] randomlyGenMatrix = random(10, 1);


       /* //  Bug !!! make probability 1
        randomlyGenMatrix[3][3] = false;
        randomlyGenMatrix[4][3] = false;
        randomlyGenMatrix[4][2] = false;
        randomlyGenMatrix[5][2] = false;
        randomlyGenMatrix[6][3] = false;
*/
        StdArrayIO.print(randomlyGenMatrix);
        show(randomlyGenMatrix, true);

        System.out.println();
        System.out.println("The system percolates: " + percolates(randomlyGenMatrix));

        System.out.println();
        System.out.println("The system percolates directly: " + percolatesDirect(randomlyGenMatrix));
        System.out.println();

        // Reading the coordinates for points A and B on the input squared grid.

        // THIS IS AN EXAMPLE ONLY ON HOW TO USE THE JAVA INTERNAL WATCH
        // Start the clock ticking in order to capture the time being spent on inputting the coordinates
        // You should position this command accordingly in order to perform the algorithmic analysis
        Stopwatch timerFlow = new Stopwatch();

        Scanner in = new Scanner(System.in);
        int Ai = 0, Aj = 0, Bi = 0, Bj = 0;
        boolean b = false;
        while (!b) {
            System.out.println("Enter Y for A > ");
            Ai = in.nextInt();

            System.out.println("Enter X for A > ");
            Aj = in.nextInt();
            b = randomlyGenMatrix[Ai][Aj];
            if (!b) {
                System.out.println("Error, blocked coordination");
            }
        }
        b = false;
        while (!b) {
            System.out.println("Enter Y for B > ");
            Bi = in.nextInt();

            System.out.println("Enter X for B > ");
            Bj = in.nextInt();
            b = randomlyGenMatrix[Bi][Bj];

            if (!b) {
                System.out.println("Error, blocked coordination");
            }
        }
        System.out.println("Please select method for find distance :");
        System.out.println("Press 1 for manhattan distance");
        System.out.println("Press 2 for euclidean distance");
        System.out.println("Press 3 for chebyshev distance");
        b = false;
        int method = 0;
        while (!b) {
            method = in.nextInt();
            if (method > 0 && method <= 3) {
                b = true;
            }
        }
        String s = "";
        if (method == 1) {
            s = "manhattan";
        } else if (method == 2) {
            s = "euclidean";
        } else {
            s = "chebyshev";
        }
        // THIS IS AN EXAMPLE ONLY ON HOW TO USE THE JAVA INTERNAL WATCH
        // Stop the clock ticking in order to capture the time being spent on inputting the coordinates
        // You should position this command accordingly in order to perform the algorithmic analysis
        StdOut.println("Elapsed time = " + timerFlow.elapsedTime());

        // System.out.println("Coordinates for A: [" + Ai + "," + Aj + "]");
        // System.out.println("Coordinates for B: [" + Bi + "," + Bj + "]");

        show(randomlyGenMatrix, true, Ai, Aj, Bi, Bj);

        Stopwatch total = new Stopwatch();

        Node start = new Node();
        start.setX(Ai);
        start.setY(Aj);
        Node end = new Node();
        end.setX(Bi);
        end.setY(Bj);
        try {

            PathFinder pf = new PathFinder(start, end, method);
            Stopwatch aStar = new Stopwatch();
            pf.aStarSearch(randomlyGenMatrix);
            StdOut.println("Elapsed time  A* Search from "+s+" method= " + aStar.elapsedTime());

            Stopwatch draw = new Stopwatch();
            pf.drawpath(randomlyGenMatrix);
            StdOut.println("Elapsed time Draw Path  from "+s+" method= " + draw.elapsedTime());

            StdOut.println("Elapsed time Total  from "+s+" method= " + total.elapsedTime());

            StdOut.println("Total  cost from "+s+" method= " + PathFinder.round(end.getMoveCost()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("The system not percolates start point to end point");
        }

    }

}


