## 自己动手实现注解

> 内容整理自公众号：CodeSheep，羊哥讲的通俗易懂

### 注解的基本原理

注解就是在 **类、接口、字段和方法** 等位置做的一个标记，可以用作 **代码生成、数据校验、资源整合** 等工作，比如说 Lombok 就是通过注解的方式实现代码生成，帮助自动生成一些代码。

注解标记完成后，就可以根据 Java **反射机制**，在运行时动态地获取注解信息，完成注解功能。

### 实现一个注解

实现 Spring 中的 Length 注解。

#### 第1步：定义注解 @Length

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {
    int min();

    int max();

    String message();
}
```

1. 注解的定义类似于接口，关键字`@interface`

2. 注解的成员变量只能是基本类型、`String`或者`enum` 枚举类。基本类的包装类也不行

3. Java中自带四种元注解

4. `@Target()`  元注解用于表示该注解可以使用的位置，是字段，方法还是类本身

   * `ElementType.FIELD` 表示注解可以作用于类的字段，这里 Length就是作用于字段的
   * `ElementType.METHOD` 表示注解可以作用与类的方法
   * `ElementType.TYPE` 表示注解作用于类

5. `@Retention()` 元注解用于定义注解的生命周期

   * `@Retention(RetentionPolicy.RUNTIME)`：表示注解可以一直保留到运行时，因此可以通过反射获取注解信息

   * `@Retention(RetentionPolicy.CLASS)`：表示注解被编译器编译进 `class`文件，但运行时会忽略

   * `@Retention(RetentionPolicy.SOURCE)`：表示注解仅在源文件中有效，编译时就会被忽略

     所以声明周期从长到短分别为：**RUNTIME** > **CLASS** > **SOURCE** ，一般来说，如果需要在运行时去动态获取注解的信息，还是得用RUNTIME。

#### 第2步：获取注解并验证

主要是通过Java的**反射**动态获取注解以及注解的信息。实现一个验证函数，在之前校验参数的地方使用验证函数即可。

```java
public class Validate {
    public static String validateLength(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Length.class)) {
                Length lengthAnnotation = field.getAnnotation(Length.class);
                field.setAccessible(true);
                int value = ((String) field.get(object)).length();

                if (value < lengthAnnotation.min() || value > lengthAnnotation.max()) {
                    return lengthAnnotation.errorMsg();
                }
            }
        }
        return null;
    }
}public class Validate {
    public static String validateLength(Object object) throws IllegalAccessException {
        // 获取对象的字段，返回一个数组
        Field[] fields = object.getClass().getFields();

        for (Field field : fields) {
            //判断字段是否存在注解
            if (field.isAnnotationPresent(Length.class)) {
                //通过反射获取到该字段上标注的@Length注解的详细信息
                Length lengthAnnotation = field.getAnnotation(Length.class);
                // 反射时能访问到私有变量
                field.setAccessible(true);
                //获取对象实例的字段字符串的长度
                int value = ((String) field.get(object)).length();

                // 将字段的实际值和注解上做标示的值进行比对
                if (value < lengthAnnotation.min() || value > lengthAnnotation.max()) {
                    return lengthAnnotation.errorMsg();
                }
            }
        }
        return null;
    }
}
```

#### 第3步：使用注解

字需要检验的字段上注解。

```java
public class Student {
    public String name;
    public long id;
    @Length(min = 11, max = 11, errorMsg = "Stduent's mobile is invalid")
    public String mobile;
}
```

具体的客户端代码中，替换之前手写校验的语句：

```java
if (Validate.validateLength(student) != null) {
    return Validate.validateLength(student);
}
```

