package Main;

import Main.Dialogs.ClassDialog;
import Main.Models.Assignment;
import Main.Models.Classes;
import Main.Models.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Controller {

    @FXML private VBox mainViewMaster;
    
    // Menu bar
    @FXML private MenuBar menuBar;
    
    // File Menu
    @FXML private Menu fileMenu;
    @FXML private MenuItem fmRefresh;
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
    @FXML private ListView<Classes> classesList;
    
    // Assignment Sub-Items
    @FXML private HBox assignmentTitleHbox;
    @FXML private Label assignmentLabel;
    @FXML private Button addAssignmentBtn;
    @FXML private TableView<Assignment> assignmentList;
    
    // Detail Sub-Items
    @FXML private HBox detailsTitleHbox;
    @FXML private Label detailsLabel;
    @FXML private ListView detailList; // Add type to view
    
    // Lists
    public ArrayList<Classes> classesArrayList;
    public Task<ObservableList<Assignment>> assignmentTask;
    
    public void initialize() {
        classesAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.25));
        classesAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.15));
        detailsAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.25));
        detailsAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.15));
        assignmentsAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.7));
        assignmentsAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.5));
        classesList.prefHeightProperty().bind(classesAnchorPane.heightProperty().subtract(40));
        classesList.prefWidthProperty().bind(classesAnchorPane.widthProperty().add(15));
        assignmentList.prefWidthProperty().bind(assignmentsAnchorPane.widthProperty());
        
        addClassBtn.hoverProperty().addListener(e -> {
            if (addClassBtn.isHover()) {
                addClassBtn.setStyle("-fx-font-weight:bold;");
            } else {
                addClassBtn.setStyle("-fx-background-color:transparent;");
            }
        });
        addAssignmentBtn.hoverProperty().addListener(e -> {
            if (addAssignmentBtn.isHover()) {
                addAssignmentBtn.setStyle("-fx-font-weight:bold;");
            } else {
                addAssignmentBtn.setStyle("-fx-background-color:transparent;");
            }
        });
        
        Datasource.getInstance().addClass(
                new Classes("TEST153","TestCourse", "Test Prof", "HW:15")
        );
        
        classesList.setItems(Datasource.getInstance().getClasses());
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
    public void refresh() {
        classesList.refresh();
        assignmentList.refresh();
    }
}
