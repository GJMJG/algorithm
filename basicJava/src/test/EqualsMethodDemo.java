package test;

import java.util.Objects;

public class EqualsMethodDemo {
    public static void main(String[] args) {
        Student student1 = new Student("GJM", 25);
        Student student2 = new Student("GJM", 25);
        System.out.println(student1 == student2);
        System.out.println(student1.equals(student2));
    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}