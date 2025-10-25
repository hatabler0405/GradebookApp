# Student Gradebook App

A comprehensive Java application for managing student grades with support for weighted categories, data persistence, and advanced analytics.

## Features

### Core Features
- **Student Management**: Add, view, and manage student information
- **Grade Management**: Add grades with support for multiple subjects and categories
- **Data Persistence**: Save and load data from text files
- **Rankings**: Rank students by performance (regular and weighted)
- **Statistics**: View class statistics and grade distributions
- **Search**: Find students by ID

### Advanced Features
- **Weighted Categories**: Support for different category weights (e.g., Homework 30%, Tests 50%, Projects 20%)
- **Multiple Subjects**: Track grades across different subjects
- **Grade Distribution**: Visual representation of grade distribution
- **Export Reports**: Generate detailed reports in text format
- **Enhanced Analytics**: Comprehensive grade analysis and reporting

## Files Structure

```
GradebookApp/
├── Student.java                 # Student class with grade management
├── Gradebook.java              # Basic gradebook functionality
├── WeightedGradebook.java        # Enhanced gradebook with weighted categories
├── GradebookApp.java           # Basic console application
├── EnhancedGradebookApp.java   # Enhanced console application
└── README.md                   # This file
```

## How to Run

### Basic Version
```bash
javac *.java
java GradebookApp
```

### Enhanced Version (Recommended)
```bash
javac *.java
java EnhancedGradebookApp
```

## Usage Examples

### Adding Students
1. Choose "Add Student" from the menu
2. Enter student name and ID
3. Student is added to the gradebook

### Adding Grades
- **Overall Grades**: Add grades that contribute to the overall average
- **Category Grades**: Add grades to specific categories (Homework, Tests, Projects)

### Managing Category Weights
1. Choose "Manage Category Weights" from the menu
2. Enter category name and weight (0.0 to 1.0)
3. Weights are used to calculate weighted averages

### Viewing Reports
- **All Students**: View basic information for all students
- **Detailed Info**: View comprehensive student information
- **Weighted Info**: View weighted averages and category breakdowns
- **Rankings**: See students ranked by performance
- **Statistics**: View class-wide statistics and grade distributions

## Data Format

The application saves data in CSV format:
```
Student Name,ID,Grade1,Grade2,Grade3,...
John Doe,1001,85.5,92.0,78.5
Jane Smith,1002,90.0,88.5,95.0
```

## Menu Options

### Basic Application (GradebookApp.java)
1. Add Student
2. Add Grade (Overall)
3. Add Grade (by Subject)
4. View All Students
5. View Detailed Student Info
6. Rank Students by Performance
7. View Grade Distribution
8. View Class Statistics
9. Search Student
10. Export Report
11. Save and Exit

### Enhanced Application (EnhancedGradebookApp.java)
1. Add Student
2. Add Grade (Overall)
3. Add Grade (by Category)
4. View All Students
5. View Detailed Student Info
6. View Weighted Student Info
7. Rank Students (Regular)
8. Rank Students (Weighted)
9. View Grade Distribution
10. View Class Statistics
11. Search Student
12. Manage Category Weights
13. View Category Weights
14. Generate Grade Report
15. Export Report
16. Save and Exit

## Advanced Features

### Weighted Categories
- Set different weights for different assignment types
- Calculate weighted averages based on category weights
- Compare regular vs weighted rankings

### Grade Distribution
- Visual representation of grade distribution
- Count of students in each grade range (A, B, C, D, F)

### Export Functionality
- Export detailed reports to text files
- Include timestamps and comprehensive statistics
- Save data automatically on exit

## Requirements

- Java 8 or higher
- No external dependencies required

## Example Workflow

1. **Start the application**: `java EnhancedGradebookApp`
2. **Add students**: Use option 1 to add students with names and IDs
3. **Set category weights**: Use option 12 to set weights (e.g., Homework: 0.3, Tests: 0.5, Projects: 0.2)
4. **Add grades**: Use option 3 to add grades by category
5. **View rankings**: Use option 8 to see weighted student rankings
6. **Generate report**: Use option 14 to generate a comprehensive report
7. **Export data**: Use option 15 to export to a file
8. **Save and exit**: Use option 16 to save data and exit

## Tips

- Use consistent category names (e.g., "Homework", "Tests", "Projects")
- Category weights should sum to 1.0 for best results
- Data is automatically saved when you exit the application
- You can run both basic and enhanced versions independently
