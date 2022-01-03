package com.example.lab6.controllers;

import com.example.lab6.models.Student;
import com.example.lab6.repos.ICrudRepo;
import com.example.lab6.repos.StudentJDBCRepo;

import java.util.Collections;
import java.util.List;

public class StudentController {
    ICrudRepo repo;



    public StudentController(ICrudRepo repo) {
        this.repo = repo;
    }



    /**
     * Adds a student to the repository
     * @param s student to be added
     */
    public void addStudentToRepo(Student s){
        this.repo.create(s);
    }

    /**
     * Finds a student object based on its id attribute
     * @param id id of the student
     * @return student object with said id null if not found
     */
    public Student getStudentById(int id){
        List<Student> all = repo.getAll();
        for(Student s : all){
            if(s.getStudentId() == id){
                return s;
            }
        }
        return null;
    }

    /**
     * Gets all the students in the repository
     * @return list of all students
     */
    public List<Student> getAllStudents(){
        return repo.getAll();
    }

    /**
     * Gets all of the students in the repository in alphabetical order
     * @return list of all students sorted
     */
    public List<Student> getSortedStudentsAlphabetic(){
        List<Student> all =repo.getAll();
        Collections.sort(all);
        return all;
    }




}
