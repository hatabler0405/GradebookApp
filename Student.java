import java.util.ArrayList;
import java.util.Collections;

/**
 * Student class representing a student with grades
 * Supports multiple subjects and grade management
 */
public class Student {
    private String name;
    private int id;
    private ArrayList<Double> grades;
    private ArrayList<String> subjects;
    private ArrayList<ArrayList<Double>> subjectGrades;
    
    /**
     * Constructor for Student with name and ID
     * @param name Student's name
     * @param id Student's unique ID
     */
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.grades = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.subjectGrades = new ArrayList<>();
    }
    
    /**
     * Add a grade to the student's overall grades
     * @param grade The grade to add (0-100)
     */
    public void addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        } else {
            System.out.println("Invalid grade. Please enter a grade between 0 and 100.");
        }
    }
    
    /**
     * Add a grade for a specific subject
     * @param subject The subject name
     * @param grade The grade to add (0-100)
     */
    public void addGradeForSubject(String subject, double grade) {
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade. Please enter a grade between 0 and 100.");
            return;
        }
        
        int subjectIndex = subjects.indexOf(subject);
        if (subjectIndex == -1) {
            // New subject
            subjects.add(subject);
            ArrayList<Double> newSubjectGrades = new ArrayList<>();
            newSubjectGrades.add(grade);
            subjectGrades.add(newSubjectGrades);
        } else {
            // Existing subject
            subjectGrades.get(subjectIndex).add(grade);
        }
        
        // Also add to overall grades
        addGrade(grade);
    }
    
    /**
     * Calculate the average of all grades
     * @return The average grade
     */
    public double getAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
    
    /**
     * Calculate the average for a specific subject
     * @param subject The subject name
     * @return The average grade for the subject
     */
    public double getAverageForSubject(String subject) {
        int subjectIndex = subjects.indexOf(subject);
        if (subjectIndex == -1) {
            return 0.0;
        }
        
        ArrayList<Double> subjectGradeList = subjectGrades.get(subjectIndex);
        if (subjectGradeList.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (double grade : subjectGradeList) {
            sum += grade;
        }
        return sum / subjectGradeList.size();
    }
    
    /**
     * Get letter grade based on average
     * @return Letter grade (A, B, C, D, F)
     */
    public String getLetterGrade() {
        double average = getAverage();
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }
    
    /**
     * Get letter grade for a specific subject
     * @param subject The subject name
     * @return Letter grade for the subject
     */
    public String getLetterGradeForSubject(String subject) {
        double average = getAverageForSubject(subject);
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }
    
    /**
     * Get all grades as a formatted string
     * @return String representation of all grades
     */
    public String getGradesString() {
        if (grades.isEmpty()) {
            return "No grades";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grades.size(); i++) {
            sb.append(String.format("%.1f", grades.get(i)));
            if (i < grades.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    /**
     * Get grades for a specific subject
     * @param subject The subject name
     * @return String representation of grades for the subject
     */
    public String getGradesStringForSubject(String subject) {
        int subjectIndex = subjects.indexOf(subject);
        if (subjectIndex == -1) {
            return "No grades for this subject";
        }
        
        ArrayList<Double> subjectGradeList = subjectGrades.get(subjectIndex);
        if (subjectGradeList.isEmpty()) {
            return "No grades";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < subjectGradeList.size(); i++) {
            sb.append(String.format("%.1f", subjectGradeList.get(i)));
            if (i < grades.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    /**
     * Get all subjects for this student
     * @return List of subject names
     */
    public ArrayList<String> getSubjects() {
        return new ArrayList<>(subjects);
    }
    
    /**
     * Get the number of grades
     * @return Number of grades
     */
    public int getGradeCount() {
        return grades.size();
    }
    
    /**
     * Get the number of grades for a specific subject
     * @param subject The subject name
     * @return Number of grades for the subject
     */
    public int getGradeCountForSubject(String subject) {
        int subjectIndex = subjects.indexOf(subject);
        if (subjectIndex == -1) {
            return 0;
        }
        return subjectGrades.get(subjectIndex).size();
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }
    
    public ArrayList<Double> getGrades() {
        return new ArrayList<>(grades);
    }
    
    /**
     * String representation of the student
     * @return Formatted string with student information
     */
    @Override
    public String toString() {
        return String.format("Student: %s (ID: %d) - Average: %.2f (%s) - Grades: [%s]",
                name, id, getAverage(), getLetterGrade(), getGradesString());
    }
    
    /**
     * Detailed string representation including subjects
     * @return Formatted string with detailed student information
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Student: %s (ID: %d)\n", name, id));
        sb.append(String.format("Overall Average: %.2f (%s)\n", getAverage(), getLetterGrade()));
        sb.append(String.format("Overall Grades: [%s]\n", getGradesString()));
        
        if (!subjects.isEmpty()) {
            sb.append("Subject Breakdown:\n");
            for (String subject : subjects) {
                sb.append(String.format("  %s: %.2f (%s) - [%s]\n", 
                    subject, 
                    getAverageForSubject(subject), 
                    getLetterGradeForSubject(subject),
                    getGradesStringForSubject(subject)));
            }
        }
        
        return sb.toString();
    }
}
