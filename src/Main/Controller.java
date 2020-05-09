package Main;

import Main.Models.Assignment;
import Main.Models.Classes;
import Main.Models.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Controller {

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
    
        
        Datasource.getInstance().addClass(new Classes("Math", "Mr. White"));
        Datasource.getInstance().addClass(new Classes("Science", "Mrs. Fisher"));
        Datasource.getInstance().addClass(new Classes("Intro to Programming", "Tim Buchalka"));
        
        Task<ObservableList<Classes>> task = new GetClassListTask();
        classesList.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }
    
    @FXML
    public void addClass() {
        Classes classes = new Classes("Testing 101", "DHARM");
        Datasource.getInstance().addClass(classes);
        classesList.refresh();
    }
}


class GetClassListTask extends Task {
    
    @Override
    protected ObservableList<Classes> call() throws Exception {
        return FXCollections.observableArrayList(
                Datasource.getInstance().getClasses());
    }
}
