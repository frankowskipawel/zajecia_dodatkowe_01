package util;

import model.Student;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentUtil {

    private final StudentRepository studentRepository;

    public StudentUtil(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

  public double calculateFinalGrade(Student student){
       int sum =  student.getStudentGrades().stream().mapToInt(value -> value).sum();
       return countTwoDigitsAverage(sum, student.getStudentGrades().size());
  }

  public void calculateWholeClassGrades(List<Student> students){
        students.forEach(student -> student.setStudentFinalGrade(calculateFinalGrade(student)));
        studentRepository.updateStudents(students);
  }

  public List<Student> findStudentsWhoFailed(){
//        List<Student> failedStudents = new ArrayList<>();
//        studentRepository.getAllStudent().forEach(student -> {
//            if (student.getStudentFinalGrade() <3.0){
//                failedStudents.add(student);
//            }
//        });
      return studentRepository.getAllStudent().stream().filter(student -> student.getStudentFinalGrade()<3.0).collect(Collectors.toList());

  }

  public boolean isStudentIsBetterThanAverage(int studentId){
        Student student = studentRepository.getStudentById(studentId);
        double classAverage = calculateClassAverage();
        double studentGrade = calculateFinalGrade(student);
        return studentGrade>classAverage;
  }

  public double calculateClassAverage(){
        List<Student> allStudents = studentRepository.getAllStudent();
        double sum = allStudents.stream().mapToDouble(Student::getStudentFinalGrade).sum();
        return countTwoDigitsAverage(sum, allStudents.size());
  }

  private double countTwoDigitsAverage(double sum, int size){
        double result = sum/size;
        return Math.round(result+100)/100.0;
  }
}
