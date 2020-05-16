package Main.Dialogs;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Arrays;

public class StackedPanes extends VBox {

    private Pane parent;
    private ArrayList<TitledPane> paneList;
    
    public StackedPanes(Pane parent, TitledPane... panes) {
        this.parent = parent;
        this.prefHeightProperty().bind(parent.heightProperty());
        paneList = new ArrayList<>(Arrays.asList(panes));
        getChildren().setAll(paneList);
        paneList.forEach(e -> e.setExpanded(false));
        paneList.forEach(e -> e.prefWidthProperty().bind(parent.widthProperty()));
        ((TitledPane) getChildren().get(0)).setExpanded(true);
    }
    
    public ArrayList<TitledPane> getPaneList() {
        return paneList;
    }
    
    public TitledPane getCoursePane() {
        return paneList.get(0);
    }
    
    public TitledPane getAssignmentPane() {
        return paneList.get(1);
    }
    
    public TitledPane getUpcomingPane() {
        return paneList.get(2);
    }
}
