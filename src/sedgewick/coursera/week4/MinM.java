package sedgewick.coursera.week4;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class MinM {
    // uses tinyBatch.txt
    public static void main(String[] args) {
        int M = 5;
        MaxPQ<Transaction> pq = new MaxPQ<>(M);
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction item = new Transaction(line);
            pq.insert(item);
            if (pq.size() > M) {
                pq.delMax();
            }
        }
        StdOut.println("Top " + M + " min. transactions");
        for (Transaction t : pq) {
            StdOut.println(t.toString());
        }
    }
}
