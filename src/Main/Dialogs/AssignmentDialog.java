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
        if (selectedClass == null) {
            return;
        }
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
    
    public void loadAssignment(Assignment selected) {
        if (selected == null) {
            return;
        }
        assignmentNameField.setText(selected.getName());
        assignmentTypeComboBox.getSelectionModel().select(selected.getType());
        earnedPointsField.setText("" + selected.getPointsEarned());
        maxPointsField.setText("" + selected.getMaxPoints());
        descriptionArea.setText(selected.getDescription());
    }
    
    public void updateAssignment(Assignment selected) {
        if (selected == null) {
            return;
        }
        selected.setName(assignmentNameField.getText());
        selected.setType(assignmentTypeComboBox.getSelectionModel().getSelectedItem());
        selected.setPointsEarned(Integer.parseInt(earnedPointsField.getText()));
        selected.setMaxPoints(Integer.parseInt(maxPointsField.getText()));
        selected.setDescription(descriptionArea.getText());
    }

}
