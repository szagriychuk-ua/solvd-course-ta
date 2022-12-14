package com.solvd.homework2;

import com.solvd.homework2.exceptions.InvalidCourseCostException;
import com.solvd.homework2.exceptions.InvalidMailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.StubNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongFunction;

public class Main {
    private static Logger console = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws InvalidMailException {
/*        try {
            console.info("This is info");
            run();
        } catch (InvalidCourseCostException e) {
            System.out.println(e.getMessage());
        } catch (InvalidMailException e) {
            System.out.println(e.getMessage());
        }*/

      /*  Enrollment newenroll = new Enrollment(111,new Student(123));
        newenroll.setGrade("MERIT");
        console.info(newenroll);*/

        // usingStreams();


    }

    public static void usingStreams() throws InvalidMailException {
        LinkedList<Student> students = new LinkedList<>();

        Student student = new Student(111, "Julian", "Schirmer",
                "j@gmail.com", LocalDate.of(2001, 10, 30));
        Student student2 = new Student(112, "Carlos", "Perez",
                "c@gmail.com", LocalDate.of(1999, 2, 14));
        Student student3 = new Student(112, "Pepe", "Perez",
                "c@gmail.com", LocalDate.of(1999, 2, 14));
        Student student4 = new Student(112, "Damian", "Perez",
                "c@gmail.com", LocalDate.of(1999, 2, 14));
        Student student5 = new Student(112, "Miguel", "Perez",
                "c@gmail.com", LocalDate.of(1999, 2, 14));



        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        Exam exam = new Exam(1,new Subject<>(111));

        // Save new Students
        exam.setStudents(students);
        console.info(exam.getStudentsNames(students));

        // Save names from students
        LinkedList<String> names = new LinkedList<>();
        names = exam.getStudentsNames(students);

        console.info("Show names in lower case");
        // Show names in lower case
        exam.toLowerCaseNames(names);

        console.info("Count students:");
        // Count students
        exam.countStudents(students);

        console.info("names match with name:");
        // Students who match name
        exam.matchNames(names,"Carlos");

        console.info("Sorted names A to Z:");
        // Sorted names A to Z
        exam.sortedNamesAtoZ(names);

        console.info("Sorted names Z to A:");
        // Sorted names Z to A
        exam.sortedNamesZtoA(names);



        students.addLast(new Student(2020,"Carlos","Miguel",
                "c@gmail.com", LocalDate.of(1999, 2, 14)));
        names = exam.getStudentsNames(students);

        // Distinct students
        console.info("Full names students list: ");
        console.info(names);
        console.info("Same list with distinct");
        exam.distinctNames(names);
    }

    public static void lambdaUtils(){
        lambda_util_consumer();
        longFunction();
        intToDouble();
        lambda_util_bipredicated();
    }

    public static void intToDouble(){
        // IntToDouble
        Professor professor = new Professor(1111,"Julian","Perez", 100);

        IntToDoubleFunction toDouble = (dni) -> {
            return Double.parseDouble(Integer.toString(dni));
        };

        double dniToDouble = toDouble.applyAsDouble(professor.getDni());
        console.info(dniToDouble);
    }

    public static void lambda_util_consumer() throws NullPointerException{
        Exam exam = new Exam(1111, new Subject<>(1111, "QA Testing"));
        Consumer<Professor> addProfessor = (Professor) -> {
            console.info(Professor);
            exam.getProfessors().addLast(Professor);
        };

        Professor professorTesting = new Professor(121212,"Carlos","Nicole",1000);
        addProfessor.accept(professorTesting);
        console.info(exam.getProfessors());
    }

    public static void longFunction() {
        long University1 = 10000;
        long University2 = 15000;
        long University3 = 25000;
        LongFunction longexample = (total) -> {
            total = University1 + University2 + University3;
            return total;
        };
        long total = 0;
        long totalStudents = (long) longexample.apply(total);
        console.info(totalStudents);
    }

    public static void lambda_util_bipredicated (){
        Professor miguel = new Professor(2020,"Miguel","Guemez", 1000);
        Professor juan = new Professor(1515,"Juan", "Belgrano", 2000);
        BiPredicate professor = (name1, name2) -> {
            if(name1.equals(name2)){
                return true;
            } else {
                return false;
            }
        };
        boolean answer = professor.test(miguel.getName(),juan.getName());
        console.info(answer);
    }



    public static void run() throws InvalidCourseCostException, InvalidMailException {

        LinkedList<Student> students = new LinkedList<>();

        Student student = new Student(111, "Juli??n", "Schirmer",
                "j@gmail.com", LocalDate.of(2001, 10, 30));
        Student student2 = new Student(112, "Carlos", "Perez",
                "c@gmail.com", LocalDate.of(1999, 2, 14));


        students.add(student);
        students.add(student2);


        Course testAutomationCourse = new Course(2424, 1000, "Test Automation");
        Course dataScienceCourse = new Course(2525, 1200, "Data Science");
        testAutomationCourse.setStudents(students);
        // dataScienceCourse.setStudents(students);

        Subject subject1testAutomation = new Subject(5454, "Java Language");
        Subject subject2testAutomation = new Subject(6464, "SQL");
        LinkedList<Subject> subjectstestAutomation = new LinkedList<>();
        subjectstestAutomation.add(subject1testAutomation);
        subjectstestAutomation.add(subject2testAutomation);


        testAutomationCourse.setSubjects(subjectstestAutomation);

        LinkedList<Course> courses = new LinkedList<>();
        courses.add(testAutomationCourse);
        courses.add(dataScienceCourse);


        menu(students, courses);

    }


    public static void menu(LinkedList<Student> students, LinkedList<Course> courses) throws InvalidCourseCostException {

        int opc = 1;
        while (opc != 5) {
            System.out.println("Chose an option: \n 1. Create Course \n 2. Find student by dni \n 3. Cost of a Course" +
                    " \n 4.Is the course available? \n 5. See subjects per course \n 6.  \n 7. Exit");
            Scanner scanner = new Scanner(System.in);
            opc = scanner.nextInt();
            switch (opc) {
                case 1: {
                    // Create new course
                    System.out.println(Course.createCourse());
                    break;
                }

                case 2: {
                    // Find student by dni
                    Student.findStudent(students);
                    console.info("Info");
                    break;
                }
                case 3: {
                    // What is the cost of a course?
                    Course.getCourseCost(courses);
                    break;
                }
                case 4: {
                    //Is the course available?
                    Course.isTheCourseAvailable(courses);
                    break;

                }
                case 5: {
                    // What are the subjects in a course
                    Course.getCourseSubjects(courses);
                    break;
                }
                case 6: {
                    // Create Fee
                    Fee.createFee();
                    break;
                }
                case 7: {
                    System.out.println("End");
                    System.out.close();
                    break;
                }
                default: {
                    System.out.println("Choose only the allowed options");
                    break;
                }
            }

        }
    }

}

