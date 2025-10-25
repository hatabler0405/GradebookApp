# Student Gradebook App - Feature Summary

## 🎯 Project Overview
A comprehensive Java application for managing student grades with support for weighted categories, data persistence, and advanced analytics.

## ✅ Core Features Implemented

### 1. Student Class (`Student.java`)
- **Instance Variables**: name, id, ArrayList<Double> grades
- **Methods**:
  - `addGrade(double grade)` - Add grade to student
  - `getAverage()` - Calculate average grade
  - `getLetterGrade()` - Get letter grade (A, B, C, D, F)
  - `toString()` - Display student info
  - **Advanced**: Support for multiple subjects with `addGradeForSubject()`

### 2. Gradebook Class (`Gradebook.java`)
- **Instance Variables**: ArrayList<Student> students
- **Methods**:
  - `addStudent(String name, int id)` - Add new student
  - `addGrade(int id, double grade)` - Add grade for student
  - `displayAll()` - Show all students
  - `rankStudents()` - Rank by performance
  - `saveToFile(String filename)` - Save data
  - `loadFromFile(String filename)` - Load data
  - **Advanced**: Grade distribution, class statistics, export reports

### 3. WeightedGradebook Class (`WeightedGradebook.java`)
- **Enhanced Features**:
  - Weighted categories (Homework 30%, Tests 50%, Projects 20%)
  - `addGrade(int id, String category, double grade)` - Add category grades
  - `getWeightedAverage(int id)` - Calculate weighted average
  - `rankStudentsByWeightedAverage()` - Weighted rankings
  - Category weight management

### 4. Console Applications
- **Basic App** (`GradebookApp.java`): 11 menu options
- **Enhanced App** (`EnhancedGradebookApp.java`): 16 menu options

## 🚀 Advanced Features

### Data Persistence
- ✅ Save/load from CSV format
- ✅ Automatic data saving on exit
- ✅ Data format: `Name,ID,Grade1,Grade2,Grade3,...`

### Grade Management
- ✅ Overall grades
- ✅ Subject-specific grades
- ✅ Category-based grades with weights
- ✅ Grade validation (0-100 range)

### Analytics & Reporting
- ✅ Student rankings (regular and weighted)
- ✅ Grade distribution charts
- ✅ Class statistics
- ✅ Individual student reports
- ✅ Export to text files

### User Interface
- ✅ Intuitive console menu system
- ✅ Input validation and error handling
- ✅ Clear formatting and organization
- ✅ Interactive prompts

## 📊 Menu Options

### Basic Application (11 options)
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

### Enhanced Application (16 options)
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

## 🎨 Grade Distribution
- A (90-100): Count of students
- B (80-89): Count of students
- C (70-79): Count of students
- D (60-69): Count of students
- F (0-59): Count of students

## 📈 Class Statistics
- Number of students
- Total grades entered
- Class average
- Highest average
- Lowest average

## 💾 Data Format Example
```
John Doe,1001,85.5,92.0,78.5
Jane Smith,1002,90.0,88.5,95.0
```

## 🛠️ Technical Implementation
- **Language**: Java 8+
- **Dependencies**: None (pure Java)
- **File I/O**: CSV format with error handling
- **Data Structures**: ArrayList for dynamic storage
- **Error Handling**: Input validation and exception handling

## 🎯 Usage Examples

### Adding Students
```java
gradebook.addStudent("Alice Johnson", 2001);
```

### Adding Grades
```java
// Overall grade
gradebook.addGrade(2001, 88.5);

// Category grade
gradebook.addGrade(2001, "Homework", 85.0);
```

### Weighted Categories
```java
gradebook.setCategoryWeight("Homework", 0.30);
gradebook.setCategoryWeight("Tests", 0.50);
gradebook.setCategoryWeight("Projects", 0.20);
```

## 🏆 Key Achievements
- ✅ Complete OOP implementation
- ✅ File I/O with data persistence
- ✅ Advanced features (weighted categories)
- ✅ Comprehensive reporting
- ✅ User-friendly interface
- ✅ Error handling and validation
- ✅ Extensible design
- ✅ Well-documented code

## 🚀 Running the Application
```bash
# Compile
javac *.java

# Run basic version
java GradebookApp

# Run enhanced version
java EnhancedGradebookApp

# Run demo
./demo.sh
```

This implementation exceeds the basic requirements by providing advanced features like weighted categories, comprehensive reporting, and a professional user interface.
