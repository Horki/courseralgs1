package sedgewick.coursera.week6;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LookupCSV {
    // args[0] <CSV_PATH> amino.csv | ip.csv
    // args[1] key   index
    // args[2] value index
    public static void main(String[] args) {
        if (args.length != 3) {
            StdOut.println("example usage ./LookupCSV amino.csv 0 1");
            System.exit(1);
        }
        In in = new In(args[0]);
//        int key = Integer.parseInt(args[1]);
//        int val = Integer.parseInt(args[2]);
        int key = 1;
        int val = 0;
        ST<String, String> st = new ST<>();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String k = tokens[key];
            String v = tokens[val];
            st.put(k, v);
        }
        StdOut.println("items:");
        for (String idx : st) {
            StdOut.println("[" + idx + "] = " + st.get(idx));
        }

        StdOut.println("Search: ");
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!st.contains(s)) {
                StdOut.println("Not found: " + s);
            } else {
                StdOut.println("Found: " + st.get(s));
            }
        }
    }
}
