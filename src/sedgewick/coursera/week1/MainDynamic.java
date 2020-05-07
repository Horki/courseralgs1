package sedgewick.coursera.week1;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import sedgewick.coursera.week1.interfaces.UF;
import sedgewick.coursera.week1.libs.QuickFindUF;

public class MainDynamic {
    private static void bookExample() {
        int N = StdIn.readInt();
        UF uf = new QuickFindUF(N);
        while (!StdIn.isEmpty()) {
            System.out.println(N);
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }

    public static void main(String[] args) {
        UF quickFindUF = new QuickFindUF(10);
        String output = "0   1 - 2   3 - 4\n" +
                "            |   |\n" +
                "5 - 6   7   8   9";
        quickFindUF.union(4, 3);
        quickFindUF.union(3, 8);
        quickFindUF.union(6, 5);
        quickFindUF.union(9, 4);
        quickFindUF.union(2, 1);
        System.out.println(output);
        System.out.println("Test connectivity");
        System.out.println("0 - 7: " + quickFindUF.connected(0, 7));
        System.out.println("8 - 9: " + quickFindUF.connected(8, 9));
        quickFindUF.union(5, 0);
        quickFindUF.union(7, 2);
        quickFindUF.union(6, 1);
        quickFindUF.union(1, 0);
        output = "0 - 1 - 2   3 - 4\n" +
                "|   |   |   |   |\n" +
                "5 - 6   7   8   9";
        System.out.println(output);
        System.out.println("0 - 7: " + quickFindUF.connected(0, 7));
    }
}
