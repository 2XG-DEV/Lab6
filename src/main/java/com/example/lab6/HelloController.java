package com.example.lab6;

import com.example.lab6.controllers.*;
import com.example.lab6.models.Course;
import com.example.lab6.models.Student;
import com.example.lab6.repos.CourseJDBCRepo;
import com.example.lab6.repos.EnrollmentsJDBCRepo;
import com.example.lab6.repos.StudentJDBCRepo;
import com.example.lab6.repos.TeacherJDBCRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloController {

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
    private Label CreditsLabel;


    @FXML
    private TextField tfFName;

    @FXML
    private TextField tfLName;

    @FXML
    private ListView<String> listView;

    @FXML
    void btnLogInClicked(ActionEvent event) {
        Student studentToEnroll = registrationSystemController.getStudentByName(tfFName.getText(),tfLName.getText());
        if(studentToEnroll == null){
            studentToEnroll =new Student(0,tfFName.getText(),tfLName.getText());
            registrationSystemController.addStudentToSystem(studentToEnroll);
        }
        studentToEnroll  = registrationSystemController.getStudentByName(tfFName.getText(),tfLName.getText());
        String courseName = listView.getSelectionModel().getSelectedItem();
        Course courseToEnroll = registrationSystemController.getCourseById(Integer.parseInt(courseName.substring(courseName.length()-1)));
        System.out.println(Integer.parseInt(courseName.substring(courseName.length()-1)));
        registrationSystemController.register(courseToEnroll,studentToEnroll);
    }



    @FXML
    void btnCoursesClicked(ActionEvent event) {
        for (Course c : registrationSystemController.getAllCourses())
            listView.getItems().add(c.getName() + "leitet bei : " + registrationSystemController.getTeacherById(c.getTeacherId()).getLastName() + " mit credits " + c.getCredits() + " freie platze : " + registrationSystemController.calculateFreePlacesInCourse(c) + " von " + c.getCredits() + " mit id " + c.getCourseId());
    }

    @FXML
    void btnCreditsClicked(ActionEvent event) {
        Student studentToCalculate = registrationSystemController.getStudentByName(tfFName.getText(),tfLName.getText());
        if(studentToCalculate == null){
            CreditsLabel.setText("Sie sind nicht eingeschrieben");
            return;
        }
        int credits = registrationSystemController.calculateStudentCredits(studentToCalculate);
        CreditsLabel.setText("Sie haben : " + credits + " credite");
    }


}
