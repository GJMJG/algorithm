package bit.edu.leecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FractionSolution {
    public int[] fraction(int[] cont) {
        int count = cont.length - 1;
        int numerator = 1;
        int denominator = cont[count];
        int temp = 0;
        // 第一次运算
        count--;
        numerator = cont[count] * denominator + 1;
        count--;
        while (count >= 0) {
            temp = numerator;
            numerator = cont[count] * numerator + denominator;
            denominator = temp;
            count--;
        }
        System.out.println(numerator + denominator);
        return new int[]{numerator, denominator};
    }
}

class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for (int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] cont = stringToIntegerArray(line);

            int[] ret = new FractionSolution().fraction(cont);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}