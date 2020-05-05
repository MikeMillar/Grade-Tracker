package Main.Models;

import java.util.ArrayList;

public class Classes {
    
    private String name;
    private String professor;
    private ArrayList<AssignmentType> assignmentTypes;
    private final ArrayList<Assignment> assignments;
    private int pointsEarned;
    private int maxPoints;
    private double percent;
    private char letterGrade;
    
    public Classes(String name, String professor, ArrayList<AssignmentType> assignmentTypes) {
        setName(name);
        setProfessor(professor);
        setAssignmentTypes(assignmentTypes);
        this.assignments = new ArrayList<Assignment>();
    }
    
    private void setAssignmentTypes(ArrayList<AssignmentType> types) {
        if (types != null && types.size() > 0) {
            this.assignmentTypes = types;
        } else {
            System.out.println("Invalid assignment types. Enter valid types.");
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getProfessor() {
        return professor;
    }
    
    public ArrayList<AssignmentType> getAssignmentTypes() {
        return assignmentTypes;
    }
    
    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }
    
    public int getPointsEarned() {
        return pointsEarned;
    }
    
    public int getMaxPoints() {
        return maxPoints;
    }
    
    public char getLetterGrade() {
        return letterGrade;
    }
    
    public double getPercent() {
        return percent;
    }
    
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name. Enter valid name.");
        }
    }
    
    public void setProfessor(String professor) {
        if (professor != null && !professor.isEmpty()) {
            this.professor = professor;
        } else {
            System.out.println("Invalid professor name. Enter valid professor name.");
        }
    }
    
    private void calculateGrade() {
        this.percent = (double) this.pointsEarned / this.maxPoints;
        if (percent >= 90) {
            letterGrade = 'A';
        } else if (percent >= 80) {
            letterGrade = 'B';
        } else if (percent >= 70) {
            letterGrade = 'C';
        } else if (percent >= 60) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }
    }
    
    public void addAssignment(Assignment assignment) {
        this.maxPoints += assignment.getMaxPoints();
        this.pointsEarned += assignment.getPointsEarned();
        calculateGrade();
        this.assignments.add(assignment);
    }
    
    public void deleteAssignment(Assignment assignment) {
        this.maxPoints -= assignment.getMaxPoints();
        this.pointsEarned -= assignment.getPointsEarned();
        calculateGrade();
        this.assignments.remove(assignment);
    }
    
    public void modifyAssignment(Assignment assignment) {
        // TODO - write method
    }
    
    public void addAssignmentType(AssignmentType type) {
        this.assignmentTypes.add(type);
        double totalWeight = 0;
        for (AssignmentType at: assignmentTypes) {
            totalWeight += at.getWeight();
        }
        if (totalWeight != 100) {
            System.out.println("Total weight does not equal 100. Current weight total: " + totalWeight + "." +
                    "Please review total weights. If total weight is not 100, grade calculation may be wrong.");
        }
    }
    
    public void deleteAssignmentType(AssignmentType type) {
        this.assignmentTypes.remove(type);
        double totalWeight = 0;
        for (AssignmentType at: assignmentTypes) {
            totalWeight += at.getWeight();
        }
        if (totalWeight != 100) {
            System.out.println("Total weight does not equal 100. Current weight total: " + totalWeight + "." +
                    "Please review total weights. If total weight is not 100, grade calculation may be wrong.");
        }
    }
    
    public void modifyAssignmentType(AssignmentType type) {
        // TODO - write method
    }
}
