package sedgewick.coursera.week3;

import edu.princeton.cs.introcs.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class MergeStudent {
    private final String name;
    private final int section;

    public static final Comparator<MergeStudent> BY_NAME = new ByName();
    public static final Comparator<MergeStudent> BY_SECTION = new BySection();

    private static class ByName implements Comparator<MergeStudent> {
        public int compare(MergeStudent o1, MergeStudent o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    private static class BySection implements Comparator<MergeStudent> {
        public int compare(MergeStudent o1, MergeStudent o2) {
            return o1.section - o2.section;
        }
    }

    public MergeStudent(String n, int s) {
        name = n;
        section = s;
    }

    public static void main(String[] args) {
        MergeStudent[] students = new MergeStudent[]{
                new MergeStudent("Rohde", 2),
                new MergeStudent("Furia", 1),
                new MergeStudent("Battle", 4),
                new MergeStudent("Chen", 3),
                new MergeStudent("Kanaga", 3),
                new MergeStudent("Fox", 3),
                new MergeStudent("Andrews", 3),
                new MergeStudent("Gazsi", 4),
        };
        StdOut.println("Sorted by name");
        Arrays.sort(students, BY_NAME);
        for (MergeStudent student : students) {
            StdOut.println("Name: " + student.name + ", Section: " + student.section);
        }

        StdOut.println("Sorted by section");
        Arrays.sort(students, BY_SECTION);
        for (MergeStudent student : students) {
            StdOut.println("Name: " + student.name + ", Section: " + student.section);
        }
    }
}
