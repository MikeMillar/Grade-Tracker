package Main;

import Main.Dialogs.AssignmentDialog;
import Main.Dialogs.ClassDialog;
import Main.Dialogs.StackedPanes;
import Main.Models.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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
    @FXML private ScrollPane detailsScrollPane;
    @FXML private VBox detailsVBox;
    @FXML private TitledPane courseDetailsPane;
    @FXML private Label courseDetailNameLabel;
    @FXML private Label professorDetailLabel;
    @FXML private Label contactDetailLabel;
    @FXML private Label officeHoursDetailLabel;
    @FXML private Label classTimesDetailLabel;
    @FXML private Label gradeDetailLabel;
    
    @FXML private TitledPane assignmentDetailsPane;
    
    
    @FXML private TitledPane upcomingDetailPane;
    @FXML private ListView<String> upcomingList;
    
//    @FXML private ListView<String> detailList; // Add type to view
    
    // Lists
    public ArrayList<Classes> classesArrayList;
    public Task<ObservableList<Assignment>> assignmentTask;
    
    
    public void initialize() {
        mainSplitPane.setFocusTraversable(false);
        
        courseDetailsPane = new TitledPane();
        assignmentDetailsPane = new TitledPane();
        upcomingDetailPane = new TitledPane();
        TitledPane test1 = new TitledPane();
        TitledPane test2 = new TitledPane();
        TitledPane test3 = new TitledPane();
        TitledPane test4 = new TitledPane();
        TitledPane test5 = new TitledPane();
        TitledPane test6 = new TitledPane();
        TitledPane test7 = new TitledPane();
        TitledPane test8 = new TitledPane();
        TitledPane test9 = new TitledPane();
        TitledPane test10 = new TitledPane();
        TitledPane test11 = new TitledPane();
        TitledPane test12 = new TitledPane();
        TitledPane test13 = new TitledPane();
        TitledPane test14 = new TitledPane();
        TitledPane test15 = new TitledPane();
        TitledPane test16 = new TitledPane();
        TitledPane test17 = new TitledPane();
        TitledPane test18 = new TitledPane();
        TitledPane test19 = new TitledPane();
        TitledPane test20 = new TitledPane();
        TitledPane test21 = new TitledPane();
        TitledPane test22 = new TitledPane();
        TitledPane test23 = new TitledPane();
        TitledPane test24 = new TitledPane();
        TitledPane test25 = new TitledPane();
        TitledPane test26 = new TitledPane();
        TitledPane test27 = new TitledPane();
        TitledPane test28 = new TitledPane();
        TitledPane test29 = new TitledPane();
        TitledPane test30 = new TitledPane();
        TitledPane test31 = new TitledPane();
        TitledPane test32 = new TitledPane();
    
//        ScrollPane stackedScroll = new ScrollPane();

////        stackedScroll.prefWidth(250);
        
        StackedPanes stackedPanes = new StackedPanes(detailsAnchorPane, courseDetailsPane, assignmentDetailsPane,
                upcomingDetailPane, test1, test2, test3, test4, test5, test6, test7, test8, test9, test10, test11,
                test12, test13, test14, test15, test16, test17, test18, test19, test20, test21, test22, test23,
                test24, test25, test26, test27, test28, test29, test30, test31, test32);
        
        detailsScrollPane.setContent(stackedPanes);
        for (TitledPane tp: stackedPanes.getPaneList()) {
            GridPane gridPane = new GridPane();
            for (int i = 0; i < 4; i++) {
                gridPane.addRow(i, new Label("Test Label " + (i + 1)));
            }
            tp.setText("Pane Test");
            tp.setContent(gridPane);
        }
        detailsAnchorPane.getChildren().setAll(detailsTitleHbox, detailsScrollPane);
        
        // Bind Height Values
        classesList.prefHeightProperty().bind(Main.stage.heightProperty()
                .subtract(menuBar.getHeight() + classesTitleHbox.getHeight() + 100));
        assignmentList.prefHeightProperty().bind(Main.stage.heightProperty()
                .subtract(menuBar.getHeight() + assignmentTitleHbox.getHeight() + 90));
        detailsScrollPane.prefHeightProperty().bind(Main.stage.heightProperty()
                .subtract(menuBar.getHeight() + detailsTitleHbox.getHeight() + 100));
        
        // Bind Width Values
        classesAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.9));
        classesAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.05));
        detailsAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.9));
        detailsAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.05));
        assignmentsAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.9));
        assignmentsAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.05));
        
        classesList.prefWidthProperty().bind(classesAnchorPane.widthProperty().add(15));
        detailsScrollPane.prefWidthProperty().bind(detailsAnchorPane.widthProperty().add(15));
        
        assignmentList.prefWidthProperty().bind(assignmentsAnchorPane.widthProperty());
        assignmentColumn.prefWidthProperty().bind(assignmentList.widthProperty().multiply(0.35));
        typeColumn.prefWidthProperty().bind(assignmentList.widthProperty().multiply(0.35));
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
        
        
        classesList.setItems(Datasource.getInstance().getClasses());
        classesList.getSelectionModel().selectFirst();
        Classes selectedClass = classesList.getSelectionModel().getSelectedItem();
        if (selectedClass != null) {
            assignmentList.setItems(selectedClass.getAssignments());
            editAssignmentBtn.setDisable(true);
            deleteAssignmentBtn.setDisable(true);
        } else {
            editClassBtn.setDisable(true);
            deleteClassBtn.setDisable(true);
            addAssignmentBtn.setDisable(true);
        }
        
        classesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Classes>() {
            @Override
            public void changed(ObservableValue<? extends Classes> observableValue, Classes classes, Classes t1) {
                assignmentList.setItems(classesList.getSelectionModel().getSelectedItem().getAssignments());
                editClassBtn.setDisable(false);
                deleteClassBtn.setDisable(false);
                addAssignmentBtn.setDisable(false);
                editAssignmentBtn.setDisable(true);
                deleteAssignmentBtn.setDisable(true);
            }
        });
        assignmentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Assignment>() {
            @Override
            public void changed(ObservableValue<? extends Assignment> observableValue, Assignment assignment, Assignment t1) {
                if (assignmentList.getSelectionModel().getSelectedItem() != null) {
                    editAssignmentBtn.setDisable(false);
                    deleteAssignmentBtn.setDisable(false);
                }
            }
        });
    }
    
    private VBox createStackedTitledPanes() {
        final Image BLUE_FISH   = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Blue-Fish-icon.png");
        final Image RED_FISH    = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Red-Fish-icon.png");
        final Image YELLOW_FISH = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Yellow-Fish-icon.png");
        final Image GREEN_FISH  = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Green-Fish-icon.png");
        
        final VBox stackedTitledPanes = new VBox();
        stackedTitledPanes.getChildren().setAll(
                createTitledPane("One Fish",  GREEN_FISH),
                createTitledPane("Two Fish",  YELLOW_FISH, GREEN_FISH),
                createTitledPane("Red Fish",  RED_FISH),
                createTitledPane("Blue Fish", BLUE_FISH)
        );
        ((TitledPane) stackedTitledPanes.getChildren().get(0)).setExpanded(true);
//        stackedTitledPanes.getStyleClass().add("stacked-titled-panes");
        
        return stackedTitledPanes;
    }
    
    public TitledPane createTitledPane(String title, Image... images) {
        FlowPane content = new FlowPane();
        for (Image image: images) {
            ImageView imageView = new ImageView(image);
            content.getChildren().add(imageView);
            
            FlowPane.setMargin(imageView, new Insets(10));
        }
        content.setAlignment(Pos.TOP_CENTER);
        
        TitledPane pane = new TitledPane(title, content);
//        pane.getStyleClass().add("stacked-titled-pane");
        pane.setExpanded(false);
        
        return pane;
    }
    
    private ScrollPane makeScrollable(final VBox node) {
        final ScrollPane scroll = new ScrollPane();
//        scroll.setContent(node);
        scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                scroll.prefHeight(100);
            }
        });
        return scroll;
    }
    
    @FXML
    public void addClass() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainViewMaster.getScene().getWindow());
        dialog.setTitle("Add a New Course");
        dialog.setHeaderText("Fill in the information below and press OK to add a new class");
        FXMLLoader classLoader = new FXMLLoader();
        classLoader.setLocation(getClass().getResource("Dialogs\\ClassDialogTest.fxml"));
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
        if (selected == null) {
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainViewMaster.getScene().getWindow());
        dialog.setTitle("Edit an Existing Course");
        dialog.setHeaderText("Fill in the information below and press OK to edit the class");
        FXMLLoader classLoader = new FXMLLoader();
        classLoader.setLocation(getClass().getResource("Dialogs\\ClassDialogTest.fxml"));
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
        if (selected == null) {
            return;
        }
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
        if (selectedClass == null) {
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainViewMaster.getScene().getWindow());
        dialog.setTitle("Add an Assignment");
        dialog.setHeaderText("Fill in the information below and press OK to add a new assignment");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Dialogs\\AssignmentDialogTest.fxml"));
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
        if (selectedClass == null || selectedAssignment == null) {
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainViewMaster.getScene().getWindow());
        dialog.setTitle("Edit an Assignment");
        dialog.setHeaderText("Fill in the information below and press OK to edit the assignment");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Dialogs\\AssignmentDialogTest.fxml"));
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
        if (selectedClass == null || selectedAssignment == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Assignment");
        alert.setHeaderText("Are you sure you want to delete " + selectedAssignment.getName() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            selectedClass.deleteAssignment(selectedAssignment);
        }
    }
}
