package interview.xiaomi;

/**
 * 题目描述： 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格,同一个单元格内的字母不允许被重复使用。
 */
public class Main1{

    static public void main(String[] args){
        double random = Math.random();
        for (int i = 0; i < 1000; i++) {
            random = Math.random();
            System.out.println(random > 0.500 ? "true" : "false");
        }

    }
}