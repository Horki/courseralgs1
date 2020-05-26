package sedgewick.coursera.week4;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class TopM {
    // run tinyBatch.txt
    // Convert text into CSV file:
    // $ sed 's/ \+/,/g' tinyBatch.txt > tinyBatch.csv
    // Add header to CSV file
    // $ sed -i '1s/^/who,when,amount\n/' tinyBatch.csv
    // Sort by amount(numerical), and skip header (HINT: add -r for reverse)
    // $ awk 'FNR>1' tinyBatch.csv | sort -t ',' -k3 -n
    public static void main(String[] args) {
        int M = 5;
        MinPQ<Transaction> pq = new MinPQ<>(M);
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction item = new Transaction(line);
            pq.insert(item);
            if (pq.size() > M) {
                pq.delMin();
            }
        }
        StdOut.println("Top " + M + " transactions");
        for (Transaction t : pq) {
            StdOut.println(t.toString());
        }
    }
}
