package sedgewick.coursera.week2.bonus;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class FileSorter {
    public static void main(String[] args) {
        StdOut.println("insertion sort example");
        File directory = new File(args[0]);
        File[] files = directory.listFiles();
        Insertion.sort(files);
        for (int i = 0; i < files.length; ++i) {
            StdOut.println(files[i].getName());
        }
    }
}
