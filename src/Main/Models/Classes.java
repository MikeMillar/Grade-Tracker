package Main.Models;

import java.util.ArrayList;

public class Classes {
    
    private String name;
    private String professor;
    private String courseNumber;
    private ArrayList<AssignmentType> assignmentTypes;
    private final ArrayList<Assignment> assignments;
    private int pointsEarned;
    private int maxPoints;
    private double percent;
    private char letterGrade;
    
    public Classes(String courseNumber, String name, String professor, String types) {
        this.courseNumber = courseNumber;
        setName(name);
        setProfessor(professor);
        createAssignmentTypes(types);
        this.assignments = new ArrayList<>();
    }
    
    private void createAssignmentTypes(String text) {
        String[] typeSplit = text.split(",");
        ArrayList<AssignmentType> types = new ArrayList<>();
        for (String s: typeSplit) {
            String[] values = s.split(":");
            AssignmentType type = new AssignmentType();
            type.setName(values[0]);
            try {
                type.setWeight(Double.parseDouble(values[1]));
                types.add(type);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Number Format Passed for assignment weight");
                e.printStackTrace();
            }
        }
        setAssignmentTypes(types);
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
    
    public String getCourseNumber() {
        return courseNumber;
    }
    
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
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
    
    @Override
    public String toString() {
        return this.name + " - " + this.professor;
    }
}
