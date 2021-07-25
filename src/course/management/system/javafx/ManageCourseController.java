/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.management.system.javafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
 * @author ewca
 */
public class ManageCourseController extends AnchorPane {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tfsearch;

    @FXML
    private JFXTextField tfcode;

    @FXML
    private JFXTextField tftitle, tfid;

    @FXML
    private JFXTextField tfhour;

    @FXML
    private JFXTextField tfcr;

    @FXML
    private Label lcountTotalCourse;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private TableView<course> courseView;

    @FXML
    public TableColumn<course, Integer> tvid;

    @FXML
    private TableColumn<course, String> tvcode;

    @FXML
    private TableColumn<course, String> tvtitle;

    @FXML
    private TableColumn<course, String> tvhour;

    @FXML
    private TableColumn<course, String> tvcr;

    private ObservableList<course> data;
    private static Connection con;
    private PreparedStatement ps;
//    DBClass objDbClass;
    ResultSet rs = null;

    public ManageCourseController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageCourse.fxml"));
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

        updateTable();

    }

    @FXML
    private void handleTable(MouseEvent event) {

        tableSelection();

    }

    @FXML
    private void handleUpdate() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");

            //
            String id = tfid.getText();
            String title = tftitle.getText();
            String code = tfcode.getText();
            String hour = tfhour.getText();
            String cr = tfcr.getText();

            String updateQuery = "UPDATE course SET CourseTitle='" + title + "',CourseCode='" + code + "',Hours='" + hour + "',Cr_Hr='" + cr + "' WHERE id='" + id + "'";
            ps = con.prepareStatement(updateQuery);
            ps.execute();
//            PreparedStatement pst = con.prepareStatement(updateQuery);
//            pst.setString(1, fname);
//            pst.setString(2, mname);
//            pst.setString(3, lname);
//            pst.setString(4, Gender);
//            pst.setString(5, dob);
//            pst.setString(6, phone);
//            pst.setString(7, address);
//            pst.setString(8, ayear);

//            pst.executeUpdate();
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
        tfid.setText(" ");
        tftitle.setText(" ");
        tfcode.setText(" ");
        tfhour.setText(" ");
        tfcr.setText(" ");
    }

    @FXML
    private void handlePrint(ActionEvent event) {

        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null && job.showPrintDialog(courseView.getScene().getWindow())) {
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL_OPPOSITES);
//             jobStatus.textProperty().bind(job.jobStatusProperty().asString());
            //    printAll.getTop().setVisible(false);
            //    printAll.getTop().setManaged(false);
            double width = courseView.getWidth();
            double height = courseView.getHeight();

            PrintResolution resolution = job.getJobSettings().getPrintResolution();

            width /= resolution.getFeedResolution();
            height /= resolution.getCrossFeedResolution();
            double scaleX = pageLayout.getPrintableWidth() / width / 600;
            double scaleY = pageLayout.getPrintableHeight() / height / 600;

            Scale scale = new Scale(scaleX, scaleY);

            courseView.getTransforms().add(scale);

            boolean success = job.printPage(pageLayout, courseView);
            if (success) {
                job.endJob();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText(null);
                alert.setContentText("Printed");
                alert.showAndWait();
            }
            courseView.getTransforms().remove(scale);
        }
        courseView.setManaged(true);
//        PrinterJob printerJob = PrinterJob.createPrinterJob();
//        if (printerJob.showPrintDialog(stage) && printerJob.printPage(studenView)) {
//            printerJob.endJob();
//            System.out.println("printed");

//     }
    }

    private void tableSelection() {
        try {

            course c = (course) courseView.getItems().get(courseView.getSelectionModel().getSelectedIndex());
//                students s = (students) studenView.getSelectionModel().getSelectedItem();
            String query = "select * from course where id=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, c.getid());
            rs = ps.executeQuery();

            while (rs.next()) {
                tfid.setText(rs.getString("id"));
                tftitle.setText(rs.getString("CourseTitle"));
                tfcode.setText(rs.getString("CourseCode"));
                tfhour.setText(rs.getString("Hours"));
                tfcr.setText(rs.getString("Cr_Hr"));

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
            String query = "select Count(id) from course";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String count = rs.getString("count(id)");
                lcountTotalCourse.setText(count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateTable() {

        tvid.setCellValueFactory(new PropertyValueFactory("ID"));
        tvtitle.setCellValueFactory(new PropertyValueFactory("CourseTitle"));
        tvcode.setCellValueFactory(new PropertyValueFactory("CourseCode"));
        tvhour.setCellValueFactory(new PropertyValueFactory("Hours"));
        tvcr.setCellValueFactory(new PropertyValueFactory("CrHr"));

        loadData();
        countNotification();
    }

    public void loadData() {
        data = FXCollections.observableArrayList();
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            String sql = "select * from course";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                course c = new course();

                c.id.set(rs.getInt("id"));
                c.CourseTitle.set(rs.getString("CourseTitle"));
                c.CourseCode.set(rs.getString("CourseCode"));
                c.Hours.set(rs.getString("Hours"));
                c.CrHr.set(rs.getString("Cr_Hr"));

                data.add(c);
            }
            courseView.setItems(data);

            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<course> filteredData = new FilteredList<>(data, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(course -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (course.getCourseTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches Course Title.
                    } else if (course.getCourseCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches Course Code.
                    } else {
                        return false; // Does not match.
                    }
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<course> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(courseView.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            courseView.setItems(sortedData);

        } catch (Exception e) {
            e.printStackTrace();;
            System.out.println("error");
        }

    }

}
