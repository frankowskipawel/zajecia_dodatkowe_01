package repository;

import model.Student;

import java.util.List;

public interface StudentRepository {
    Student getStudentById(int studentId);
    List<Student> getAllStudents();
    List<Student> getStudentByFinalGrades();
    public void updateStudents(List<Student> studentList);
}
