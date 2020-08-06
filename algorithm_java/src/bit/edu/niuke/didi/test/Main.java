package bit.edu.niuke.didi.test;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 滴滴2018校招网申笔试题
 * <p>
 * 给定两个数R和n，输出R的n次方，其中0.0<R<99.999, 0<n<=25
 * </p>
 * <P>计算结果不能用 double 或者 float 类型保存，BigDecimal的使用也算是难点</P>
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigDecimal R = sc.nextBigDecimal();
        int n = sc.nextInt();
        int r = R.compareTo(BigDecimal.ZERO);
        if (r == 0) {
            System.out.print(0);
            return;
        }

        BigDecimal result = new BigDecimal(0);
        if (n == 0) result = new BigDecimal(1);
        if (n < 0) {
            result = new BigDecimal(1).divide(power(R, -n), 10, BigDecimal.ROUND_HALF_EVEN);
        } else if (n > 0) {
            result = power(R, n);
        }

        System.out.println(result.toPlainString());
    }

    public static BigDecimal power(BigDecimal R, int n) {
        if (n == 1) {
            return R;
        }
        BigDecimal temp = new BigDecimal(0);
        // 如果偶数
        if (n % 2 == 0) {
            temp = power(R, n / 2);
            return temp.multiply(temp);
        } else {
            return R.multiply(power(R, n - 1));
        }
    }
}