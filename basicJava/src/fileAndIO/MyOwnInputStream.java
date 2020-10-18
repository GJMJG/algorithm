package fileAndIO;

import java.io.*;

/**
 * Java IO 类采用了装饰者模式，好处是可以在运行时动态地给对象添加额外的职责。
 * <p>通过继承 <code>FilterInputStream</code>，在 {@code super.read()} 的方法上添加自己的逻辑，实现大小写的转换</p>
 */
public class MyOwnInputStream extends FilterInputStream {

    protected MyOwnInputStream(InputStream in) {
        super(in);
    }

    public static void main(String[] args) {
        int c;
        try {
            InputStream is = new MyOwnInputStream(new BufferedInputStream(new FileInputStream("input.txt")));
            while ((c = is.read()) >= 0) {
                System.out.println("c : " + c + " (char)c : " + (char) c);
            }
            is.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int read() throws IOException {
        int c = 0;
        if ((c = super.read()) != -1) {
            if (Character.isLowerCase((char) c)) {
                return Character.toUpperCase(c);
            } else if (Character.isUpperCase(c)) {
                Character.toLowerCase(c);
            } else return c;
        } else {
            return -1;
        }
        return super.read();
    }
}
