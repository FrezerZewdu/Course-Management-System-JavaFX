/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.management.system.javafx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Sebu
 */
public class assign {
    protected SimpleIntegerProperty id = new SimpleIntegerProperty();
    protected SimpleStringProperty TeacherName = new SimpleStringProperty();
    protected SimpleStringProperty CourseTitle = new SimpleStringProperty();
 
//
//    

    public int getid() {
        return this.id.get();
    }

    public void setid(int id) {
        this.id.set(id);
    }
    
     public String getTeacherName() {
        return TeacherName.get();
    }

    public void setTeacherName(String TeacherNamesStr) {
        TeacherName.set(TeacherNamesStr);
    }

    public String getCourseTitle() {
        return CourseTitle.get();
    }

    public void setCourseTitle(String CourseTitleStr) {
        CourseTitle.set(CourseTitleStr);
    }

   

   
}
