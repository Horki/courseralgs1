package sedgewick.coursera.week1;

import sedgewick.coursera.week1.interfaces.UF;
import sedgewick.coursera.week1.libs.QuickUnionWeightedUF;

public class MainQuickUnionWeighted {
    public static void main(String[] args) {
        System.out.println("Quick-union weighted");
        UF quickUnionWeighted = new QuickUnionWeightedUF(10);
        quickUnionWeighted.union(3, 4);
        quickUnionWeighted.debug_output();
        quickUnionWeighted.union(2, 9);
        quickUnionWeighted.debug_output();
        quickUnionWeighted.union(3, 9);
        quickUnionWeighted.debug_output();
        quickUnionWeighted.union(4, 9);
        quickUnionWeighted.debug_output();
        quickUnionWeighted.union(5, 6);
        quickUnionWeighted.debug_output();
        String tree = "*****************\n" +
                "0    1    9    6    7    8\n" +
                "         / \\   |\n" +
                "        2   4  5\n" +
                "            |\n" +
                "            3\n";
        System.out.println(tree);
        System.out.println("union 3 - 9: " + quickUnionWeighted.connected(3, 9) + ": 3 - 4 - 9");
        quickUnionWeighted.debug_output();
        System.out.println(quickUnionWeighted.count());

    }
}
