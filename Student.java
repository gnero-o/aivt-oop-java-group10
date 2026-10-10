/*
OBJECT ORIENDTED PROGRAMMING
AIVT PROGRAM
Group 10
- Manaloto, Manzo Adiel D.
- Manansala, Justine Rouie M.
- Mangaliman, Rolaigne E.
- Mendoza, Gabriel Peter B.
*/

import java.util.ArrayList;

class Student {
    
    private String studentName;
    private String studentNum;
    private int yearLevel;
    private String studentDepartment;
    private String studentProgram;

    private ArrayList<Violation> violationHistory;
    private static final int MAX_MINOR = 2;
    
   public Student(String studentName, String studentNum, int yearLevel, String studentDepartment, String studentProgram) {
    this.studentName = studentName;
    this.studentNum = studentNum;
    this.yearLevel = yearLevel;
    this.studentDepartment = studentDepartment;
    this.studentProgram = studentProgram;
    this.violationHistory = new ArrayList<>();
   }

   public String getStudentName() {
    return studentName;
   }

   public void setStudentName(String studentName) {
    this.studentName = studentName;
   }

   public String getStudentNum() {
    return studentNum;
   }

   public void setStudentNum(String studentNum) {
    this.studentNum = studentNum;
   }

   public int getYearLevel() {
    return yearLevel;
   }

   public void setYearLevel(int yearLevel) {
    this.yearLevel = yearLevel;
   }

   public String getStudentDepartment() {
    return studentDepartment;
   }

   public void setStudentDepartment(String studentDepartment) {
    this.studentDepartment = studentDepartment;
   }

   public String getStudentProgram() {
    return studentProgram;
   }

   public void setStudentProgram(String studentProgram) {
    this.studentProgram = studentProgram;
   }

    public ArrayList<Violation> getViolationHistory() {
    return violationHistory;
   }

   public int getMinorCount() {
    int count = 0;
    for (Violation v : violationHistory) {
        if (v.getViolationLevel().equalsIgnoreCase("Minor")) {
            count++;
        }
    }
    return count;
   }

   public int getMajorCount() {
    int count = 0;
    for (Violation v : violationHistory) {
        if (v.getViolationLevel().equalsIgnoreCase("Major")) {
            count++;
        }
    }
    return count;
   }

   public void addViolation(Violation violation) {
    if (violation.getViolationLevel().equalsIgnoreCase("Minor") && getMinorCount() >= MAX_MINOR) {
        violation.setViolationLevel("Major");
    }
    violationHistory.add(violation);
   }

   public int getViolationCount() {
    return violationHistory.size();
   }

   public void displayStudentInfo() {
    System.out.printf("Name: %s\n", studentName);
    System.out.printf("Student ID: %s\n", studentNum);
    System.out.printf("Year Level: %d\n", yearLevel);
    System.out.printf("Department: %s\n", studentDepartment);
    System.out.printf("Course/Program: %s\n", studentProgram);
   }

   public void displayViolationHistory() {
    System.out.println("\n========STUDENT INFORMATION========");
    displayStudentInfo();

    if (violationHistory.isEmpty()) {
        System.out.println("-----------------------------------------");
        System.out.println("This student has no recorded violations.");
        return;
    }

    for (int i = 0; i < violationHistory.size(); i++) {
        System.out.println("-----------------------------------------");
        System.out.println("\nViolation #" + (i+1));
        System.out.println("----------------------------------");
        violationHistory.get(i).displayViolation();
    }
    System.out.println("=========================================");
   }   
}