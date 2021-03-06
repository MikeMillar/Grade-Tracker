package Main.Dialogs;

import Main.Models.Classes;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClassDialog {
    
    // Add Class Dialog
    @FXML private TextField courseNumField;
    @FXML private TextField courseNameField;
    @FXML private TextField professorNameField;
//    @FXML private TextField assignmentTypeField;
    @FXML private TextArea assignmentTypeField;
    
   public Classes createClass() {
       String num = courseNumField.getText();
       String course = courseNameField.getText();
       String prof = professorNameField.getText();
       String types = assignmentTypeField.getText();
       return new Classes(num, course, prof, types);
   }
   
   public void loadClass(Classes selected) {
       if (selected == null) {
           return;
       }
       courseNumField.setText(selected.getCourseNumber());
       courseNameField.setText(selected.getName());
       professorNameField.setText(selected.getProfessor());
       assignmentTypeField.setText(selected.getTypeString());
   }
   
   public void updateClass(Classes selected) {
       if (selected == null) {
           return;
       }
       selected.setCourseNumber(courseNumField.getText());
       selected.setName(courseNameField.getText());
       selected.setProfessor(professorNameField.getText());
       selected.createAssignmentTypes(assignmentTypeField.getText());
   }
   
   
}
