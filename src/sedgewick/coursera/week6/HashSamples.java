package sedgewick.coursera.week6;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class HashSamples {
    public static void main(String[] args) {
        {
            String call = "call";
            StdOut.println(call.hashCode() == 3045982);
        }
        {
            Transaction[] transactions = new Transaction[4];
            transactions[0] = new Transaction("Turing   6/17/1990  644.08");
            transactions[1] = new Transaction("Tarjan   3/26/2002 4121.85");
            transactions[2] = new Transaction("Knuth    6/14/1999  288.34");
            transactions[3] = new Transaction("Dijkstra 8/22/2007 2678.40");
            StdOut.println(transactions[0].hashCode() == -1534311905);
            StdOut.println(transactions[1].hashCode() == -1003135275);
            StdOut.println(transactions[2].hashCode() == 651480158);
            StdOut.println(transactions[3].hashCode() == 732561052);
        }
        {
            StdOut.println("Separate Chaining Hash");
            SeparateChainingHashST<String, Integer> separateChainingHashST = new SeparateChainingHashST<>();
            separateChainingHashST.put("jedan", 1);
            separateChainingHashST.put("dva", 2);
            separateChainingHashST.put("tri", 3);
            separateChainingHashST.put("cetiri", 4);
            separateChainingHashST.put("pet", 5);

            // print keys
            for (String key : separateChainingHashST.keys()) {
                StdOut.println("[" + key + "] = " + separateChainingHashST.get(key));
            }
        }
        {
            StdOut.println("Linear Probing Hash");
            LinearProbingHashST<String, Integer> linear = new LinearProbingHashST<>();
            linear.put("jedan", 1);
            linear.put("dva", 2);
            linear.put("tri", 3);
            linear.put("cetiri", 4);
            linear.put("pet", 5);

            // print keys
            for (String key : linear.keys()) {
                StdOut.println("[" + key + "] = " + linear.get(key));
            }
        }
    }
}
