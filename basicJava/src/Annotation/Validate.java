package Annotation;

import java.lang.reflect.Field;

public class Validate {
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
