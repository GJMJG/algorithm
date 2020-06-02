package Annotation;

public class Client {

    public String addStudent(Student student) throws IllegalAccessException {
        if (student == null) {
            return "Input student is null";
        }
        if (student.name == null || "".equals(student.name)) {
            return "Student's name is null";
        }
        if (student.id < 0) {
            return "Student's id should't less than 0";
        }
        if (Validate.validateLength(student) != null) {
            return Validate.validateLength(student);
        }
        return "Succeed!";
    }

    public static void main(String[] args) throws IllegalAccessException {
        Student student = new Student();
        student.name = "JM";
        student.id = 3120180326L;
        student.mobile = "17888822331";
        System.out.println(new Client().addStudent(student));
    }
}
