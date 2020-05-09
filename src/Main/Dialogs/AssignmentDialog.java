package Main.Dialogs;

import Main.Models.Assignment;
import Main.Models.AssignmentType;
import Main.Models.Classes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.util.ArrayList;

public class AssignmentDialog  {

    @FXML private TextField assignmentNameField;
    @FXML private ComboBox<String> assignmentTypeComboBox;
    @FXML private TextField earnedPointsField;
    @FXML private TextField maxPointsField;
    @FXML private TextArea descriptionArea;
    
    public void setComboBox(Classes selectedClass) {
        ObservableList<String> types = FXCollections.observableArrayList();
        ObservableList<AssignmentType> classTypes = selectedClass.getAssignmentTypes();
        for (AssignmentType type : classTypes) {
            types.add(type.getName().getValue());
        }
        assignmentTypeComboBox.setItems(types);
    }
    
    public Assignment addAssignment() {
        String name = assignmentNameField.getText();
        String type = assignmentTypeComboBox.getSelectionModel().getSelectedItem();
        int earned = Integer.parseInt(earnedPointsField.getText());
        int max = Integer.parseInt(maxPointsField.getText());
        String description = descriptionArea.getText();
        return new Assignment(name, type, description, max, earned);
    }

}
