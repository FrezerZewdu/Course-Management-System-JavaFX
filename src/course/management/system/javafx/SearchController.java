/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.management.system.javafx;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sebu
 */
public class SearchController extends AnchorPane {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label close_btn,lcountTotal;

    @FXML
    private JFXTextField tfsearch;

    @FXML
    private TableView<assign> assignView;

    @FXML
    private TableColumn<assign, ?> tvid;

    @FXML
    private TableColumn<assign, String> tvname;

    @FXML
    private TableColumn<assign, String> tvtitle;

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ObservableList<assign> data;

    public SearchController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void initialize() {
        // TODO
        updateTable();
    }

    @FXML
    private void handlePrint(ActionEvent event) {

        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null && job.showPrintDialog(assignView.getScene().getWindow())) {
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL_OPPOSITES);
//             jobStatus.textProperty().bind(job.jobStatusProperty().asString());
            //    printAll.getTop().setVisible(false);
            //    printAll.getTop().setManaged(false);
            double width = assignView.getWidth();
            double height = assignView.getHeight();

            PrintResolution resolution = job.getJobSettings().getPrintResolution();

            width /= resolution.getFeedResolution();
            height /= resolution.getCrossFeedResolution();
            double scaleX = pageLayout.getPrintableWidth() / width / 600;
            double scaleY = pageLayout.getPrintableHeight() / height / 600;

            Scale scale = new Scale(scaleX, scaleY);

            assignView.getTransforms().add(scale);

            boolean success = job.printPage(pageLayout, assignView);
            if (success) {
                job.endJob();
            }
            assignView.getTransforms().remove(scale);
        }
        assignView.setManaged(true);
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
        tvname.setCellValueFactory(new PropertyValueFactory("TeacherName"));
        tvtitle.setCellValueFactory(new PropertyValueFactory("CourseTitle"));
        

        loadData();

//        setCellValueFromTableToTextField();
        countNotification();
    }

    public void loadData() {
        data = FXCollections.observableArrayList();
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            String sql = "select * from assign";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                assign a = new assign();

                a.id.set(rs.getInt("id"));
                a.TeacherName.set(rs.getString("TeachersName"));
                a.CourseTitle.set(rs.getString("CourseTitle"));

                data.add(a);
            }
            assignView.setItems(data);

            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<assign> filteredData = new FilteredList<>(data, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(assign -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare Teacher Name and Course Title of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (assign.getTeacherName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches Teacher Name.
                    } else if (assign.getCourseTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches Course Title.
                    } else {
                        return false; // Does not match.
                    }
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<assign> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(assignView.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            assignView.setItems(sortedData);

        } catch (Exception e) {
            e.printStackTrace();;
            System.out.println("error");
        }

    }
    
    private void countNotification() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cms", "root", "");
            Statement st = con.createStatement();
            String query = "select Count(ID) from assign";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String count = rs.getString("count(ID)");
                lcountTotal.setText(count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
