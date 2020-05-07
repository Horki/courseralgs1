package sedgewick.coursera.week1;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import sedgewick.coursera.week1.interfaces.UF;
import sedgewick.coursera.week1.libs.QuickUnionWeightedUF;

public class MainQuickUnionWeighted {
    public static void main(String[] args) {
        StdOut.println("Read from tinyUF.txt");
        int N = StdIn.readInt();
        System.out.println("N = " + N);
        UF quickUnionImprovedUF = new QuickUnionWeightedUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!quickUnionImprovedUF.connected(p, q)) {
                quickUnionImprovedUF.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
