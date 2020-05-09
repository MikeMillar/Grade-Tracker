package Main.Dialogs;

import Main.Models.Classes;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClassDialog {
    
    // Add Class Dialog
    @FXML private TextField courseNumField;
    @FXML private TextField courseNameField;
    @FXML private TextField professorNameField;
    @FXML private TextField assignmentTypeField;
    
   public Classes createClass() {
       String num = courseNumField.getText();
       String course = courseNameField.getText();
       String prof = professorNameField.getText();
       String types = assignmentTypeField.getText();
       return new Classes(num, course, prof, types);
   }
   
   
}
