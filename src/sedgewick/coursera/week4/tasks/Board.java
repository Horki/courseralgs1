package sedgewick.coursera.week4.tasks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final int n;
    private final int[] board;
    private Board twin;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        board = new int[n * n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                board[idx(i, j)] = tiles[i][j];
            }
        }
    }

    private Board(int[] tiles) {
        n = (int) Math.sqrt(tiles.length);
        board = tiles.clone();
        twin = null;
    }

    private class Point {
        int x, y;

        public Point(int i, int j) {
            x = i;
            y = j;
        }
    }


    // string representation of this board
    public String toString() {
        StringBuilder result = new StringBuilder("" + n + "\n");
        for (int i = 0; i < (n * n); ++i) {
            result.append(" ").append(board[i]).append(" ");
            if ((i + 1) % n == 0) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int dimension = dimension();
        int currentPlace = 1;
        int cnt = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[idx(i, j)] != currentPlace++) {
                    cnt++;
                }
            }
        }
        return cnt - 1;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int dimension = dimension();
        int point = 1;
        int result = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int cur = board[idx(i, j)];
                if (cur != point && cur != 0) {
                    int curY = cur / dimension;
                    int modder = cur % dimension;
                    int curX = 0;

                    if (modder == 0) {
                        curX = curY;
                        curY--;
                    }

                    if (cur > dimension && curX == 0) {
                        curX = modder - 1;
                    } else if (cur == dimension) {
                        curX = dimension - 1;
                    } else if (cur < dimension) {
                        curX = dimension % cur;
                    }

                    int deltaY = Math.abs(i - curY);
                    int deltaX = Math.abs(j - curX);

                    result += deltaY + deltaX;
                }
                point++;
            }
        }

        return result;
    }

    // is this board the goal board?
    public boolean isGoal() {
        // Check if array is sorted
        // Special case: last board number should be 0!
        for (int i = 0; i < (n * n) - 2; ++i) {
            if (board[i] > board[i + 1]) {
                return false;
            }
        }
        return true;
        // Last should be 0
//        return board[N * N - 1] == 0;
    }

    // does this board equal y?
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Board that = (Board) other;
        return Arrays.equals(board, that.board);
    }

    private int idx(int i, int j) {
        return (i * n) + j;
    }

    private Point findZero() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[idx(i, j)] == 0) {
                    return new Point(i, j);
                }
            }
        }
        return new Point(n + 1, n + 1);
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        Point zero = findZero();
        List<Board> neighbors = new ArrayList<>();

        // DOWN
        if ((zero.x - 1) >= 0) {
            int[] top = board.clone();
            swapZero(top, zero.x - 1, zero.y, zero);
            neighbors.add(new Board(top));
        }

        // UP
        if ((zero.x + 1) < n) {
            int[] bottom = board.clone();
            swapZero(bottom, zero.x + 1, zero.y, zero);
            neighbors.add(new Board(bottom));
        }

        // LEFT
        if ((zero.y - 1) >= 0) {
            int[] left = board.clone();
            swapZero(left, zero.x, zero.y - 1, zero);
            neighbors.add(new Board(left));
        }

        // RIGHT
        if ((zero.y + 1) < n) {
            int[] right = board.clone();
            swapZero(right, zero.x, zero.y + 1, zero);
            neighbors.add(new Board(right));
        }

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (twin != null) {
            return twin;
        }
        int x, y;
        int[] twinBoard = board.clone();
        boolean isSwapped = false;
        do {
            x = StdRandom.uniform(n);
            y = StdRandom.uniform(n);
            if (x == y && n == 2 && twinBoard[idx(x, x)] != 0) {
                y = (x == 1) ? 0 : 1;
                if (twinBoard[idx(y, y)] != 0) {
                    int leftVal = twinBoard[idx(x, x)];
                    twinBoard[idx(x, x)] = twinBoard[idx(y, y)];
                    twinBoard[idx(y, y)] = leftVal;
                    isSwapped = true;
                    break;
                }
            }
        } while (x == y || twinBoard[idx(x, y)] == 0 || twinBoard[idx(y, x)] == 0);

        if (!isSwapped) {
            exch(twinBoard, x, y);
        }
        twin = new Board(twinBoard);
        return twin;
    }

    private void exch(int[] b, int x, int y) {
        int tmp = b[idx(x, y)];
        b[idx(x, y)] = b[idx(y, x)];
        b[idx(y, x)] = tmp;
    }

    private void swapZero(int[] b, int x, int y, Point zero) {
        int tmp = b[idx(x, y)];
        b[idx(x, y)] = 0;
        b[idx(zero.x, zero.y)] = tmp;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3},
                {3, 4, 5},
                {2, 1, 1},
        };
        int[][] b = {
                {1, 2, 3},
                {3, 4, 5},
                {2, 1, 1},
        };
        int[][] goal = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0},
        };

        Board ba = new Board(a);
        Board bb = new Board(b);
        Board bgoal = new Board(goal);
        StdOut.println(ba);
        StdOut.println(!ba.isGoal());
        StdOut.println(bgoal.isGoal());
        StdOut.println(ba.dimension() == 3);
        StdOut.println(ba.dimension() != 33);
        StdOut.println(ba.equals(bb));
    }
}

