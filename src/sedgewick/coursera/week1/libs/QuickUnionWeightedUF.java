package sedgewick.coursera.week1.libs;

import sedgewick.coursera.week1.interfaces.UF;

public class QuickUnionWeightedUF implements UF<Integer> {
    private Integer[] id;
    private Integer[] sz;
    private int cnt;
    private int capacity;

    public QuickUnionWeightedUF(int N) {
        cnt = N;
        capacity = N;
        id = new Integer[cnt];
        sz = new Integer[cnt];
        for (int i = 0; i < cnt; ++i) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    /*
     Improved Union
     */
    public void union(Integer p, Integer q) {
        if (connected(p, q)) {
            return;
        }
        Integer pid = root(p);
        Integer qid = root(q);
        if (sz[pid] < sz[qid]) {
            id[pid] = qid;
            sz[qid] += sz[pid];
        } else {
            id[qid] = pid;
            sz[pid] += sz[qid];
        }
        --cnt;
    }

    public boolean connected(Integer p, Integer q) {
        return root(p).equals(root(q));
    }

    public Integer find(Integer p) {
        return root(p);
    }

    public int count() {
        return cnt;
    }

    private Integer root(Integer i) {
        while (!i.equals(id[i])) {
            id[i] = id[id[i]]; // path point to its grandparent node
            i = id[i];
        }
        return i;
    }
    public void debug_output() {
        System.out.print("*************************\nidx: ");
        for (int k = 0; k < capacity; ++k) {
            System.out.print(k + " ");
        }
        System.out.print("\nval: ");
        for (Integer v : id) {
            System.out.print(v + " ");
        }
        System.out.print("\nsiz: ");
        for (Integer v : sz) {
            System.out.print(v + " ");
        }
        System.out.println("\n*************************");
    }

}
