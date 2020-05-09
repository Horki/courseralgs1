package sedgewick.coursera.week1;

import sedgewick.coursera.week1.libs.QuickUnionUF;

public class MainQuickUnion {
    public static void main(String[] args) {
        System.out.println("Quick-union");
        QuickUnionUF quickUnion = new QuickUnionUF(10);
        quickUnion.union(3, 4);
        quickUnion.debug_output();
        quickUnion.union(2, 9);
        quickUnion.debug_output();
        quickUnion.union(3, 9);
        quickUnion.debug_output();
        quickUnion.union(4, 9);
        quickUnion.debug_output();
        quickUnion.union(5, 6);
        quickUnion.debug_output();
        String tree = "*****************\n" +
                "0    1    9    6    7    8\n" +
                "         / \\   |\n" +
                "        2   4  5\n" +
                "            |\n" +
                "            3\n";
        System.out.println(tree);
        System.out.println("union 3 - 9: " + quickUnion.connected(3, 9) + ": 3 - 4 - 9");
        quickUnion.debug_output();
        System.out.println(quickUnion.count());

    }
}
