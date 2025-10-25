import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Enhanced Gradebook with weighted categories support
 * Allows different weightings for different types of assignments
 */
public class WeightedGradebook extends Gradebook {
    private Map<String, Double> categoryWeights;
    private Map<Integer, Map<String, ArrayList<Double>>> studentCategoryGrades;
    
    /**
     * Constructor for WeightedGradebook
     * @param dataFile The filename to save/load data from
     */
    public WeightedGradebook(String dataFile) {
        super(dataFile);
        this.categoryWeights = new HashMap<>();
        this.studentCategoryGrades = new HashMap<>();
        
        // Set default category weights
        setDefaultCategoryWeights();
    }
    
    /**
     * Set default category weights
     */
    private void setDefaultCategoryWeights() {
        categoryWeights.put("Homework", 0.30);
        categoryWeights.put("Tests", 0.50);
        categoryWeights.put("Projects", 0.20);
    }
    
    /**
     * Add a grade for a specific student and category
     * @param id Student's ID
     * @param category The category name (e.g., "Homework", "Tests")
     * @param grade The grade to add (0-100)
     * @return true if grade was added successfully, false if student not found
     */
    public boolean addGrade(int id, String category, double grade) {
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found!");
            return false;
        }
        
        // Initialize student category grades if not exists
        if (!studentCategoryGrades.containsKey(id)) {
            studentCategoryGrades.put(id, new HashMap<>());
        }
        
        // Initialize category if not exists
        if (!studentCategoryGrades.get(id).containsKey(category)) {
            studentCategoryGrades.get(id).put(category, new ArrayList<>());
        }
        
        // Add grade to category
        studentCategoryGrades.get(id).get(category).add(grade);
        
        // Also add to overall grades
        student.addGrade(grade);
        
        System.out.println("Grade " + grade + " added for " + student.getName() + " in " + category);
        return true;
    }
    
    /**
     * Calculate weighted average for a student
     * @param id Student's ID
     * @return Weighted average grade
     */
    public double getWeightedAverage(int id) {
        Student student = findStudentById(id);
        if (student == null) {
            return 0.0;
        }
        
        if (!studentCategoryGrades.containsKey(id)) {
            return student.getAverage(); // Fall back to regular average
        }
        
        double weightedSum = 0.0;
        double totalWeight = 0.0;
        
        Map<String, ArrayList<Double>> studentGrades = studentCategoryGrades.get(id);
        
        for (Map.Entry<String, ArrayList<Double>> entry : studentGrades.entrySet()) {
            String category = entry.getKey();
            ArrayList<Double> grades = entry.getValue();
            
            if (!grades.isEmpty() && categoryWeights.containsKey(category)) {
                double categoryAverage = grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                double weight = categoryWeights.get(category);
                
                weightedSum += categoryAverage * weight;
                totalWeight += weight;
            }
        }
        
        return totalWeight > 0 ? weightedSum / totalWeight : student.getAverage();
    }
    
    /**
     * Get weighted letter grade for a student
     * @param id Student's ID
     * @return Weighted letter grade
     */
    public String getWeightedLetterGrade(int id) {
        double weightedAverage = getWeightedAverage(id);
        if (weightedAverage >= 90) return "A";
        else if (weightedAverage >= 80) return "B";
        else if (weightedAverage >= 70) return "C";
        else if (weightedAverage >= 60) return "D";
        else return "F";
    }
    
    /**
     * Set category weights
     * @param category The category name
     * @param weight The weight (0.0 to 1.0)
     */
    public void setCategoryWeight(String category, double weight) {
        if (weight >= 0.0 && weight <= 1.0) {
            categoryWeights.put(category, weight);
            System.out.println("Category weight for " + category + " set to " + (weight * 100) + "%");
        } else {
            System.out.println("Weight must be between 0.0 and 1.0");
        }
    }
    
    /**
     * Display category weights
     */
    public void displayCategoryWeights() {
        System.out.println("\n=== CATEGORY WEIGHTS ===");
        for (Map.Entry<String, Double> entry : categoryWeights.entrySet()) {
            System.out.printf("%s: %.1f%%\n", entry.getKey(), entry.getValue() * 100);
        }
        System.out.println("=======================\n");
    }
    
    /**
     * Display weighted student rankings
     */
    public void rankStudentsByWeightedAverage() {
        ArrayList<Student> allStudents = getAllStudents();
        if (allStudents.isEmpty()) {
            System.out.println("No students to rank.");
            return;
        }
        
        // Sort by weighted average
        allStudents.sort((s1, s2) -> Double.compare(getWeightedAverage(s2.getId()), getWeightedAverage(s1.getId())));
        
        System.out.println("\n=== STUDENT RANKINGS (by weighted average) ===");
        for (int i = 0; i < allStudents.size(); i++) {
            Student student = allStudents.get(i);
            double weightedAvg = getWeightedAverage(student.getId());
            System.out.println(String.format("%d. %s (ID: %d) - Weighted Average: %.2f (%s)",
                    i + 1, student.getName(), student.getId(), 
                    weightedAvg, getWeightedLetterGrade(student.getId())));
        }
        System.out.println("=============================================\n");
    }
    
    /**
     * Display detailed student information with weighted grades
     */
    public void displayWeightedStudentInfo(int id) {
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found!");
            return;
        }
        
        System.out.println("\n=== WEIGHTED STUDENT INFORMATION ===");
        System.out.printf("Student: %s (ID: %d)\n", student.getName(), student.getId());
        System.out.printf("Regular Average: %.2f (%s)\n", student.getAverage(), student.getLetterGrade());
        System.out.printf("Weighted Average: %.2f (%s)\n", getWeightedAverage(id), getWeightedLetterGrade(id));
        
        if (studentCategoryGrades.containsKey(id)) {
            System.out.println("\nCategory Breakdown:");
            Map<String, ArrayList<Double>> studentGrades = studentCategoryGrades.get(id);
            for (Map.Entry<String, ArrayList<Double>> entry : studentGrades.entrySet()) {
                String category = entry.getKey();
                ArrayList<Double> grades = entry.getValue();
                double categoryAvg = grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                double weight = categoryWeights.getOrDefault(category, 0.0);
                
                System.out.printf("  %s (%.1f%%): %.2f - [%s]\n", 
                    category, weight * 100, categoryAvg, 
                    grades.toString().replace("[", "").replace("]", ""));
            }
        }
        System.out.println("=====================================\n");
    }
    
    /**
     * Get all categories for a student
     * @param id Student's ID
     * @return List of categories
     */
    public ArrayList<String> getStudentCategories(int id) {
        if (studentCategoryGrades.containsKey(id)) {
            return new ArrayList<>(studentCategoryGrades.get(id).keySet());
        }
        return new ArrayList<>();
    }
    
    /**
     * Get grades for a specific category and student
     * @param id Student's ID
     * @param category The category name
     * @return List of grades for the category
     */
    public ArrayList<Double> getCategoryGrades(int id, String category) {
        if (studentCategoryGrades.containsKey(id) && 
            studentCategoryGrades.get(id).containsKey(category)) {
            return new ArrayList<>(studentCategoryGrades.get(id).get(category));
        }
        return new ArrayList<>();
    }
    
    /**
     * Calculate average for a specific category and student
     * @param id Student's ID
     * @param category The category name
     * @return Average grade for the category
     */
    public double getCategoryAverage(int id, String category) {
        ArrayList<Double> grades = getCategoryGrades(id, category);
        if (grades.isEmpty()) {
            return 0.0;
        }
        return grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
