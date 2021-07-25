/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.management.system.javafx;


import com.jfoenix.controls.JFXComboBox;
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
public class AddCourseController extends AnchorPane {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label close_btn;
    
    @FXML
    private JFXTextField tfcode;

    @FXML
    private JFXTextField tftitle;

    @FXML
    private JFXComboBox<String> comboHour;

    @FXML
    private JFXComboBox<String> comboCr;
    
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    public String Hours;
    public String CreditHour;
    private ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");
    private ObservableList<String> list2 = FXCollections.observableArrayList("1", "2", "3", "4");
    
    public AddCourseController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCourse.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
            comboHour.setItems(list);
            comboCr.setItems(list2);
    }
    
    @FXML
    private void handleAdd(ActionEvent event) {

        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            String sql = "  INSERT INTO course"
                       + "  (CourseTitle, CourseCode, Hours, Cr_Hr)"
                       + "  VALUES (?,?,?,?)";

            ps = (PreparedStatement) con.prepareStatement(sql);
            
            ps.setString(1, tftitle.getText());
            ps.setString(2, tfcode.getText());
            Hours = (String) comboHour.getSelectionModel().getSelectedItem();
            ps.setString(3, Hours);
            CreditHour = (String) comboCr.getSelectionModel().getSelectedItem();
            ps.setString(4, CreditHour);
   
            ps.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Course Added Successfully");
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
        
        tftitle.setText(" ");
        tfcode.setText(" ");
        
    }


    @FXML
    private void handleClose(MouseEvent event) {
   // get a handle to the stage
    Stage stage = (Stage) close_btn.getScene().getWindow();
    // do what you have to do
    stage.close();
    
    }
}
