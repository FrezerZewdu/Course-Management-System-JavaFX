/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.management.system.javafx;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ewca
 */
public class LoginController extends AnchorPane {

    /**
     * Initializes the controller class.
     */
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @FXML
    private Label close_btn;

    @FXML
    private JFXTextField tfuname;

    @FXML
    private JFXPasswordField tfpass;

    @FXML
    private Label lerror;

    public LoginController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
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

    }

    @FXML
    private void handleLogin(ActionEvent event) {
        
        try {
            

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", ""); // 
            // Get the text from the textField
            String username = tfuname.getText();
            String password = tfpass.getText();
            String EncryptPassword = getEncodedString(password); //Encrypt the password 
            Register.setusername(username);
            //Query
            String sql = "select * from users where username=? and password=? ";

            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, EncryptPassword);

            rs = ps.executeQuery();

            if (rs.next()) {
                lerror.setTextFill(Color.GREEN);
                lerror.setText("Login Successful ... Redirecting...");

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                HomeController root = new HomeController();
                Stage s = new Stage();
                s.setTitle("CMS Desktop App");
//                Image logo = new Image("/icons/logo2.jpg");
//                s.getIcons().add(logo);
                Scene scene = new Scene(root);
                s.initStyle(StageStyle.DECORATED);
                s.setScene(scene);
                s.show();

            } else {
                lerror.setTextFill(Color.TOMATO);
                lerror.setText("Error !!! Incorrect Username and/or Password. Please Try Again");
                System.err.println("Wrong Login --//");

            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static String getEncodedString(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    private static String getDecodedString(String EncryptPassword) {
        return new String(Base64.getMimeDecoder().decode(EncryptPassword));
    }

    @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }
}
