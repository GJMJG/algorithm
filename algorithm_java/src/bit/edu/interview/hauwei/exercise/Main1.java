package bit.edu.interview.hauwei.exercise;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main1 {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                set.add(sc.nextInt());
            }

            Iterator it = set.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
                it.remove();
            }
        }
    }
}
