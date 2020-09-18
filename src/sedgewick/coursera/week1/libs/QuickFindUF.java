package sedgewick.coursera.week1.libs;

import sedgewick.coursera.week1.interfaces.UF;

public class QuickFindUF implements UF<Integer> {
    private final Integer[] id;
    private final int cnt;
    private final int capacity;

    /*
    Set id of each object to itself
    (N array accesses)
     */
    public QuickFindUF(int N) {
        cnt = N;
        capacity = N;
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
        int pid = find(p);
        int qid = find(q);
        for (int i = 0; i < capacity; ++i) {
            if (id[i].equals(pid)) {
                id[i] = qid;
            }
        }
    }

    public boolean connected(Integer p, Integer q) {
        return find(id[p]).equals(find(id[q]));
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

    public void debug_output() {
        System.out.print("*************************\nidx: ");
        for (int k = 0; k < count(); ++k) {
            System.out.print(k + " ");
        }
        System.out.print("\nval: ");
        for (Integer v : id) {
            System.out.print(v + " ");
        }
        System.out.println("\n*************************");
    }

}
