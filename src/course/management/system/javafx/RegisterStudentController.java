/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.management.system.javafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ewca
 */
public class RegisterStudentController extends AnchorPane {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label close_btn;

    @FXML
    private JFXTextField tffname;

    @FXML
    private JFXTextField tfmname;

    @FXML
    private JFXTextField tflname;

    @FXML
    private JFXTextField tfphone;

    @FXML
    private JFXTextField tfaddress;

    @FXML
    private JFXDatePicker tfdob;

    @FXML
    private JFXRadioButton genderMale;

    @FXML
    private JFXRadioButton genderFemale;

    @FXML
    private JFXComboBox<String> comboYear,comboYear1,comboType;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton clearBtn;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    public String Gender;
    public String Year;
    public String Year2;
    public String Type;
    private ObservableList<String> list = FXCollections.observableArrayList("2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025");
    private ObservableList<String> list2 = FXCollections.observableArrayList("1", "2", "3", "4", "5");
    private ObservableList<String> list3 = FXCollections.observableArrayList("Normal Load", "Under Load");

    public RegisterStudentController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterStudent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void handleMale(ActionEvent event) {
        Gender = "Male";
    }

    @FXML
    private void handleFemale(ActionEvent event) {
        Gender = "Female";
    }

    @FXML
    private void initialize() {
        comboYear.setItems(list);
        comboYear1.setItems(list2);
        comboType.setItems(list3);
    }

    @FXML
    private void handleAdd(ActionEvent event) {

        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            String sql = "  INSERT INTO students "
                       + "  (FirstName, MiddleName, LastName, Gender, DOB, Phone, Address, AcadamicYear, Year, RegType)"
                       + "  VALUES (?,?,?,?,?,?,?,?,?,?)";

            ps = (PreparedStatement) con.prepareStatement(sql);
            
            ps.setString(1, tffname.getText());
            ps.setString(2, tfmname.getText());
            ps.setString(3, tflname.getText());
            ps.setString(4, Gender);
            ps.setString(5, ((JFXTextField) tfdob.getEditor()).getText());
            ps.setString(6, tfphone.getText());
            ps.setString(7, tfaddress.getText());
            Year = (String) comboYear.getSelectionModel().getSelectedItem();
            ps.setString(8, Year);
            Year2 = (String) comboYear1.getSelectionModel().getSelectedItem();
            ps.setString(9, Year2);
            Type = (String) comboType.getSelectionModel().getSelectedItem();
            ps.setString(10, Type);

            ps.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Information Added Successfully");
            alert.showAndWait();

        } catch (Exception e) {
            System.out.println("Error!" + e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Error!!" + e);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        
        tffname.setText(" ");
        tfmname.setText(" ");
        tflname.setText(" ");
        tfphone.setText(" ");
        tfaddress.setText(" ");
        tfdob.setValue(null);
        genderMale.setSelected(false);
        genderFemale.setSelected(false);
        
    }

    @FXML
    private void handleClose(MouseEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) close_btn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
