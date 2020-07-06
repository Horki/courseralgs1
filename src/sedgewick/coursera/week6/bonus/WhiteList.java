package sedgewick.coursera.week6.bonus;

import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WhiteList {
    // input: tinyText.txt
    public static void main(String[] args) {
        SET<String> set = new SET<>();
        while (!StdIn.isEmpty()) {
            set.add(StdIn.readString());
        }
        StdOut.println("All items");
        for (String item : set) {
            StdOut.println(item);
        }
        String w = "times";
        StdOut.println("Searching item 'times'");
        if (set.contains(w)) {
            StdOut.println("Found item: " + w);
        }
        StdOut.print("Search item: ");
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (set.contains(word)) {
                StdOut.println(word);
            }
        }
    }
}
