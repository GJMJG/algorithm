package overrideAndReload;

/**
 * 方法重载跟返回值类型和修饰符无关。Java的重载是发生在本类中的，重载的条件是在本类中有多个方法名相同，但参数列表不同(可能是 参数个数不同，参数类型不同)，跟返回值无关。
 */
public class reload {
    public void method(int a, String b) {
    }

    /**
     * 方法参数顺序不同，也会发生重载
     */
    public void method(String a, int b) {
    }


    /**
     * 方法参参数个数不同，会发生重载
     *
     * @param a
     */
    public void method(int a) {
    }

    /**
     * 如果方法名和参数列表相同，仅仅返回值类型不同，不能够重载，编译器错误
     public boolean method(int a, String b){
     }
     */

    /**
     * 和返回值类型以及修饰符无关
     */
    private boolean method() {
        return true;
    }
}
