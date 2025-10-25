import java.util.Scanner;

/**
 * Main Gradebook Application
 * Provides a console interface for managing student grades
 */
public class GradebookApp {
    private static final String DATA_FILE = "gradebook_data.txt";
    private Gradebook gradebook;
    private Scanner scanner;
    
    public GradebookApp() {
        this.gradebook = new Gradebook(DATA_FILE);
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method to run the application
     */
    public static void main(String[] args) {
        GradebookApp app = new GradebookApp();
        app.run();
    }
    
    /**
     * Main application loop
     */
    public void run() {
        System.out.println("=== WELCOME TO THE STUDENT GRADEBOOK APP ===");
        System.out.println("Manage student grades with ease!");
        
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
                    addGradeWithSubject();
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    viewDetailedStudents();
                    break;
                case 6:
                    rankStudents();
                    break;
                case 7:
                    viewGradeDistribution();
                    break;
                case 8:
                    viewClassStatistics();
                    break;
                case 9:
                    searchStudent();
                    break;
                case 10:
                    exportReport();
                    break;
                case 11:
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
     * Display the main menu
     */
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                    MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1.  Add Student");
        System.out.println("2.  Add Grade (Overall)");
        System.out.println("3.  Add Grade (by Subject)");
        System.out.println("4.  View All Students");
        System.out.println("5.  View Detailed Student Info");
        System.out.println("6.  Rank Students by Performance");
        System.out.println("7.  View Grade Distribution");
        System.out.println("8.  View Class Statistics");
        System.out.println("9.  Search Student");
        System.out.println("10. Export Report");
        System.out.println("11. Save and Exit");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Get user's menu choice
     * @return The menu choice (1-11)
     */
    private int getMenuChoice() {
        System.out.print("Enter your choice (1-11): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice >= 1 && choice <= 11) {
                return choice;
            } else {
                System.out.println("Please enter a number between 1 and 11.");
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
     * Add a grade for a student (by subject)
     */
    private void addGradeWithSubject() {
        System.out.println("\n--- ADD GRADE (BY SUBJECT) ---");
        System.out.print("Enter student ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter subject name: ");
            String subject = scanner.nextLine().trim();
            
            if (subject.isEmpty()) {
                System.out.println("Subject name cannot be empty!");
                return;
            }
            
            System.out.print("Enter grade (0-100): ");
            double grade = Double.parseDouble(scanner.nextLine().trim());
            gradebook.addGrade(id, subject, grade);
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
     * Rank students by performance
     */
    private void rankStudents() {
        System.out.println("\n--- STUDENT RANKINGS ---");
        gradebook.rankStudents();
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
            Student student = gradebook.findStudentById(id);
            
            if (student != null) {
                System.out.println("\nStudent Found:");
                System.out.println(student.toDetailedString());
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }
    
    /**
     * Export a detailed report
     */
    private void exportReport() {
        System.out.println("\n--- EXPORT REPORT ---");
        System.out.print("Enter filename for report (e.g., report.txt): ");
        String filename = scanner.nextLine().trim();
        
        if (filename.isEmpty()) {
            filename = "gradebook_report_" + System.currentTimeMillis() + ".txt";
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
