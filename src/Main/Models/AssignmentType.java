package Main.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AssignmentType {
    
    private StringProperty name = new SimpleStringProperty();
    private double weight;
    
    
    public AssignmentType() {  }
    
    public AssignmentType(String name, double weight) {
        setName(name);
        setWeight(weight);
    }
    
    public StringProperty getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name.setValue(name);
        } else {
            System.out.println("Invalid name, enter valid name");
        }
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        if (weight > 0 && weight <= 100) {
            this.weight = weight;
        } else {
            System.out.println("Invalid weight, enter a valid weight.");
        }
    }
}
