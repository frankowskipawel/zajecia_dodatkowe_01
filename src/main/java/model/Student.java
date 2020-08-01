package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Student {

    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private List<Integer> studentGrades;
    private double studentFinalGrade;

    public Student(int studentId, String studentFirstName, String studentLastName, List<Integer> studentGrades) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentGrades = studentGrades;
    }

    public String getStudentFullName(){
        return studentFirstName+" "+studentLastName;
    }
}
