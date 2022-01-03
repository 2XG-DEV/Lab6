package com.example.lab6;

import com.example.lab6.controllers.*;
import com.example.lab6.models.Student;
import com.example.lab6.models.Teacher;
import com.example.lab6.repos.CourseJDBCRepo;
import com.example.lab6.repos.EnrollmentsJDBCRepo;
import com.example.lab6.repos.StudentJDBCRepo;
import com.example.lab6.repos.TeacherJDBCRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class TeacherView {

    TeacherJDBCRepo teacherRepo = new TeacherJDBCRepo();
    StudentJDBCRepo studentRepo = new StudentJDBCRepo();
    EnrollmentsJDBCRepo enrollmentRepo = new EnrollmentsJDBCRepo();
    CourseJDBCRepo courseRepo = new CourseJDBCRepo();
    TeacherController teacherController = new TeacherController(teacherRepo);
    StudentController studentController = new StudentController(studentRepo);
    CourseController courseController = new CourseController(courseRepo);
    EnrollmentController enrollmentController = new EnrollmentController(enrollmentRepo);
    RegistrationSystemController registrationSystemController = new RegistrationSystemController(studentController, courseController, teacherController, enrollmentController);

    public static final ObservableList names =
            FXCollections.observableArrayList();

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField tfFName;

    @FXML
    private TextField tfLName;

    @FXML
    void showStudents(ActionEvent event) {
        Teacher teacher = registrationSystemController.getTeacherByName(tfFName.getText(),tfLName.getText());
        if(teacher == null ){
            return;
        }
        List<Student> students = registrationSystemController.getStudentsInTeachersCourses(teacher);
        for(Student s : students){
            listView.getItems().add("Student : " + s.getLastName() +" "+ s.getFirstName() + " mit " + registrationSystemController.calculateStudentCredits(s)+" credits und StudentId: "+s.getStudentId());
        }
    }

}
