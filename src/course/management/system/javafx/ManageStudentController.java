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
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrintResolution;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;

/**
 * FXML Controller class
 *
 * @author sebu
 */
public class ManageStudentController extends AnchorPane {

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    private Label lcountTotalStudents;

    @FXML
    private JFXTextField tffname, tfid;

    @FXML
    private JFXTextField tfmname;

    @FXML
    private JFXTextField tfdob;

    @FXML
    private JFXTextField tflname;

    @FXML
    private JFXTextField tfaddress, tfphone, tfyear, tfyear1, tftype;

    @FXML
    private JFXRadioButton genderMale;

    @FXML
    private JFXRadioButton genderFemale;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton clearBtn;

    @FXML
    private TextField tfsearch;

    @FXML
    private TableView<students> studenView;

    @FXML
    private TableColumn<students, String> tvid;

    @FXML
    private TableColumn<students, String> tvfname;

    @FXML
    private TableColumn<students, String> tvmname;

    @FXML
    private TableColumn<students, String> tvlname;

    @FXML
    private TableColumn<students, String> tvgender;

    @FXML
    private TableColumn<students, String> tvdob;

    @FXML
    private TableColumn<students, String> tvphone;

    @FXML
    private TableColumn<students, String> tvaddress;

    @FXML
    private TableColumn<students, String> tvayear;

    @FXML
    private TableColumn<students, String> tvyear;
    
    @FXML
    private TableColumn<students, String> tvtype;

    private ObservableList<students> data;
    private static Connection con;
    private PreparedStatement ps;
//    DBClass objDbClass;
    ResultSet rs = null;
    public String Gender;

    public ManageStudentController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manageStudent.fxml"));
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

        updateTable();

    }

    @FXML
    private void handleTable(MouseEvent event) {

        tableSelection();

    }

    @FXML
    private void handleUpdate(ActionEvent event) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");

            //
            String id = tfid.getText();
            String fname = tffname.getText();
            String mname = tfmname.getText();
            String lname = tflname.getText();
            String dob = tfdob.getText();
            String phone = tfphone.getText();
            String address = tfaddress.getText();
            String ayear = tfyear.getText();
            String year = tfyear1.getText();
            String type = tftype.getText();
            
            String updateQuery = "UPDATE students SET FirstName='" + fname + "',MiddleName='" + mname + "',LastName='" + lname + "',Gender='" + Gender + "',DOB='" + dob + "',Phone='" + phone + "',Address='" + address + "',AcadamicYear='" + ayear + "' ,Year='" + year + "' ,RegType='" + type + "' WHERE ID='" + id + "'";
            ps = con.prepareStatement(updateQuery);
            ps.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Info updated Successfully");
            alert.showAndWait();

            updateTable();

        } catch (Exception ex) {
//    JOptionPane.showMessageDialog(null, ex);
        }

    }

    @FXML
    private void handleClear(ActionEvent event) {

        tffname.setText(" ");
        tfmname.setText(" ");
        tflname.setText(" ");
        tfphone.setText(" ");
        tfaddress.setText(" ");
        tfdob.setText(" ");
        genderMale.setSelected(false);
        genderFemale.setSelected(false);
        tfyear.setText(" ");
        tfyear1.setText(" ");
        tftype.setText(" ");

    }

    @FXML
    private void handlePrint(ActionEvent event) {

        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null && job.showPrintDialog(studenView.getScene().getWindow())) {
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL_OPPOSITES);
//             jobStatus.textProperty().bind(job.jobStatusProperty().asString());
            //    printAll.getTop().setVisible(false);
            //    printAll.getTop().setManaged(false);
            double width = studenView.getWidth();
            double height = studenView.getHeight();

            PrintResolution resolution = job.getJobSettings().getPrintResolution();

            width /= resolution.getFeedResolution();
            height /= resolution.getCrossFeedResolution();
            double scaleX = pageLayout.getPrintableWidth() / width / 600;
            double scaleY = pageLayout.getPrintableHeight() / height / 600;

            Scale scale = new Scale(scaleX, scaleY);

            studenView.getTransforms().add(scale);

            boolean success = job.printPage(pageLayout, studenView);
            if (success) {
                job.endJob();
            }
            studenView.getTransforms().remove(scale);
        }
        studenView.setManaged(true);
//        PrinterJob printerJob = PrinterJob.createPrinterJob();
//        if (printerJob.showPrintDialog(stage) && printerJob.printPage(studenView)) {
//            printerJob.endJob();
//            System.out.println("printed");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Printed");
        alert.showAndWait();
//     }
    }

    private void updateTable() {

        tvid.setCellValueFactory(new PropertyValueFactory("ID"));
        tvfname.setCellValueFactory(new PropertyValueFactory("FirstName"));
        tvmname.setCellValueFactory(new PropertyValueFactory("MiddleName"));
        tvlname.setCellValueFactory(new PropertyValueFactory("LastName"));
        tvgender.setCellValueFactory(new PropertyValueFactory("Gender"));
        tvdob.setCellValueFactory(new PropertyValueFactory("DOB"));
        tvphone.setCellValueFactory(new PropertyValueFactory("phone"));
        tvaddress.setCellValueFactory(new PropertyValueFactory("address"));
        tvayear.setCellValueFactory(new PropertyValueFactory("AcadamicYear"));
        tvyear.setCellValueFactory(new PropertyValueFactory("Year"));
        tvtype.setCellValueFactory(new PropertyValueFactory("RegType"));

        loadData();

//        setCellValueFromTableToTextField();
        countNotification();
    }

    private void tableSelection() {
        try {

            students s = (students) studenView.getItems().get(studenView.getSelectionModel().getSelectedIndex());
//                students s = (students) studenView.getSelectionModel().getSelectedItem();
            String query = "select * from students where ID=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, s.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                tfid.setText(rs.getString("ID"));
                tffname.setText(rs.getString("FirstName"));
                tfmname.setText(rs.getString("MiddleName"));
                tflname.setText(rs.getString("LastName"));
                if (null != rs.getString("Gender")) {
                    switch (rs.getString("Gender")) {
                        case "Male":
                            genderMale.setSelected(true);
                            break;
                        case "Female":
                            genderFemale.setSelected(true);
                            break;
                        default:
                            genderMale.setSelected(false);
                            genderFemale.setSelected(false);
                    }
                } else {
                    genderMale.setSelected(false);
                    genderFemale.setSelected(false);
                }
                tfdob.setText(rs.getString("DOB"));
                tfphone.setText(rs.getString("Phone"));
                tfaddress.setText(rs.getString("Address"));
                tfyear.setText(rs.getString("AcadamicYear"));
                tfyear1.setText(rs.getString("Year"));
                tftype.setText(rs.getString("RegType"));
            }

        } catch (Exception ex) {
            System.err.print(ex);
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
    }

    public void loadData() {
        data = FXCollections.observableArrayList();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            String sql = "select * from students";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                students s = new students();
                s.id.set(rs.getInt("ID"));
                s.FirstName.set(rs.getString("FirstName"));
                s.MiddleName.set(rs.getString("MiddleName"));
                s.LastName.set(rs.getString("LastName"));
                s.Gender.set(rs.getString("Gender"));
                s.DOB.set(rs.getString("DOB"));
                s.Phone.set(rs.getString("Phone"));
                s.Address.set(rs.getString("Address"));
                s.AcadamicYear.set(rs.getString("AcadamicYear"));
                s.Year.set(rs.getString("Year"));
                s.Type.set(rs.getString("RegType"));
                
                data.add(s);
            }
            studenView.setItems(data);

            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<students> filteredData = new FilteredList<>(data, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(students -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every students with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (students.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches first name.
                    } else if (students.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else if (String.valueOf(students.getAcadamicYear()).indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches AcadamicYear.
                    } else {
                        return false; // Does not match.
                    }
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<students> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(studenView.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            studenView.setItems(sortedData);

        } catch (Exception e) {
            e.printStackTrace();;
            System.out.println("error");
        }

    }

}
