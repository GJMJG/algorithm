package bit.edu.interview.hauwei.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChangeHexaDecimal {
    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        s = s.substring(2);
        System.out.println(Integer.parseInt(s, 16));
    }
}
