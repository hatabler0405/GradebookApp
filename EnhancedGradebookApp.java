import java.util.Scanner;

/**
 * Enhanced Gradebook Application with weighted categories
 * Provides advanced features for managing student grades
 */
public class EnhancedGradebookApp {
    private static final String DATA_FILE = "enhanced_gradebook_data.txt";
    private WeightedGradebook gradebook;
    private Scanner scanner;
    
    public EnhancedGradebookApp() {
        this.gradebook = new WeightedGradebook(DATA_FILE);
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method to run the enhanced application
     */
    public static void main(String[] args) {
        EnhancedGradebookApp app = new EnhancedGradebookApp();
        app.run();
    }
    
    /**
     * Main application loop
     */
    public void run() {
        System.out.println("=== ENHANCED STUDENT GRADEBOOK APP ===");
        System.out.println("Advanced grade management with weighted categories!");
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGrade();
                    break;
                case 3:
                    addGradeWithCategory();
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    viewDetailedStudents();
                    break;
                case 6:
                    viewWeightedStudentInfo();
                    break;
                case 7:
                    rankStudents();
                    break;
                case 8:
                    rankStudentsByWeighted();
                    break;
                case 9:
                    viewGradeDistribution();
                    break;
                case 10:
                    viewClassStatistics();
                    break;
                case 11:
                    searchStudent();
                    break;
                case 12:
                    manageCategoryWeights();
                    break;
                case 13:
                    viewCategoryWeights();
                    break;
                case 14:
                    generateGradeReport();
                    break;
                case 15:
                    exportReport();
                    break;
                case 16:
                    saveAndExit();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }
    
    /**
     * Display the enhanced main menu
     */
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    ENHANCED MAIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("1.  Add Student");
        System.out.println("2.  Add Grade (Overall)");
        System.out.println("3.  Add Grade (by Category)");
        System.out.println("4.  View All Students");
        System.out.println("5.  View Detailed Student Info");
        System.out.println("6.  View Weighted Student Info");
        System.out.println("7.  Rank Students (Regular)");
        System.out.println("8.  Rank Students (Weighted)");
        System.out.println("9.  View Grade Distribution");
        System.out.println("10. View Class Statistics");
        System.out.println("11. Search Student");
        System.out.println("12. Manage Category Weights");
        System.out.println("13. View Category Weights");
        System.out.println("14. Generate Grade Report");
        System.out.println("15. Export Report");
        System.out.println("16. Save and Exit");
        System.out.println("=".repeat(60));
    }
    
    /**
     * Get user's menu choice
     * @return The menu choice (1-16)
     */
    private int getMenuChoice() {
        System.out.print("Enter your choice (1-16): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice >= 1 && choice <= 16) {
                return choice;
            } else {
                System.out.println("Please enter a number between 1 and 16.");
                return getMenuChoice();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return getMenuChoice();
        }
    }
    
    /**
     * Add a new student to the gradebook
     */
    private void addStudent() {
        System.out.println("\n--- ADD STUDENT ---");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }
        
        System.out.print("Enter student ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            gradebook.addStudent(name, id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }
    
    /**
     * Add a grade for a student (overall)
     */
    private void addGrade() {
        System.out.println("\n--- ADD GRADE (OVERALL) ---");
        System.out.print("Enter student ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter grade (0-100): ");
            double grade = Double.parseDouble(scanner.nextLine().trim());
            gradebook.addGrade(id, grade);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        }
    }
    
    /**
     * Add a grade for a student (by category)
     */
    private void addGradeWithCategory() {
        System.out.println("\n--- ADD GRADE (BY CATEGORY) ---");
        System.out.print("Enter student ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter category (e.g., Homework, Tests, Projects): ");
            String category = scanner.nextLine().trim();
            
            if (category.isEmpty()) {
                System.out.println("Category name cannot be empty!");
                return;
            }
            
            System.out.print("Enter grade (0-100): ");
            double grade = Double.parseDouble(scanner.nextLine().trim());
            gradebook.addGrade(id, category, grade);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        }
    }
    
    /**
     * View all students
     */
    private void viewAllStudents() {
        System.out.println("\n--- ALL STUDENTS ---");
        gradebook.displayAll();
    }
    
    /**
     * View detailed student information
     */
    private void viewDetailedStudents() {
        System.out.println("\n--- DETAILED STUDENT INFORMATION ---");
        gradebook.displayAllDetailed();
    }
    
    /**
     * View weighted student information
     */
    private void viewWeightedStudentInfo() {
        System.out.println("\n--- WEIGHTED STUDENT INFORMATION ---");
        System.out.print("Enter student ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            gradebook.displayWeightedStudentInfo(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }
    
    /**
     * Rank students by performance (regular)
     */
    private void rankStudents() {
        System.out.println("\n--- STUDENT RANKINGS (REGULAR) ---");
        gradebook.rankStudents();
    }
    
    /**
     * Rank students by weighted performance
     */
    private void rankStudentsByWeighted() {
        System.out.println("\n--- STUDENT RANKINGS (WEIGHTED) ---");
        gradebook.rankStudentsByWeightedAverage();
    }
    
    /**
     * View grade distribution
     */
    private void viewGradeDistribution() {
        System.out.println("\n--- GRADE DISTRIBUTION ---");
        gradebook.displayGradeDistribution();
    }
    
    /**
     * View class statistics
     */
    private void viewClassStatistics() {
        System.out.println("\n--- CLASS STATISTICS ---");
        gradebook.displayClassStatistics();
    }
    
    /**
     * Search for a specific student
     */
    private void searchStudent() {
        System.out.println("\n--- SEARCH STUDENT ---");
        System.out.print("Enter student ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            gradebook.displayWeightedStudentInfo(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }
    
    /**
     * Manage category weights
     */
    private void manageCategoryWeights() {
        System.out.println("\n--- MANAGE CATEGORY WEIGHTS ---");
        System.out.println("Current category weights:");
        gradebook.displayCategoryWeights();
        
        System.out.print("Enter category name: ");
        String category = scanner.nextLine().trim();
        
        if (category.isEmpty()) {
            System.out.println("Category name cannot be empty!");
            return;
        }
        
        System.out.print("Enter weight (0.0 to 1.0): ");
        try {
            double weight = Double.parseDouble(scanner.nextLine().trim());
            gradebook.setCategoryWeight(category, weight);
        } catch (NumberFormatException e) {
            System.out.println("Invalid weight. Please enter a number between 0.0 and 1.0.");
        }
    }
    
    /**
     * View category weights
     */
    private void viewCategoryWeights() {
        System.out.println("\n--- CATEGORY WEIGHTS ---");
        gradebook.displayCategoryWeights();
    }
    
    /**
     * Generate a comprehensive grade report
     */
    private void generateGradeReport() {
        System.out.println("\n--- GENERATE GRADE REPORT ---");
        
        if (gradebook.getStudentCount() == 0) {
            System.out.println("No students to generate report for.");
            return;
        }
        
        System.out.println("=== COMPREHENSIVE GRADE REPORT ===");
        System.out.println("Generated on: " + java.time.LocalDateTime.now());
        System.out.println();
        
        // Class statistics
        gradebook.displayClassStatistics();
        
        // Category weights
        gradebook.displayCategoryWeights();
        
        // Student rankings (both regular and weighted)
        System.out.println("=== REGULAR RANKINGS ===");
        gradebook.rankStudents();
        
        System.out.println("=== WEIGHTED RANKINGS ===");
        gradebook.rankStudentsByWeightedAverage();
        
        // Grade distribution
        gradebook.displayGradeDistribution();
        
        // Individual student details
        System.out.println("=== INDIVIDUAL STUDENT DETAILS ===");
        for (int i = 0; i < gradebook.getStudentCount(); i++) {
            gradebook.displayWeightedStudentInfo(gradebook.getAllStudents().get(i).getId());
        }
    }
    
    /**
     * Export a detailed report
     */
    private void exportReport() {
        System.out.println("\n--- EXPORT REPORT ---");
        System.out.print("Enter filename for report (e.g., enhanced_report.txt): ");
        String filename = scanner.nextLine().trim();
        
        if (filename.isEmpty()) {
            filename = "enhanced_gradebook_report_" + System.currentTimeMillis() + ".txt";
        }
        
        gradebook.exportReport(filename);
    }
    
    /**
     * Save data and exit the application
     */
    private void saveAndExit() {
        System.out.println("\n--- SAVING AND EXITING ---");
        if (gradebook.saveToFile(DATA_FILE)) {
            System.out.println("Data saved successfully. Goodbye!");
        } else {
            System.out.println("There was an error saving data. Please check your file permissions.");
        }
    }
    
    /**
     * Clean up resources
     */
    public void cleanup() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
