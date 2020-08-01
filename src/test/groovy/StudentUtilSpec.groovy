import model.Student
import spock.lang.Specification

class StudentUtilSpec extends Specification{

    def studentList = [ new Student(1, 'A', 'B', [2,2,4]),
                        new Student(2, 'C', 'D', [4,5,3,2,4]),
                        new Student(3, 'E', 'F', [2,2,5,4,3,3,5])]

    def "Checking if Student Full Name Display correctly"(){
        when:
        Student student = new Student(230,"Przykładowy", "Student", [3,4])
        then:
        student.studentFullName == "Przykładowy Student"
    }
}
