package sedgewick.coursera.week1.libs;

import edu.princeton.cs.introcs.In;
import sedgewick.coursera.week1.interfaces.UF;

public class QuickUnionUF implements UF<Integer> {
    private Integer[] id;
    private int cnt;

    /*
    Set id of each object to itself
    (N array accesses)
     */
    public QuickUnionUF(int N) {
        cnt = N;
        id = new Integer[cnt];
        for (int i = 0; i < cnt; ++i) {
            id[i] = i;
        }
    }

    /*
    change all entries with id[p] to id[q]
    (at most 2N + 2 array accesses)
     */
    public void union(Integer p, Integer q) {
        Integer pid = root(p);
        Integer qid = root(q);
        id[pid] = qid;
    }

    /*
    check whether p and q
    are in the same component
    (2 array accesses)
     */
    public boolean connected(Integer p, Integer q) {
        return root(p).equals(root(q));
    }

    /*
    Quadratic N2
     */
    public Integer find(Integer p) {
        return 0;
    }

    public int count() {
        return cnt;
    }

    private Integer root(Integer i) {
        while (!i.equals(id[i])) {
            i = id[i];
        }
        return i;
    }
}
