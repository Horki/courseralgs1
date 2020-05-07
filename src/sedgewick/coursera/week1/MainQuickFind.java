package sedgewick.coursera.week1;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import sedgewick.coursera.week1.interfaces.UF;
import sedgewick.coursera.week1.libs.QuickFindUF;

public class MainQuickFind {
    // Redirect input from
    // $PATH/tinyUF.txt
    public static void main(String[] args) {
        StdOut.println("Read from tinyUF.txt");
        int N = StdIn.readInt();
        UF quickFindText = new QuickFindUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!quickFindText.connected(p, q)) {
                quickFindText.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
        // ------------------------------------
        StdOut.println("Hardcoded input");
        UF quickFindUF = new QuickFindUF(N);
        String output = "*****************\n" +
                "0   1 - 2   3 - 4\n" +
                "            |   |\n" +
                "5 - 6   7   8   9\n" +
                "*****************\n";
        quickFindUF.union(4, 3);
        quickFindUF.union(3, 8);
        quickFindUF.union(6, 5);
        quickFindUF.union(9, 4);
        quickFindUF.union(2, 1);
        StdOut.println(output);
        StdOut.println("Test connectivity");
        StdOut.println("0 - 7: " + quickFindUF.connected(0, 7));
        StdOut.println("8 - 9: " + quickFindUF.connected(8, 9) + ": 8 - 3 - 4 - 9");
        quickFindUF.union(5, 0);
        quickFindUF.union(7, 2);
        quickFindUF.union(6, 1);
        quickFindUF.union(1, 0);
        output = "*****************\n" +
                "0 - 1 - 2   3 - 4\n" +
                "|   |   |   |   |\n" +
                "5 - 6   7   8   9\n" +
                "*****************\n";
        StdOut.println(output);
        StdOut.println("0 - 7: " + quickFindUF.connected(0, 7) + ": 0 - 1 - 2 - 7");
    }
}
