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
 * @author sebu
 */
public class students {

    protected SimpleIntegerProperty id = new SimpleIntegerProperty();
//  protected  SimpleStringProperty id = new SimpleStringProperty();
    protected SimpleStringProperty FirstName = new SimpleStringProperty();
    protected SimpleStringProperty MiddleName = new SimpleStringProperty();
    protected SimpleStringProperty LastName = new SimpleStringProperty();
    protected SimpleStringProperty Gender = new SimpleStringProperty();
    protected SimpleStringProperty DOB = new SimpleStringProperty();
    protected SimpleStringProperty Phone = new SimpleStringProperty();
    protected SimpleStringProperty Address = new SimpleStringProperty();
    protected SimpleStringProperty AcadamicYear = new SimpleStringProperty();
    protected SimpleStringProperty Year = new SimpleStringProperty();
    protected SimpleStringProperty Type = new SimpleStringProperty();

//
//    
    public int getId() {
        return this.id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return FirstName.get();
    }

    public void setFirstName(String firstNameStr) {
        FirstName.set(firstNameStr);
    }

    public String getMiddleName() {
        return MiddleName.get();
    }

    public void setMiddleName(String middleNameStr) {
        MiddleName.set(middleNameStr);
    }

    public String getLastName() {
        return LastName.get();
    }

    public void setLastName(String lastNameStr) {
        LastName.set(lastNameStr);
    }

    public void setGender(String gender) {
        this.Gender.set(gender);
    }

    public String getGender() {
        return this.Gender.get();
    }

    public void setDOB(String dob) {
        this.Phone.set(dob);
    }

    public String getDOB() {
        return this.DOB.get();
    }

    public void setPhone(String phone) {
        this.Phone.set(phone);
    }

    public String getPhone() {
        return this.Phone.get();
    }

    public String getAddress() {
        return Address.get();
    }

    public void setAddress(String address) {
        this.Address.set(address);
    }

    public void setAcadamicYear(String ayear) {
        this.AcadamicYear.set(ayear);
    }

    public String getAcadamicYear() {
        return this.AcadamicYear.get();
    }

    public void setYear(String year) {
        this.Year.set(year);
    }

    public String getYear() {
        return this.Year.get();
    }

    public String getType() {
        return this.Type.get();
    }

    public void setType(String Type) {
        this.Type.set(Type);
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
