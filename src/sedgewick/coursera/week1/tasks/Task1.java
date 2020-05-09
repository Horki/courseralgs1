package sedgewick.coursera.week1.tasks;

import edu.princeton.cs.algs4.StdOut;
import org.javatuples.Triplet;
import sedgewick.coursera.week1.interfaces.UF;
import sedgewick.coursera.week1.libs.QuickUnionWeightedUF;

import java.util.ArrayList;
import java.util.Date;

//Social network connectivity.
// Given a social network containing nnn members and a
// log file containing mmm timestamps at which times pairs of members formed
// friendships,
// design an algorithm to determine the earliest time at which
// all members are connected
// (i.e., every member is a friend of a friend of a friend ... of a friend).
// Assume that the log file is sorted by timestamp and that friendship
// is an equivalence relation.
// The running time of your algorithm should be mlog⁡nm \log nmlogn or
// better and use extra space proportional to nnn.


// socialnetwork.txt
//10
//4 3 1588972600
//3 8 1588972800
//6 5 1588972900
//9 4 1588973000
//2 1 1588973100
//8 9 1588973200
//5 0 1588973300
//7 2 1588973400
//6 1 1588973500
//1 0 1588973600
//6 7 1588973700
//1 9 1588973800
//2 9 1588973900
public class Task1 {
    public static void main(String[] args) {
        ArrayList<Triplet<Integer, Integer, Integer>> data = new ArrayList();
        data.add(Triplet.with(4, 3, 1588972600));
        data.add(Triplet.with(3, 8, 1588972800));
        data.add(Triplet.with(6, 5, 1588972900));
        data.add(Triplet.with(9, 4, 1588973000));
        data.add(Triplet.with(2, 1, 1588973100));
        data.add(Triplet.with(8, 9, 1588973200));
        data.add(Triplet.with(5, 0, 1588973300));
        data.add(Triplet.with(7, 2, 1588973400));
        data.add(Triplet.with(6, 1, 1588973500));
        data.add(Triplet.with(1, 0, 1588973600));
        data.add(Triplet.with(6, 7, 1588973700));
        data.add(Triplet.with(1, 9, 1588973800));
        data.add(Triplet.with(2, 9, 1588973900));

        UF uf = new QuickUnionWeightedUF(10);
        for (Triplet<Integer, Integer, Integer> d : data) {
            Integer p = d.getValue0();
            Integer q = d.getValue1();
            long timestamp = d.getValue2();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                if (uf.count() == 1) {
                    // No timezone converter in this case
                    Date dt = new Date(timestamp * 1000L);
                    StdOut.println("User : " + p + " and " + q + "; connected at " + dt.toString());
                }
            }
        }
    }
}