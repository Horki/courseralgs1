package sedgewick.coursera.week1.tasks;

import sedgewick.coursera.week1.interfaces.UF;
import sedgewick.coursera.week1.libs.QuickUnionLargest;

// Union-find with specific canonical element.
// Add a method find() to the union-find data type so that find(i)
// returns the largest element in the connected component containing iii.
// The operations, union(), connected(), and find() should all take logarithmic time or better.
//
// For example, if one of the connected components is {1,2,6,9}\{1, 2, 6, 9\}{1,2,6,9},
// then the find() method should return 999 for each of the four elements in the connected components.

public class Task2 {
    public static void main(String[] args) {
        UF uf = new QuickUnionLargest(10);
        uf.union(1, 9);
        uf.union(2, 9);
        uf.union(3, 9);
        uf.union(6, 9);
        System.out.println(uf.find(1));
        System.out.println(uf.find(2));
        System.out.println(uf.find(3));
        uf.debug_output();
    }
}
