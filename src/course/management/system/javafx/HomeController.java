/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.management.system.javafx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Sebu
 */
public class HomeController extends AnchorPane {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label luname,lcountTotalStudents,lcountTotalTeachers,lcountPM;
    
    String u = Register.getusername();
    
    public HomeController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
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
     luname.setText(u);
     countNotification();
    }
    
    @FXML
    private void handleAssign(ActionEvent event){
        try {
            AssignTeachersController root = new AssignTeachersController();

            Stage stage = new Stage();
//            Image logo = new Image("/icons/logo2.jpg");
//            stage.getIcons().add(logo);
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Error!" + e);
        }
    }

    @FXML
    private void handleSearch(ActionEvent event){
        try {
            SearchController root = new SearchController();

            Stage stage = new Stage();
//            Image logo = new Image("/icons/logo2.jpg");
//            stage.getIcons().add(logo);
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Error!" + e);
        }
    }
    
    @FXML
    private void handleAddCourse(ActionEvent event){
        try {
            AddCourseController root = new AddCourseController();

            Stage stage = new Stage();
//            Image logo = new Image("/icons/logo2.jpg");
//            stage.getIcons().add(logo);
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Error!" + e);
        }
    }
    
    @FXML
    private void handleManageCourse(ActionEvent event) {
        try {
            ManageCourseController root = new ManageCourseController();

            Stage stage = new Stage();
//            Image logo = new Image("/icons/logo2.jpg");
//            stage.getIcons().add(logo);
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Error!" + e);
        }
    }

    @FXML
    private void handleManageStudent(ActionEvent event) {
        try {
            ManageStudentController root = new ManageStudentController();

            Stage stage = new Stage();
//            Image logo = new Image("/icons/logo2.jpg");
//            stage.getIcons().add(logo);
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Error!" + e);
        }
    }

   private void countNotification() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            Statement st = con.createStatement();
            String query = "select Count(ID) from students";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String count = rs.getString("count(ID)");
                lcountTotalStudents.setText(count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            Statement st = con.createStatement();
            String query = "select Count(ID) from teachers";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String count = rs.getString("count(ID)");
                lcountTotalTeachers.setText(count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            Statement st = con.createStatement();
            String query = "select Count(id) from users";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String count = rs.getString("count(id)");
                lcountPM.setText(count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleRgisterStudent(ActionEvent event) {
        try {
            RegisterStudentController root = new RegisterStudentController();

            Stage stage = new Stage();
//            Image logo = new Image("/icons/logo2.jpg");
//            stage.getIcons().add(logo);
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Error!" + e);
        }
    }
    
    @FXML
    private void handleRgisterTeacher(ActionEvent event) {
        try {
            RegisterTeacherController root = new RegisterTeacherController();

            Stage stage = new Stage();
//            Image logo = new Image("/icons/logo2.jpg");
//            stage.getIcons().add(logo);
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Error!" + e);
        }
    }
    
    @FXML
    private void handleLogout(ActionEvent e) {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        LoginController root = new LoginController();
        Stage s = new Stage();
        Scene scene = new Scene(root);
        s.initStyle(StageStyle.UNDECORATED);
        s.setScene(scene);
        s.show();
    }

}
