/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.management.system.javafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class RegisterTeacherController extends AnchorPane {

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
    private JFXTextField tfemail;

    @FXML
    private JFXTextField tfphone;

    @FXML
    private JFXRadioButton rbgenderMale;

    @FXML
    private JFXRadioButton rbgenderFemale;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton clearBtn;

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    public String Gender;

    public RegisterTeacherController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterTeacher.fxml"));
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
    public void initialize() {
        // TODO
    }

    @FXML
    private void handleAdd(ActionEvent event) {

        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            String sql = "  INSERT INTO teachers "
                    + "  (FirstName, MiddleName, LastName, Gender, Email, Phone)"
                    + "  VALUES (?,?,?,?,?,?)";

            ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, tffname.getText());
            ps.setString(2, tfmname.getText());
            ps.setString(3, tflname.getText());
            
            ps.setString(4, Gender);
            ps.setString(5, tfemail.getText());
            ps.setString(6, tfphone.getText());
           

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
        tfemail.setText(" ");
        rbgenderMale.setSelected(false);
        rbgenderFemale.setSelected(false);

    }

    @FXML
    private void handleClose(MouseEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) close_btn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
