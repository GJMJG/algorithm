package bit.edu.leecode;

import java.util.Scanner;

public class SearchSubString {
    static int subStringNumber(int k, String string) {
        int result = 0;
        char[] charArray = string.toCharArray();
        int length = charArray.length;

        for (int i = 0; i < length; ++i) {
            int count = 0;
            int pointer = i;
            while (pointer < length && count < k) {
                if (charArray[pointer] == '1' && ++count == k) {
                    ++result;
                    ++pointer;
                    break;
                }
                ++pointer;
            }

            while (pointer < length && charArray[pointer] != '1') {
                ++result;
                ++pointer;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        scanner.nextLine();
        String inputString = scanner.next();
        System.out.println(subStringNumber(k, inputString));
    }
}
