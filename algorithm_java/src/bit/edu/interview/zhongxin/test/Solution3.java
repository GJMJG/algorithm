package interview.zhongxin;

public class Solution3 {
    public static void main(String[] args) {
        System.out.println(new Solution3().toChinese("2312.123"));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可 将数字转换成大写中文金额
     *
     * @param str string字符串 字符串类型的金额数字
     * @return string字符串
     */
    public String toChinese(String str) {
        // write code here
        long input = Long.parseLong(str);
        if (input > Integer.MAX_VALUE) return "超出计算能力！";
        int intPart = 0;
        int intPartLen = 0;
        int hasNext = str.length();
        String[] numSign = new String[]{"壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] unitSign = new String[]{"元", "拾", "佰", "仟", "万", "亿"};
        String[] pointSign = new String[]{"角", "分", "厘"};
        StringBuilder res = new StringBuilder();

        char[] array = str.toCharArray();
        int len = array.length;
        // 处理整数部分
        for (int i = 0; i < len; i++) {
            if (array[i] >= '0' && array[i] <= '9') {
                intPart = intPart * 10 + (array[i] - '0');
                intPartLen++;
            } else if (array[i] == '.') {
                hasNext = i;
                break;
            } else {
                return "抱歉，输入数字不符合要求！";
            }
        }
        while (intPartLen > 0) {
            int index = intPart / (intPartLen == 1 ? 1 : (int) Math.pow(10, intPartLen - 1));
            res.append(numSign[index]);
            intPart = intPart / (int) Math.pow(10, intPartLen - 1);
            res.append(unitSign[intPartLen--]);
        }
        // 处理小数部分
        int pointIndex = 0;
        for (int i = hasNext + 1; i < len; i++) {
            res.append(numSign[array[i] - '0']);
            res.append(pointSign[pointIndex++]);
        }

        return res.toString();
    }
}