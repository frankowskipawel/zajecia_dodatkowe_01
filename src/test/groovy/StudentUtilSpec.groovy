import model.Student
import repository.StudentRepository
import spock.lang.Specification
import spock.lang.Unroll
import util.StudentUtil

class StudentUtilSpec extends Specification{

    def studentList = [ new Student(1, 'A', 'B', [2,2,4]),
                        new Student(2, 'C', 'D', [4,5,3,2,4]),
                        new Student(3, 'E', 'F', [2,2,5,4,3,3,5])]

    def studentListWithResults = [new Student(1, "A","B",[2,2,4], 2.67 )
                                  ,new Student(2, "C","D",[4,5,3,2,4],3.6)
                                  ,new Student(3, "E","F",[2,2,5,4,3,3,5], 3.43)]

    def "Checking if Student Full Name Display correctly"(){
        when:
        Student student = new Student(230,"Przykładowy", "Student", [3,4])
        then:
        student.studentFullName == "Przykładowy Student"
        studentList*.studentFullName == ["A B", "C D", "E F"]
    }

    @Unroll
    def "Checking if Final Grade calculation is done correctly with Id: #studentId"(){
        given:
        def calculator = new StudentUtil()
        when:
        def result = calculator.calculateFinalGrade(student)
        then:
        result == expactedResult as double

        where:
        student                                  | expactedResult
        new Student(1,'t','t',[2,2,4])           | 2.67
        new Student(2,'s','s',[4,5,3,2,4])       | 3.6
        new Student(3,'f','f',[2,2,5,4,3,3,5])   | 3.43
    }

    def "Mock data checks"(){
        given: "Creating Mock fir the repository"
        def mockedRepository = Mock(StudentRepository.class)
        def studentUtil = new StudentUtil(mockedRepository)

        when: "Calculating grades for whole class"
        studentUtil.calculateWholeClassGrades(studentList)

        then: "Update students should ce called ones, with the updated data"
        1*mockedRepository.updateStudents(studentList)

    }

    def "Stubing data checks"(){
        given: "Creating Stub for the repository"
        def stubedRepostiory = Stub(StudentRepository.class)
        def studentUtil = new StudentUtil(stubedRepostiory)
        stubedRepostiory.getAllStudents() >> studentListWithResults

        when : "Calling a tested method"
        def failedStudents = studentUtil.findStudentsWhoFailed()

        then: "checking if the result of Stub is correct"
        failedStudents == [new Student(1, "A","B",[2,2,4], 2.67)]
    }

    def "Mock Stub combination"(){
        given: "Creating Mock for Repository"
        def mockedRepository = Mock(StudentRepository.class)
        def studentUtil = new StudentUtil(mockedRepository)
        1*mockedRepository.getAllStudents() >> studentListWithResults
        1*mockedRepository.getStudentById(_ as Integer) >> new Student(123,'t', 't', [3,3,5], 3.66)

        when:
        boolean isBetter = studentUtil.isStudentIsBetterThanAverage(123)

        then: "Stubing called methods, checking if the final condition is correct"
        isBetter
    }
}
