package sedgewick.coursera.week6;

import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BlackList {
    // input: tinyText.txt
    public static void main(String[] args) {
        SET<String> set = new SET<>();
        while (!StdIn.isEmpty()) {
            set.add(StdIn.readString());
        }
        StdOut.println("[whitelisted] All items");
        for (String item : set) {
            StdOut.println(item);
        }
        String w = "times";
        StdOut.println("[blacklist] Searching item 'times'");
        if (!set.contains(w)) {
            StdOut.println("Found item: " + w);
        }
        StdOut.print("[blacklist] Search item: ");
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (!set.contains(word)) {
                StdOut.println(word);
            }
        }
    }
}
