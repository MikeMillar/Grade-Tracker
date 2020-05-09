package Main.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Datasource {
    
    private static ObservableList<Classes> classes;
    private static Datasource instance = new Datasource();
    
    private Datasource() {
        this.classes = FXCollections.observableArrayList();
    }
    
    public ObservableList<Classes> getClasses() {
        return classes;
    }
    
    public static Datasource getInstance() {
        return instance;
    }
    
    public void addClass(Classes classes1) {
        classes.add(classes1);
        System.out.println("added class");
        System.out.println(classes.size());
    }
    
    public void removeClass(Classes classes1) {
        classes.remove(classes1);
    }
    
}
