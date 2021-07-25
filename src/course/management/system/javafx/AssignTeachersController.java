/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.management.system.javafx;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sebu
 */
public class AssignTeachersController extends AnchorPane {

    @FXML
    private Label close_btn;
    @FXML
    private JFXComboBox<String> combotName;
    @FXML
    private JFXComboBox<String> combocTitle;

    /**
     * Initializes the controller class.
     */
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    public String CourseTitle;
    public String TeacherName;
    private ObservableList<String> list;
    private ObservableList<String> list2;

    public AssignTeachersController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AssignTeachers.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void initialize() throws SQLException {
        // TODO
        combotName.setItems(FXCollections.observableArrayList(loadTeacher()));
        combocTitle.setItems(FXCollections.observableArrayList(loadCourse()));
    }

    @FXML
    private void handleAssign(ActionEvent e){
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            String sql = "  INSERT INTO assign"
                       + "  (TeachersName, CourseTitle)"
                       + "  VALUES (?,?)";

            ps = (PreparedStatement) con.prepareStatement(sql);
            
            TeacherName = (String) combotName.getSelectionModel().getSelectedItem();
            ps.setString(1, TeacherName);
            CourseTitle = (String) combocTitle.getSelectionModel().getSelectedItem();
            ps.setString(2, CourseTitle);
            
   
            ps.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Course Added Successfully");
            alert.showAndWait();

        } catch (Exception ex) {
            System.out.println("Error!" + ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Error!!" + ex);
            alert.showAndWait();
        }
    }
    
    
    private List<String> loadTeacher() throws SQLException {

        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();

        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            String query = "select FirstName from teachers";
            PreparedStatement statement = con.prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options.add(set.getString("FirstName"));
            }

            statement.close();
            set.close();

            // Return the List
            return options;

        } catch (Exception ex) {
            Logger.getLogger(AssignTeachersController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private List<String> loadCourse() {
        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();

        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            String query = "select CourseTitle from course";
            PreparedStatement statement = con.prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options.add(set.getString("CourseTitle"));
            }

            statement.close();
            set.close();

            // Return the List
            return options;

        } catch (Exception ex) {
            Logger.getLogger(AssignTeachersController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @FXML
    private void handleClose(MouseEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) close_btn.getScene().getWindow();
        // do what you have to do
        stage.close();

    }

}
