package Main;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    @FXML private ScrollPane classScrollPane;
    @FXML private ListView classesList; // Add type to view
    
    // Assignment Sub-Items
    @FXML private HBox assignmentTitleHbox;
    @FXML private Label assignmentLabel;
    @FXML private Button addAssignmentBtn;
    @FXML private ScrollPane assignmentScrollPane;
    @FXML private ListView assignmentList; // Add type to view
    
    // Detail Sub-Items
    @FXML private HBox detailsTitleHbox;
    @FXML private Label detailsLabel;
    @FXML private ScrollPane detailScrollPane;
    @FXML private ListView detailList; // Add type to view
    
    
    public void initialize() {
        classesAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.25));
        classesAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.15));
        detailsAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.25));
        detailsAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.15));
        assignmentsAnchorPane.maxWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.7));
        assignmentsAnchorPane.minWidthProperty().bind(mainSplitPane.widthProperty().multiply(0.5));
        
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
    }
    
}
