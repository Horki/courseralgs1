package sedgewick.coursera.week1.libs;

import sedgewick.coursera.week1.interfaces.UF;

public class QuickFindUF implements UF<Integer> {
    private Integer[] id;
    private int cnt;

    /*
    Set id of each object to itself
    (N array accesses)
     */
    public QuickFindUF(int N) {
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
        if (connected(p, q)) {
            return;
        }
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < count(); ++i) {
            if (id[i].equals(pid)) {
                id[i] = qid;
            }
        }
    }

    /*
    check whether p and q
    are in the same component
    (2 array accesses)
     */
    public boolean connected(Integer p, Integer q) {
        return id[p].equals(id[q]);
    }

    /*
     O(1)
     */
    public Integer find(Integer p) {
        return id[p];
    }

    public int count() {
        return cnt;
    }
}
