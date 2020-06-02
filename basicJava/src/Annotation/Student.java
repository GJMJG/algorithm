package Annotation;

public class Student {
    public String name;
    @ID(min = 0, errorMsg = "Student's id should't less than 0")
    public long id;
    @Length(min = 11, max = 11, errorMsg = "Stduent's mobile is invalid")
    public String mobile;
}
