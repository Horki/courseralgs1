package sedgewick.coursera.week4.tasks;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;


public class Solver {
    private boolean isSolvable;
    private int moves;
    private final Board initialBoard;
    private final Board twinInitialBoard;
    private Stack<SearchNode> solution;


    public Solver(Board initial) {
        isSolvable = true;
        moves = 0;
        solution = new Stack<>();
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        initialBoard = initial;
        SearchNode current = new SearchNode(0, moves, initial, null);
        MinPQ<SearchNode> queue = new MinPQ<>(boardComparator);

        twinInitialBoard = initial.twin();
        SearchNode twinCurrent = new SearchNode(0, moves, initial, null);
        MinPQ<SearchNode> twinQueue = new MinPQ<>(boardComparator);

        while (!current.board.isGoal() && !twinCurrent.board.isGoal()) {
            current = findNext(current, queue);
            twinCurrent = findNext(twinCurrent, twinQueue);
        }

        isSolvable = current.board.isGoal();

        if (isSolvable()) {
            moves = current.move;
            while (current.parent != null) {
                solution.push(current);
                current = current.parent;
            }

            solution.push(current);
        }
    }


    private Comparator<SearchNode> boardComparator = new Comparator<>() {
        public int compare(SearchNode b1, SearchNode b2) {
            int priority1 = b1.priority + b1.move;
            int priority2 = b2.priority + b2.move;
            return (priority1 < priority2) ? -1 : 1;
        }
    };

    private class SearchNode {
        SearchNode(int p, int m, Board b, SearchNode s) {
            priority = p;
            move = m;
            board = b;
            parent = s;
        }

        public int priority;
        public int move;
        public Board board;
        public SearchNode parent;
    }

    private SearchNode findNext(SearchNode current, MinPQ<SearchNode> queue) {
        for (Board b : current.board.neighbors()) {
            if ((current.parent != null && b.equals(current.parent.board)) || b.equals(initialBoard)) {
                continue;
            }
            int priority = b.manhattan();
            SearchNode sn = new SearchNode(priority, current.move + 1, b, current);
            queue.insert(sn);
        }
        return queue.delMin();
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        List<Board> list = new ArrayList<Board>();
        while (!solution.isEmpty()) {
            list.add(solution.pop().board);
        }

        return list;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}