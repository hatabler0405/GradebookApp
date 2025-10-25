#!/bin/bash

echo "=== Student Gradebook App Demo ==="
echo ""
echo "This demo will show you how to use the Gradebook App."
echo ""

echo "1. Testing Basic Gradebook App..."
echo "=================================="
echo "Adding a student and grades..."
echo ""

# Test basic app
echo "1
Alice Johnson
2001
2
2001
88.5
2
2001
92.0
2
2001
85.0
4
6
7
8
11" | java GradebookApp

echo ""
echo "2. Testing Enhanced Gradebook App..."
echo "===================================="
echo "Adding students with weighted categories..."
echo ""

# Test enhanced app
echo "1
Bob Wilson
2002
1
Carol Davis
2003
3
2002
Homework
85.0
3
2002
Tests
90.0
3
2002
Projects
88.0
3
2003
Homework
92.0
3
2003
Tests
95.0
3
2003
Projects
90.0
13
8
14
16" | java EnhancedGradebookApp

echo ""
echo "Demo completed! Check the generated data files:"
echo "- gradebook_data.txt (basic app data)"
echo "- enhanced_gradebook_data.txt (enhanced app data)"
echo ""
echo "You can also run the applications interactively:"
echo "- java GradebookApp (basic version)"
echo "- java EnhancedGradebookApp (enhanced version)"
