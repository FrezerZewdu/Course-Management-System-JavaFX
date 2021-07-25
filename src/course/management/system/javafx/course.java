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
public class course {

    protected SimpleIntegerProperty id = new SimpleIntegerProperty();
    protected SimpleStringProperty CourseTitle = new SimpleStringProperty();
    protected SimpleStringProperty CourseCode = new SimpleStringProperty();
    protected SimpleStringProperty Hours = new SimpleStringProperty();
    protected SimpleStringProperty CrHr = new SimpleStringProperty();
//
//    

    public int getid() {
        return this.id.get();
    }

    public void setid(int id) {
        this.id.set(id);
    }

    public String getCourseTitle() {
        return CourseTitle.get();
    }

    public void setCourseTitle(String CourseTitleStr) {
        CourseTitle.set(CourseTitleStr);
    }

    public String getCourseCode() {
        return CourseCode.get();
    }

    public void setCourseCode(String CourseCodeStr) {
        CourseCode.set(CourseCodeStr);
    }

    public String getHours() {
        return Hours.get();
    }

    public void setHours(String HoursStr) {
        Hours.set(HoursStr);
    }

    public String getCrHr() {
        return this.CrHr.get();
    }

    public void setCrHr(String CrHr) {
        this.CrHr.set(CrHr);
    }

//    /**
//     * @return the id
//     */
//    public SimpleIntegerProperty getId() {
//        return id;
//    }
//
//    /**
//     * @param id the id to set
//     */
//    public void setId(SimpleIntegerProperty id) {
//        this.id = id;
//    }
//
//    /**
//     * @return the FirstName
//     */
//    public SimpleStringProperty getFirstName() {
//        return FirstName;
//    }
//
//    /**
//     * @param FirstName the FirstName to set
//     */
//    public void setFirstName(SimpleStringProperty FirstName) {
//        this.FirstName = FirstName;
//    }
//
//    /**
//     * @return the MiddleName
//     */
//    public SimpleStringProperty getMiddleName() {
//        return MiddleName;
//    }
//
//    /**
//     * @param MiddleName the MiddleName to set
//     */
//    public void setMiddleName(SimpleStringProperty MiddleName) {
//        this.MiddleName = MiddleName;
//    }
//
//    /**
//     * @return the LastName
//     */
//    public SimpleStringProperty getLastName() {
//        return LastName;
//    }
//
//    /**
//     * @param LastName the LastName to set
//     */
//    public void setLastName(SimpleStringProperty LastName) {
//        this.LastName = LastName;
//    }
//
//    /**
//     * @return the Gender
//     */
//    public SimpleStringProperty getGender() {
//        return Gender;
//    }
//
//    /**
//     * @param Gender the Gender to set
//     */
//    public void setGender(SimpleStringProperty Gender) {
//        this.Gender = Gender;
//    }
//
//    /**
//     * @return the DOB
//     */
//    public SimpleStringProperty getDOB() {
//        return DOB;
//    }
//
//    /**
//     * @param DOB the DOB to set
//     */
//    public void setDOB(SimpleStringProperty DOB) {
//        this.DOB = DOB;
//    }
//
//    /**
//     * @return the Phone
//     */
//    public SimpleStringProperty getPhone() {
//        return Phone;
//    }
//
//    /**
//     * @param Phone the Phone to set
//     */
//    public void setPhone(SimpleStringProperty Phone) {
//        this.Phone = Phone;
//    }
//
//    /**
//     * @return the Address
//     */
//    public SimpleStringProperty getAddress() {
//        return Address;
//    }
//
//    /**
//     * @param Address the Address to set
//     */
//    public void setAddress(SimpleStringProperty Address) {
//        this.Address = Address;
//    }
//
//    /**
//     * @return the AcadamicYear
//     */
//    public SimpleStringProperty getAcadamicYear() {
//        return AcadamicYear;
//    }
//
//    /**
//     * @param AcadamicYear the AcadamicYear to set
//     */
//    public void setAcadamicYear(SimpleStringProperty AcadamicYear) {
//        this.AcadamicYear = AcadamicYear;
//    }
}
