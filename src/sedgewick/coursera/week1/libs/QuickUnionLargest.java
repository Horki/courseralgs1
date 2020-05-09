package sedgewick.coursera.week1.libs;

import sedgewick.coursera.week1.interfaces.UF;

public class QuickUnionLargest implements UF<Integer> {
    private Integer[] id;
    private Integer[] sizes;
    private Integer[] largest;
    private int cnt;
    private int capacity;

    public QuickUnionLargest(int N) {
        cnt = N;
        capacity = N;
        id = new Integer[cnt];
        sizes = new Integer[cnt];
        largest = new Integer[cnt];
        for (int i = 0; i < capacity; ++i) {
            id[i] = i;
            sizes[i] = 1;
            largest[i] = i;
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
        Integer largestP = largest[pid];
        Integer largestQ = largest[qid];

        if (sizes[pid] < sizes[qid]) {
            id[pid] = qid;
            sizes[qid] += sizes[pid];
            largest[qid] = (largestP > largestQ) ? largestP : largestQ;
        } else {
            id[qid] = pid;
            sizes[pid] += sizes[qid];
            largest[pid] = (largestQ > largestP) ? largestQ : largestP;
        }
        --cnt;
    }

    public boolean connected(Integer p, Integer q) {
        return root(p).equals(root(q));
    }

    public Integer find(Integer p) {
        return largest[root(p)];
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
        for (Integer s : sizes) {
            System.out.print(s + " ");
        }
        System.out.print("\nlar: ");
        for (Integer l : largest) {
            System.out.print(l + " ");
        }
        System.out.println("\n*************************");
    }

}
