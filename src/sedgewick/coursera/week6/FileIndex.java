package sedgewick.coursera.week6;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class FileIndex {
    // usage FileIndex *.txt
    public static void main(String[] args) {
        ST<String, SET<File>> st = new ST<>();
        for (String filepath : args) {
            File file = new File(filepath);
            In in = new In(file);
            while (!in.isEmpty()) {
                String k = in.readString();
                if (!st.contains(k)) {
                    st.put(k, new SET<>());
                }
                SET<File> set = st.get(k);
                set.add(file);
            }
        }
        StdOut.println("Query search: ");
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            StdOut.println("result: " + st.get(key));
        }
    }
}
