package Main.Models;

public class Assignment {
    
    private String name;
    private AssignmentType type;
    private String description;
    private int maxPoints;
    private int pointsEarned;
    private double percent;
    private char grade;
    
    public Assignment(String name, AssignmentType type, String description, int maxPoints, int pointsEarned) {
        setName(name);
        setType(type);
        setDescription(description);
        setMaxPoints(maxPoints);
        setPointsEarned(pointsEarned);
        calculateGrade();
    }
    
    public void calculateGrade() {
        percent = (double) this.pointsEarned / this.maxPoints;
        if (percent >= 90) {
            grade = 'A';
        } else if (percent >= 80) {
            grade = 'B';
        } else if (percent >= 70) {
            grade = 'C';
        } else if (percent >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name. Please enter another.");
        }
    }
    
    public AssignmentType getType() {
        return type;
    }
    
    public void setType(AssignmentType type) {
        this.type = type;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        } else {
            this.description = "";
        }
    }
    
    public int getMaxPoints() {
        return maxPoints;
    }
    
    public void setMaxPoints(int maxPoints) {
        if (maxPoints > 0) {
            this.maxPoints = maxPoints;
        } else {
            System.out.println("Invalid max points. Enter valid number");
        }
    }
    
    public int getPointsEarned() {
        return pointsEarned;
    }
    
    public void setPointsEarned(int pointsEarned) {
        if (pointsEarned >= 0) {
            this.pointsEarned = pointsEarned;
        } else {
            System.out.println("Invalid points earned. Enter valid number");
        }
    }
    
    public char getGrade() {
        return grade;
    }
    
    public double getPercent() {
        return percent;
    }
}
