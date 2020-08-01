package repository;

import model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {

    private List<Student> studentList;
    private Map<Integer, Student> studentMap;

    @Override
    public Student getStudentById(int studentId) {
        return studentMap.get(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }

    @Override
    public List<Student> getStudentByFinalGrades() {
        List<Student> listByGrades = studentList;
        Comparator<Student> comparatorTest = Comparator.comparingDouble(value -> value.getStudentFinalGrade());
        Comparator<Student> comparator = Comparator.comparingDouble(Student::getStudentFinalGrade);
        listByGrades.sort(comparator);
        return listByGrades;
    }

    @Override
    public void updateStudents(List<Student> studentList) {
        this.studentList = studentList;
    }
}
