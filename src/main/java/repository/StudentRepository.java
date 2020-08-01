package repository;

import model.Student;

import java.util.List;

public interface StudentRepository {
    Student getStudentById(int studentId);
    List<Student> getAllStudent();
    List<Student> getStudentByFinalGrades();
    public void updateStudents(List<Student> studentList);
}
