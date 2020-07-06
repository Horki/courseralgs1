package sedgewick.coursera.week6.bonus;

import edu.princeton.cs.algs4.*;

public class Concordance {
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] words = in.readAllStrings();
        ST<String, SET<Integer>> st = new ST<>();
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            if (!st.contains(word)) {
                st.put(word, new SET<>());
            }
            SET<Integer> set = st.get(word);
            set.add(i);
        }
        StdOut.print("search: ");
        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (st.contains(query)) {
                SET<Integer> set = st.get(query);
                for (int k : set) {
                    StdOut.print(words[k] + ", ");
                }
                StdOut.println("");
            } else {
                StdOut.println("Not found!");
            }
        }
    }
}
