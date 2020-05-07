package sedgewick.coursera.week1.libs;

import edu.princeton.cs.introcs.In;
import sedgewick.coursera.week1.interfaces.UF;

public class QuickUnionLargest implements UF<Integer> {
    private Integer[] id;
    private Integer[] sz;
    private Integer[] lar;
    private int cnt;

    public QuickUnionLargest(int N) {
        cnt = N;
        id = new Integer[cnt];
        sz = new Integer[cnt];
        lar = new Integer[cnt];
        for (int i = 0; i < cnt; ++i) {
            id[i] = i;
            sz[i] = i;
            lar[i] = i;
        }
    }

    /*
     Improved Union
     */
    public void union(Integer p, Integer q) {
        Integer pid = root(p);
        Integer qid = root(q);
        if (pid.equals(qid)) {
            return;
        }
        Integer maxP = lar[pid];
        Integer maxQ = lar[qid];

        if (sz[pid] < sz[qid]) {
            id[pid] = qid;
            sz[qid] += sz[pid];
            lar[qid] = (maxP > maxQ) ? maxP : maxQ;
        } else {
            id[qid] = pid;
            sz[pid] += sz[qid];
            lar[pid] = (maxQ > maxP) ? maxQ : maxP;
        }
    }

    public boolean connected(Integer p, Integer q) {
        return root(p).equals(root(q));
    }

    public Integer find(Integer p) {
        return lar[root(p)];
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
}
