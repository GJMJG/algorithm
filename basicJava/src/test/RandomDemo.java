package test;

import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        Random rd = new Random();
        for (int i = 0; i < 100; i++) {
            int a = rd.nextInt(1) * 9;
            System.out.println(a);
        }
    }
}
