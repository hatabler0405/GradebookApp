import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;
import java.util.Scanner;

/**
 * Gradebook class to manage a collection of students
 * Provides functionality for adding students, grades, and managing data persistence
 */
public class Gradebook {
    private ArrayList<Student> students;
    private String dataFile;
    
    /**
     * Constructor for Gradebook
     * @param dataFile The filename to save/load data from
     */
    public Gradebook(String dataFile) {
        this.students = new ArrayList<>();
        this.dataFile = dataFile;
        loadFromFile(dataFile);
    }
    
    /**
     * Add a new student to the gradebook
     * @param name Student's name
     * @param id Student's unique ID
     * @return true if student was added successfully, false if ID already exists
     */
    public boolean addStudent(String name, int id) {
        // Check if student with this ID already exists
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Student with ID " + id + " already exists!");
                return false;
            }
        }
        
        Student newStudent = new Student(name, id);
        students.add(newStudent);
        System.out.println("Student " + name + " (ID: " + id + ") added successfully!");
        return true;
    }
    
    /**
     * Add a grade for a specific student
     * @param id Student's ID
     * @param grade The grade to add (0-100)
     * @return true if grade was added successfully, false if student not found
     */
    public boolean addGrade(int id, double grade) {
        Student student = findStudentById(id);
        if (student != null) {
            student.addGrade(grade);
            System.out.println("Grade " + grade + " added for " + student.getName());
            return true;
        } else {
            System.out.println("Student with ID " + id + " not found!");
            return false;
        }
    }
    
    /**
     * Add a grade for a specific student and subject
     * @param id Student's ID
     * @param subject The subject name
     * @param grade The grade to add (0-100)
     * @return true if grade was added successfully, false if student not found
     */
    public boolean addGrade(int id, String subject, double grade) {
        Student student = findStudentById(id);
        if (student != null) {
            student.addGradeForSubject(subject, grade);
            System.out.println("Grade " + grade + " added for " + student.getName() + " in " + subject);
            return true;
        } else {
            System.out.println("Student with ID " + id + " not found!");
            return false;
        }
    }
    
    /**
     * Find a student by their ID
     * @param id The student's ID
     * @return Student object if found, null otherwise
     */
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    /**
     * Display all students and their information
     */
    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students in the gradebook.");
            return;
        }
        
        System.out.println("\n=== ALL STUDENTS ===");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).toString());
        }
        System.out.println("===================\n");
    }
    
    /**
     * Display detailed information for all students
     */
    public void displayAllDetailed() {
        if (students.isEmpty()) {
            System.out.println("No students in the gradebook.");
            return;
        }
        
        System.out.println("\n=== DETAILED STUDENT INFORMATION ===");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).toDetailedString());
            System.out.println("---");
        }
        System.out.println("=====================================\n");
    }
    
    /**
     * Rank students by their average grade (highest to lowest)
     */
    public void rankStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to rank.");
            return;
        }
        
        // Create a copy of the students list and sort by average
        ArrayList<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getAverage(), s1.getAverage()); // Descending order
            }
        });
        
        System.out.println("\n=== STUDENT RANKINGS (by average grade) ===");
        for (int i = 0; i < sortedStudents.size(); i++) {
            Student student = sortedStudents.get(i);
            System.out.println(String.format("%d. %s (ID: %d) - Average: %.2f (%s)",
                    i + 1, student.getName(), student.getId(), 
                    student.getAverage(), student.getLetterGrade()));
        }
        System.out.println("==========================================\n");
    }
    
    /**
     * Display grade distribution chart
     */
    public void displayGradeDistribution() {
        if (students.isEmpty()) {
            System.out.println("No students to analyze.");
            return;
        }
        
        int[] gradeRanges = new int[5]; // A, B, C, D, F
        String[] gradeLabels = {"A (90-100)", "B (80-89)", "C (70-79)", "D (60-69)", "F (0-59)"};
        
        for (Student student : students) {
            String letterGrade = student.getLetterGrade();
            switch (letterGrade) {
                case "A": gradeRanges[0]++; break;
                case "B": gradeRanges[1]++; break;
                case "C": gradeRanges[2]++; break;
                case "D": gradeRanges[3]++; break;
                case "F": gradeRanges[4]++; break;
            }
        }
        
        System.out.println("\n=== GRADE DISTRIBUTION ===");
        for (int i = 0; i < gradeRanges.length; i++) {
            System.out.printf("%s: %d students\n", gradeLabels[i], gradeRanges[i]);
        }
        System.out.println("========================\n");
    }
    
    /**
     * Calculate and display class statistics
     */
    public void displayClassStatistics() {
        if (students.isEmpty()) {
            System.out.println("No students to analyze.");
            return;
        }
        
        double totalAverage = 0.0;
        double highestGrade = 0.0;
        double lowestGrade = 100.0;
        int totalGrades = 0;
        
        for (Student student : students) {
            double studentAverage = student.getAverage();
            totalAverage += studentAverage;
            
            if (studentAverage > highestGrade) {
                highestGrade = studentAverage;
            }
            if (studentAverage < lowestGrade) {
                lowestGrade = studentAverage;
            }
            
            totalGrades += student.getGradeCount();
        }
        
        double classAverage = totalAverage / students.size();
        
        System.out.println("\n=== CLASS STATISTICS ===");
        System.out.printf("Number of students: %d\n", students.size());
        System.out.printf("Total grades entered: %d\n", totalGrades);
        System.out.printf("Class average: %.2f\n", classAverage);
        System.out.printf("Highest average: %.2f\n", highestGrade);
        System.out.printf("Lowest average: %.2f\n", lowestGrade);
        System.out.println("=======================\n");
    }
    
    /**
     * Save student data to file
     * @param filename The filename to save to
     * @return true if save was successful, false otherwise
     */
    public boolean saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
                // Format: Name, ID, Grades (comma-separated)
                writer.printf("%s,%d,%s%n", 
                    student.getName(), 
                    student.getId(), 
                    student.getGradesString().replace(", ", ","));
            }
            System.out.println("Data saved to " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Load student data from file
     * @param filename The filename to load from
     * @return true if load was successful, false otherwise
     */
    public boolean loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Data file " + filename + " does not exist. Starting with empty gradebook.");
            return true;
        }
        
        try (Scanner scanner = new Scanner(file)) {
            students.clear(); // Clear existing data
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String name = parts[0];
                    int id = Integer.parseInt(parts[1]);
                    
                    Student student = new Student(name, id);
                    
                    // Add grades if they exist
                    if (parts.length > 2 && !parts[2].equals("No grades")) {
                        String[] gradeStrings = parts[2].split(",");
                        for (String gradeStr : gradeStrings) {
                            try {
                                double grade = Double.parseDouble(gradeStr.trim());
                                student.addGrade(grade);
                            } catch (NumberFormatException e) {
                                // Skip invalid grades
                            }
                        }
                    }
                    
                    students.add(student);
                }
            }
            System.out.println("Data loaded from " + filename + " (" + students.size() + " students)");
            return true;
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Export detailed report to file
     * @param filename The filename to export to
     * @return true if export was successful, false otherwise
     */
    public boolean exportReport(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== GRADEBOOK REPORT ===");
            writer.println("Generated on: " + java.time.LocalDateTime.now());
            writer.println();
            
            // Class statistics
            if (!students.isEmpty()) {
                double totalAverage = 0.0;
                for (Student student : students) {
                    totalAverage += student.getAverage();
                }
                double classAverage = totalAverage / students.size();
                writer.printf("Class Average: %.2f\n", classAverage);
                writer.printf("Number of Students: %d\n", students.size());
                writer.println();
            }
            
            // Student details
            for (int i = 0; i < students.size(); i++) {
                writer.println((i + 1) + ". " + students.get(i).toDetailedString());
                writer.println("---");
            }
            
            System.out.println("Report exported to " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("Error exporting report: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get the number of students in the gradebook
     * @return Number of students
     */
    public int getStudentCount() {
        return students.size();
    }
    
    /**
     * Get all students
     * @return List of all students
     */
    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
}
