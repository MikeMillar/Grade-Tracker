package Main;

import Main.Dialogs.AssignmentDialog;
import Main.Dialogs.ClassDialog;
import Main.Models.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

public class Controller {

    @FXML private VBox mainViewMaster;
    
    // Menu bar
    @FXML private MenuBar menuBar;
    
    // File Menu
    @FXML private Menu fileMenu;
    @FXML private MenuItem fmNew;
    @FXML private MenuItem fmOpen;
    @FXML private MenuItem fmOpenRecent;
    @FXML private MenuItem fmClose;
    @FXML private MenuItem fmSave;
    @FXML private MenuItem fmSaveAs;
    @FXML private MenuItem fmRevert;
    @FXML private MenuItem fmPreferences;
    @FXML private MenuItem fmQuit;
    
    // Edit Menu
    @FXML private Menu editMenu;
    @FXML private MenuItem emUndo;
    @FXML private MenuItem emRedo;
    @FXML private MenuItem emCut;
    @FXML private MenuItem emCopy;
    @FXML private MenuItem emPaste;
    @FXML private MenuItem emDelete;
    @FXML private MenuItem emSelectAll;
    @FXML private MenuItem emUnselectAll;
    
    // Help Menu
    @FXML private Menu helpMenu;
    @FXML private MenuItem hmAbout;
    
    // Main Display Panes
    @FXML private SplitPane mainSplitPane;
    @FXML private AnchorPane classesAnchorPane;
    @FXML private AnchorPane assignmentsAnchorPane;
    @FXML private AnchorPane detailsAnchorPane;
    
    // Classes Sub-Items
    @FXML private HBox classesTitleHbox;
    @FXML private Label classesLabel;
    @FXML private Button addClassBtn;
    @FXML private Button editClassBtn;
    @FXML private Button deleteClassBtn;
    @FXML private ListView<Classes> classesList;
    
    // Assignment Sub-Items
    @FXML private HBox assignmentTitleHbox;
    @FXML private Label assignmentLabel;
    @FXML private Button addAssignmentBtn;
    @FXML private Button editAssignmentBtn;
    @FXML private Button deleteAssignmentBtn;
    @FXML private TableView<Assignment> assignmentList;
    @FXML private TableColumn<Assignment, String> assignmentColumn;
    @FXML private TableColumn<Assignment, String> typeColumn;
    @FXML private TableColumn<Assignment, String> scoreColumn;
    @FXML private TableColumn<Assignment, String> gradeColumn;
    
    // Detail Sub-Items
    @FXML private HBox detailsTitleHbox;
    @FXML private Label detailsLabel;
    @FXML private ListView<String> detailList; // Add type to view
    
    // Lists
    public ArrayList<Classes> classesArrayList;
    public Task<ObservableList<Assignment>> assignmentTask;
    
    
    public void initialize() {
        mainSplitPane.setFocusTraversable(false);
        
        // Bind Height Values
        classesList.prefHeightProperty().bind(Main.stage.heightProperty()
                .subtract(menuBar.getHeight() + classesTitleHbox.getHeight() + 100));
        assignmentList.prefHeightProperty().bind(Main.stage.heightProperty()
                .subtract(menuBar.getHeight() + assignmentTitleHbox.getHeight() + 90));
        detailList.prefHeightProperty().bind(Main.stage.heightProperty()
                .subtract(menuBar.getHeight() + detailsTitleHbox.getHeight() + 100));
        
        // Bind Width Values
        classesAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.25));
        classesAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.15));
        detailsAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.25));
        detailsAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.15));
        assignmentsAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.7));
        assignmentsAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.5));
        
        classesList.prefWidthProperty().bind(classesAnchorPane.widthProperty().add(15));
        detailList.prefWidthProperty().bind(detailsAnchorPane.widthProperty().add(15));
        
        assignmentList.prefWidthProperty().bind(assignmentsAnchorPane.widthProperty());
        assignmentColumn.prefWidthProperty().bind(assignmentList.widthProperty().multiply(0.45));
        typeColumn.prefWidthProperty().bind(assignmentList.widthProperty().multiply(0.25));
        scoreColumn.prefWidthProperty().bind(assignmentList.widthProperty().multiply(0.15));
        gradeColumn.prefWidthProperty().bind(assignmentList.widthProperty().multiply(0.15));
        
        
        
        addClassBtn.setOnMouseEntered(e -> addClassBtn.setStyle("-fx-font-weight:bold;"));
        addClassBtn.setOnMouseExited(e -> addClassBtn.setStyle("-fx-background-color:transparent;"));
        editClassBtn.setOnMouseEntered(e -> editClassBtn.setStyle("-fx-font-weight:bold;"));
        editClassBtn.setOnMouseExited(e -> editClassBtn.setStyle("-fx-background-color:transparent;"));
        deleteClassBtn.setOnMouseEntered(e -> deleteClassBtn.setStyle("-fx-font-weight:bold;"));
        deleteClassBtn.setOnMouseExited(e -> deleteClassBtn.setStyle("-fx-background-color:transparent;"));
        addAssignmentBtn.setOnMouseEntered(e -> addAssignmentBtn.setStyle("-fx-font-weight:bold;"));
        addAssignmentBtn.setOnMouseExited(e -> addAssignmentBtn.setStyle("-fx-background-color:transparent;"));
        editAssignmentBtn.setOnMouseEntered(e -> editAssignmentBtn.setStyle("-fx-font-weight:bold;"));
        editAssignmentBtn.setOnMouseExited(e -> editAssignmentBtn.setStyle("-fx-background-color:transparent;"));
        deleteAssignmentBtn.setOnMouseEntered(e -> deleteAssignmentBtn.setStyle("-fx-font-weight:bold;"));
        deleteAssignmentBtn.setOnMouseExited(e -> deleteAssignmentBtn.setStyle("-fx-background-color:transparent;"));
    
    
    
        ObservableList<String> detailTest = FXCollections.observableArrayList();
        
//        // Test Data Seeding
//        for (int i = 0; i < 50; i++) {
//            Classes addClass = new Classes("TEST-Class", "TestCourse " + i, "Test Prof", "HW:15");
//            Datasource.getInstance().addClass(addClass);
//            for (int j = 0; j < 50; j++) {
//                addClass.addAssignment(new Assignment("TypeTest" + j, "HW",
//                        "Test description", 20, 20));
//            }
//            detailTest.add("Test" + i);
//        }
        
        classesList.setItems(Datasource.getInstance().getClasses());
        classesList.getSelectionModel().selectFirst();
        Classes selectedClass = classesList.getSelectionModel().getSelectedItem();
        
        assignmentList.setItems(selectedClass.getAssignments());
        detailList.setItems(detailTest);
        classesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Classes>() {
            @Override
            public void changed(ObservableValue<? extends Classes> observableValue, Classes classes, Classes t1) {
                assignmentList.setItems(classesList.getSelectionModel().getSelectedItem().getAssignments());
            }
        });
    }
    
    @FXML
    public void addClass() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainViewMaster.getScene().getWindow());
        dialog.setTitle("Add a New Course");
        dialog.setHeaderText("Fill in the information below and press OK to add a new class");
        FXMLLoader classLoader = new FXMLLoader();
        classLoader.setLocation(getClass().getResource("Dialogs\\ClassDialogFXML.fxml"));
        try {
            dialog.getDialogPane().setContent(classLoader.load());
        } catch (IOException e) {
            System.out.println("ERROR: Could not load the dialog");
            e.printStackTrace();
            return;
        }
        
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ClassDialog controller = classLoader.getController();
            Classes createdClass = controller.createClass();
            Datasource.getInstance().addClass(createdClass);
        }
        classesList.refresh();
    }
    
    @FXML
    public void editClass() {
        Classes selected = classesList.getSelectionModel().getSelectedItem();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainViewMaster.getScene().getWindow());
        dialog.setTitle("Edit an Existing Course");
        dialog.setHeaderText("Fill in the information below and press OK to edit the class");
        FXMLLoader classLoader = new FXMLLoader();
        classLoader.setLocation(getClass().getResource("Dialogs\\ClassDialogFXML.fxml"));
        try {
            dialog.getDialogPane().setContent(classLoader.load());
        } catch (IOException e) {
            System.out.println("ERROR: Could not load the dialog");
            e.printStackTrace();
            return;
        }
    
        ClassDialog controller = classLoader.getController();
        controller.loadClass(selected);
    
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.updateClass(selected);
        }
        classesList.refresh();
    }
    
    @FXML void deleteClass() {
        Classes selected = classesList.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Class");
        alert.setHeaderText("Are you sure you want to delete " + selected + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Datasource.getInstance().removeClass(selected);
        }
        classesList.refresh();
    }
    
    @FXML
    public void addAssignment() {
        Classes selectedClass = classesList.getSelectionModel().getSelectedItem();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainViewMaster.getScene().getWindow());
        dialog.setTitle("Add an Assignment");
        dialog.setHeaderText("Fill in the information below and press OK to add a new assignment");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Dialogs\\AssignmentDialogFXML.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("ERROR: Could not load the dialog");
            e.printStackTrace();
            return;
        }
        AssignmentDialog controller = loader.getController();
        controller.setComboBox(selectedClass);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Assignment assignment = controller.addAssignment();
            selectedClass.addAssignment(assignment);
        }
        assignmentList.refresh();
    }
    
    @FXML
    public void editAssignment() {
        Classes selectedClass = classesList.getSelectionModel().getSelectedItem();
        Assignment selectedAssignment = assignmentList.getSelectionModel().getSelectedItem();
    
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainViewMaster.getScene().getWindow());
        dialog.setTitle("Edit an Assignment");
        dialog.setHeaderText("Fill in the information below and press OK to edit the assignment");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Dialogs\\AssignmentDialogFXML.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("ERROR: Could not load the dialog");
            e.printStackTrace();
            return;
        }
        AssignmentDialog controller = loader.getController();
        controller.setComboBox(selectedClass);
        
        controller.loadAssignment(selectedAssignment);
        
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.updateAssignment(selectedAssignment);
        }
        assignmentList.refresh();
    }
    
    @FXML
    public void deleteAssignment() {
        Classes selectedClass = classesList.getSelectionModel().getSelectedItem();
        Assignment selectedAssignment = assignmentList.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Assignment");
        alert.setHeaderText("Are you sure you want to delete " + selectedAssignment.getName() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            selectedClass.deleteAssignment(selectedAssignment);
        }
    }
}
