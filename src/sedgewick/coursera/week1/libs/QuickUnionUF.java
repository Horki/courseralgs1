package sedgewick.coursera.week1.libs;

import sedgewick.coursera.week1.interfaces.UF;

public class QuickUnionUF implements UF<Integer> {
    private final Integer[] id;
    private int cnt;
    private final int capacity;

    /*
    Set id of each object to itself
    (N array accesses)
     */
    public QuickUnionUF(int N) {
        cnt = N;
        capacity = N;
        id = new Integer[cnt];
        for (int i = 0; i < cnt; ++i) {
            id[i] = i;
        }
    }

    /*
    change root of p to point to root of q
    (depth of p and q array accesses)
     */
    public void union(Integer p, Integer q) {
        if (connected(p, q)) {
            return;
        }
        Integer pid = find(p);
        Integer qid = find(q);
        if (!pid.equals(qid)) {
            id[pid] = qid;
        }
        --cnt;
    }

    /*
    check if p and q have same root
    (depth of p and q array accesses)
     */
    public boolean connected(Integer p, Integer q) {
        return find(p).equals(find(q));
    }

    /*
    Quadratic N2
     */
    public Integer find(Integer p) {
        return root(p);
    }

    public int count() {
        return cnt;
    }

    /*
    chase parent pointers until reach root
    (depth of i array accesses)
     */
    private Integer root(Integer p) {
        while (!p.equals(id[p])) {
            p = id[p];
        }
        return p;
    }

    /*
    For debug
     */
    public void debug_output() {
        System.out.print("*************************\nidx: ");
        for (int k = 0; k < capacity; ++k) {
            System.out.print(k + " ");
        }
        System.out.print("\nval: ");
        for (Integer v : id) {
            System.out.print(v + " ");
        }
        System.out.println("\n*************************");
    }
}
