### 单例模式

单例模式分为 **懒汉模式** 和 **饿汉模式**。饿汉模式采用  **提前加载** 的策略，在类初始化时将单例对象创建出来，即使没有用到单例也会创建，**饿汉模式不存在线程安全问题**，因为Java的类加载机制能够保证单线程，而饿汉模式正是利用了Java的类加载机制。懒汉模式是 **延迟加载策略**，只有在获取单例对象时才会创建单例，

单例模式需要注意的特征：

1. 怎么保证是单例

   私有的构造函数

2. 怎么保存唯一的实例

   使用一个静态变量存储唯一的实例

3. 怎么获取唯一的实例

   对外暴露常量或者提供 get 方法

#### 懒汉模式

1. 非线程安全

   ```java
   public class Singleton4 {
       private static Singleton4 instance;
   
       private Singleton4() {
       }
   
       public static Singleton4 getInstance() {
           if (instance == null) {       //（1）
               instance = new Singleton4();
           }
           return instance;
       }
   }
   ```

   如果存在多线程，在线程A 执行到 （1）位置时，可能会切换到另外一个线程 B，B 执行到（1）位置处可能再次切换回A，此时A会创建一个`Singleton4` 实例，等线程 B 再次执行时， 从（1）之后开始执行，也会创建一个实例，导致单例模式失败。

2. 同步块线程安全

   使用同步块锁住判空代码，但是这样做性能会很差。

   ```java
   public class Singleton5 {
       private static Singleton5 instance;
   
       private Singleton5() {
       }
   
       public static Singleton5 getInstance() {
           synchronized (Singleton5.class) {
               if (instance == null) {
                   instance = new Singleton5();
               }
           }
           return instance;
       }
   }
   ```

3. 双重判断加锁

   可以先做一次与判，如果实例不为空，无需使用同步块。这样写是考虑到加锁的性能原因，操作效率不高。

   ```java
   public class Singleton6 {
       private static Singleton6 instance;
   
       private Singleton6() {
       }
   
       public static Singleton6 getInstance() {
           if (instance == null) {
               synchronized (Singleton5.class) {
                   if (instance == null) {
                       instance = new Singleton6();
                   }
               }
           }
           return instance;
       }
   }
   
   ```

4. 内部类方式（推荐）

   既实现了延迟加载，又不存在线程安全问题。同时性能不会收到影响。主要使用的特性是 **内部类不会随着外部类的初始化而初始化**。

   ```java
   public class Singleton7 {
       private Singleton7() {
       }
   
       private static class Inner {
           private static final Singleton7 instance = new Singleton7();
       }
   
       public Singleton7 getInstance() {
           return Inner.instance;
       }
   }
   ```

#### 饿汉模式

1. 直接创建

   ```java
   public class Singleton1 {
       public static Singleton1 INSTANCE = new Singleton1();
   
       private Singleton1() {
       }
   }
   ```

2. 使用枚举类

   这种方式最简单

   ```java
   /**
    * 饿汉模式：枚举方式保证只有一个实例
    */
   public enum Singleton2 {
       INSTANCE
   }
   ```

3. 使用静态代码块

   使用静态代码块实际上和直接创建的方式一样，都是在类初始化过程中将实例对象创建出来。但是在一些请款下，在创建单例时需要提供一些额外的信息，例如从配置文件中加载信息，这些操作实在创建实例之前必须先进行的，因此采用静态代码块的方式，实现更复杂的初始化操作。

   ```java
   /**
    * 饿汉模式：使用静态代码块
    */
   public class Singleton3 {
       public static final Singleton3 INSTANCE;
       private String info;
   
       static {
           Properties pro = new Properties();
           try {
               pro.load(Singleton3.class.getClassLoader().getResourceAsStream("singleton/implement/pro.properties"));
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
           String content = pro.getProperty("name");
           INSTANCE = new Singleton3(content);
       }
   
       private Singleton3(String info) {
           this.info = info;
       }
   
       @Override
       public String toString() {
           return "Singleton3{" + "info='" + info + '\'' + '}';
       }
   }
   
   ```

   