/*
OBJECT ORIENDTED PROGRAMMING
AIVT PROGRAM
Group 10
- Manaloto, Manzo Adiel D.
- Manansala, Justine Rouie M.
- Mangaliman, Rolaigne E.
- Mendoza, Gabriel Peter B.
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

class Main {
    
    private static ArrayList<Student> students = new ArrayList<>();
    private static final int MAX_YEAR_LEVEL = 5;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        try {
            while (running) {
                displayMenu();
                int choice = readMenuChoice();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewStudentViolationHistory();
                        break;
                    case 3:
                        updateStatus();
                        break;
                    case 4:
                        displayViolationStatistics();
                        break;
                    case 5:
                        displayAbout();
                        break;
                    case 6:
                        running = false;
                        System.out.println("\nThank you for using the -Academic Integrity Violation Tracker (AIVT)-.");
                        System.out.println("Program Terminated...");
                        break;
                    default:
                        break;
                }
                System.out.println();                
            }
        }
        catch (NoSuchElementException e) {
            System.out.println();
            System.out.println("Input ended unexpectedly. Program Terminated...\n");
        }
        finally {
            input.close();
        }
    }

    //Prints the main menu
    public static void displayMenu() {
        System.out.println("=========================================");
        System.out.println("  ACADEMIC INTEGRITY VIOLATION TRACKER");
        System.out.println("=========================================");
        System.out.println("1. Add Student Violation Record");
        System.out.println("2. View Student Violation History");
        System.out.println("3. Update Violation Status");
        System.out.println("4. Display Violation Statistics");
        System.out.println("5. About the Program");
        System.out.println("6. Exit");
       System.out.println("-----------------------------------------");
    }

    //Menu: 6 -- about the AIVT
    public static void displayAbout() {
       System.out.println("\n=========================================");
        System.out.println("            ABOUT THE PROGRAM");
        System.out.println("=========================================");
        System.out.println("Academic Integrity Violation Tracker (AIVT)\n -- A program that records and tracks students' academic integrity violations.");
         System.out.println("-----------------------------------------");
        System.out.println("How to use AIVT:");
        System.out.println("  1. Add Student Violation Record - enter the student's details, pick the");
        System.out.println("     violation code, then choose if it is a MAJOR or MINOR offense.");
        System.out.println("  2. View Student Violation History - enter a Student ID to see all of the records.");
        System.out.println("  3. Update Violation Status - mark a record as Under Investigation or Resolved.");
        System.out.println("  4. Display Violation Statistics - see totals per level, violation and category.");
        System.out.println("  5. About the Program - shows this screen.");
        System.out.println("  6. Exit - closes the program.");
        System.out.println("-----------------------------------------");
        System.out.println("Rule: after 2 minor offenses, any new minor offense\n becomes MAJOR automatically.");
        System.out.println("-----------------------------------------");
        System.out.println("Created by:");
        System.out.println("  Manaloto, Manansala, Mangaliman, Mendoza");
        System.out.println("=========================================");
    }

    public static int readMenuChoice() {
        while (true) {
            System.out.print("Enter choice (1-6): ");
            String line = input.nextLine().trim();

            try {
                int choice = Integer.parseInt(line);
 
                if (choice < 1 || choice > 6) {
                    throw new AIVTException("INVALID CHOICE. Please enter a number between 1-6.\n");
                }
                return choice;
            }
            catch (NumberFormatException e) {
                System.out.println("INVALID INPUT. Please enter a number between 1-6.\n");
            }
            catch (AIVTException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Menu: 1 -- add student
    public static void addStudent() {
        System.out.println("\n=========================================");
        System.out.println("          ADD VIOLATION RECORD");
        System.out.println("=========================================");
 
        String studentNum = readStudentId();
        Student student = searchStudent(studentNum);
 
        if (student != null) {
            System.out.println("\n-----------------------------------------");
            System.out.printf("A record already exists for Student ID: %s (%s).\n", studentNum, student.getStudentName());
            System.out.println("Adding this violation to that record.");
        }
        else {
            String name = readLettersOnly("Student Name: ", "Student name");
            int yearLevel = readYearLevel();
            String department = readLettersOnly("Department: ", "Department");
            String program = readLettersOnly("Course/Program: ", "Course/Program");
 
            student = new Student(name, studentNum, yearLevel, department, program);
            students.add(student);
        }
 
        addViolation(student);
    }
 
    public static Student searchStudent(String studentNum) {
        for (Student s : students) {
            if (s.getStudentNum().equalsIgnoreCase(studentNum)) {
                return s;
            }
        }
        return null;
    }
 
    private static Student findStudent(String studentNum) throws AIVTException {
        Student student = searchStudent(studentNum);
        if (student == null) {
            throw new AIVTException("No student found with Student ID: " + studentNum);
        }
        return student;
    }
 
    //pick student violaion, type of offense, & action taken
    public static void addViolation(Student student) {
        boolean addingMore = true;
 
        while (addingMore) {
            System.out.println();
            System.out.println("========SELECT ACADEMIC INTEGRITY VIOLATION========");
            System.out.println("1. Assessment");
            System.out.println("\t1a - Cheating");
            System.out.println("\t1b - Exam Misconduct");
            System.out.println("2. Attribution");
            System.out.println("\t2a - Plagiarism");
            System.out.println("\t2b - Improper Citation");
            System.out.println("3. Information");
            System.out.println("\t3a - Fabrication");
            System.out.println("\t3b - Falsification");
            System.out.println("4. Collaboration");
            System.out.println("\t4a - Contract Cheating");
            System.out.println("\t4b - Unauthorized Collaboration");
            System.out.println("5. Academic Record");
            System.out.println("\t5a - Record Tampering");
            System.out.println("\t5b - False Academic Document");
            System.out.println("-----------------------------------------");
            System.out.print("Enter violation code (e.g., 1b or C to cancel): ");
 
            String code = input.nextLine().trim();
 
            if (code.equalsIgnoreCase("C")) {
                System.out.println("Violation entry cancelled.");
                return;
            }
 
            Violation violation;
 
            try {
                violation = buildViolation(code);
            }
            catch (AIVTException e) {
                System.out.println(e.getMessage());
                System.out.println();
                continue;
            }
 
            System.out.println();
            System.out.println("========VIOLATION SELECTED========");
            System.out.println("Category    : " + violation.getCategory());
            System.out.println("Violation   : " + violation.getViolationName());
            System.out.println("Description : " + violation.getDescription());
            System.out.println("-----------------------------------------\n");
 
            String chosenLevel = readViolationLevel();
            violation.setViolationLevel(chosenLevel);
            System.out.println("Violation Level: " + violation.getViolationLevel());
            System.out.println("-----------------------------------------");

            System.out.print("Action Taken (press Enter to skip): ");
            String action = input.nextLine().trim();
            if (action.isEmpty()) {
                action = "None";
            }
            violation.setConsequences(action);
 
            String levelBefore = violation.getViolationLevel();
            student.addViolation(violation);
 
            System.out.println();
            System.out.println("========VIOLATION RECORDED========");
            System.out.println("\nStudent has violated " + student.getViolationCount()
                    + " academic integrity rule" + (student.getViolationCount() == 1 ? "" : "s") + ".");
            System.out.println("\n-----------------------------------------");
            System.out.println("Category     : " + violation.getCategory());
            System.out.println("Violation    : " + violation.getViolationName());
            System.out.println("Level        : " + violation.getViolationLevel());
            System.out.println("Status       : " + violation.getStatus());
            System.out.println("Action Taken : " + violation.getConsequences());
            System.out.println("-----------------------------------------");
 
            if (!levelBefore.equalsIgnoreCase(violation.getViolationLevel())) {
                System.out.println("NOTICE: This student already has 2 MINOR offenses, so this offense has been upgraded to MAJOR.");
            }
            if (violation.getViolationLevel().equalsIgnoreCase("Major")) {
                int majors = student.getMajorCount();
                if (majors == 2) {
                    System.out.println("NOTICE: This student now has 2 MAJOR offenses.");
                    System.out.println("Notify the student that one more offense will result in a disciplinary note.\n");
                }
                else if (majors >= 3) {
                    System.out.printf("WARNING: This student now has %d MAJOR offenses.\n", majors);
                    System.out.println("Refer to the Discipline Office for action (e.g., suspension or expulsion).");
                    System.out.println("The student's parent/guardian must also be contacted.");
                }
            }
 
            if (!readYesNo("Add another violation for this student? (Y/N): ")) {
                addingMore = false;
                System.out.println("\nReturning to the main menu...");
            }
        }
    }
 
    private static String readViolationLevel() {
        while (true) {
            System.out.println("Select the Offense Level:");
            System.out.println("  1. Major");
            System.out.println("  2. Minor");
            System.out.print("Enter level (1-2): ");
            String choice = input.nextLine().trim();
 
            try {
                if (choice.equals("1")) {
                    return "Major";
                }
                if (choice.equals("2")) {
                    return "Minor";
                }
                throw new AIVTException("Invalid level. Please enter 1 for Major or 2 for Minor.\n");
            } catch (AIVTException e) {
                System.out.println(e.getMessage());
            }
        }
    }
 
    public static Violation buildViolation(String code) throws AIVTException {
        switch (code.toLowerCase()) {
            case "1a":
                return new AssessmentViolation("Cheating", "Not set",
                        "Using unauthorized materials, devices, or assistance during an academic exercise.");
            case "1b":
                return new AssessmentViolation("Exam Misconduct", "Not set",
                        "Violating exam rules or procedures, such as unauthorized communication during a test.");
            case "2a":
                return new AttributionViolation("Plagiarism", "Not set",
                        "Presenting, submitting, or copying another person's work, or resubmitting one's own or another person's work.");
            case "2b":
                return new AttributionViolation("Improper Citation", "Not set",
                        "Failing to properly cite or acknowledge sources used in academic work.");
            case "3a":
                return new InformationViolation("Fabrication", "Not set",
                        "Inventing or making up data, information, or results that were not actually obtained.");
            case "3b":
                return new InformationViolation("Falsification", "Not set",
                        "Manipulating or altering data, information, or materials to misrepresent the truth.");
            case "4a":
                return new CollaborationViolation("Contract Cheating", "Not set",
                        "Having someone else complete academic work on one's behalf, with or without payment.");
            case "4b":
                return new CollaborationViolation("Unauthorized Collaboration", "Not set",
                        "Working with others on an assignment that was meant to be completed individually.");
            case "5a":
                return new AcademicRecordViolation("Record Tampering", "Not set",
                        "Altering official academic records, grades, or transcripts without authorization.");
            case "5b":
                return new AcademicRecordViolation("False Academic Document", "Not set",
                        "Creating, submitting, or using a forged or fraudulent academic document.");
            default:
                throw new AIVTException("Invalid violation code: " + code);
        }
    }
 
    //Menu: 2 -- view student violation history
    public static void viewStudentViolationHistory() {
        System.out.println("\n=========================================");
        System.out.println("        STUDENT VIOLATION HISTORY");
        System.out.println("=========================================");
        System.out.print("Enter Student ID: ");
        String studentNum = input.nextLine().trim();
 
        try {
            Student student = findStudent(studentNum);
            student.displayViolationHistory();
        } catch (AIVTException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }
 
    //Menu: 3 -- update violation status
    public static void updateStatus() {
        System.out.println("\n=========================================");
        System.out.println("         UPDATE VIOLATION STATUS");
        System.out.println("=========================================");
        System.out.print("Enter Student ID: ");
        String studentNum = input.nextLine().trim();
 
        Student student;
        try {
            student = findStudent(studentNum);
        } catch (AIVTException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return;
        }
 
        System.out.println("-----------------------------------------");
        System.out.println("Student: " + student.getStudentName());
 
        ArrayList<Violation> history = student.getViolationHistory();
        int count = history.size();
 
        if (count == 0) {
            System.out.println("This student has no recorded violations.");
            return;
        }
 
        System.out.println("\nViolation Records:");
        for (int i = 0; i < count; i++) {
            Violation v = history.get(i);
            System.out.println("-----------------------------------------");
            System.out.println("Record #" + (i + 1));
            System.out.println("  Violation : " + v.getViolationName() + " (" + v.getCategory() + ")");
            System.out.println("  Level     : " + v.getViolationLevel());
            System.out.println("  Status    : " + v.getStatus());
        }
        System.out.println("-----------------------------------------");
 
        int recordNum;
        while (true) {
            System.out.print("Enter the Record # to update (1-" + count + ") or C to cancel: ");
            String choice = input.nextLine().trim();
            if (choice.equalsIgnoreCase("C")) {
                System.out.println("Update cancelled.");
                return;
            }
            try {
                recordNum = parseRecordNumber(choice, count);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please type the Record # shown above (for example: 1) or C to cancel.\n");
            } catch (AIVTException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
 
        Violation selected = history.get(recordNum - 1);
 
        System.out.println("-----------------------------------------");
        System.out.println("Updating Record #" + recordNum + ": " + selected.getViolationName());
        System.out.println("Select New Status:");
        System.out.println("  1. Under Investigation - Case is being reviewed");
        System.out.println("  2. Resolved - Required process is completed");
 
        String newStatus;
        while (true) {
            System.out.print("Enter status (1-2): ");
            String statusChoice = input.nextLine().trim();
            try {
                newStatus = parseStatusChoice(statusChoice);
                break;
            } catch (AIVTException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
 
        selected.setStatus(newStatus);
        System.out.println("\n-----------------------------------------");
        System.out.println("Status updated successfully.");
        System.out.println("Record #" + recordNum + " - " + selected.getViolationName());
        System.out.println("New Status: " + selected.getStatus());
    }
 
    private static int parseRecordNumber(String text, int count) throws AIVTException {
        int recordNum = Integer.parseInt(text);
        if (recordNum < 1 || recordNum > count) {
            throw new AIVTException("Invalid Record #. Please enter a number between 1 and " + count + ".");
        }
        return recordNum;
    }
 
    private static String parseStatusChoice(String text) throws AIVTException {
        switch (text) {
            case "1":
                return "Under Investigation";
            case "2":
                return "Resolved";
            default:
                throw new AIVTException("Invalid status choice. Please enter 1 or 2.");
        }
    }
 
    //Menu: 4 -- view violation statistics
    public static void displayViolationStatistics() {
        System.out.println("\n=========================================");
        System.out.println("          VIOLATION STATISTICS");
        System.out.println("=========================================");
 
        String[] categoryNames = {"Assessment", "Attribution", "Information",
                                  "Collaboration", "Academic Record"};
        int[] categoryCounts = new int[categoryNames.length];
 
        String[] violationNames = {"Cheating", "Exam Misconduct", "Plagiarism",
                                   "Improper Citation", "Fabrication", "Falsification",
                                   "Contract Cheating", "Unauthorized Collaboration",
                                   "Record Tampering", "False Academic Document"};
        int[] violationCounts = new int[violationNames.length];
 
        int totalViolations = 0;
        int majorCount = 0;
        int minorCount = 0;
 
        for (int i = 0; i < students.size(); i++) {
            ArrayList<Violation> history = students.get(i).getViolationHistory();
 
            for (int j = 0; j < history.size(); j++) {
                Violation v = history.get(j);
                totalViolations++;
 
                if (v.getViolationLevel().equalsIgnoreCase("Major")) {
                    majorCount++;
                } else if (v.getViolationLevel().equalsIgnoreCase("Minor")) {
                    minorCount++;
                }
 
                for (int k = 0; k < violationNames.length; k++) {
                    if (violationNames[k].equals(v.getViolationName())) {
                        violationCounts[k]++;
                    }
                }
 
                for (int k = 0; k < categoryNames.length; k++) {
                    if (categoryNames[k].equals(v.getCategory())) {
                        categoryCounts[k]++;
                    }
                }
            }
        }
 
        for (int i = 0; i < violationCounts.length - 1; i++) {
            for (int j = 0; j < violationCounts.length - 1 - i; j++) {
                if (violationCounts[j] < violationCounts[j + 1]) {
                    int tempCount = violationCounts[j];
                    violationCounts[j] = violationCounts[j + 1];
                    violationCounts[j + 1] = tempCount;
 
                    String tempName = violationNames[j];
                    violationNames[j] = violationNames[j + 1];
                    violationNames[j + 1] = tempName;
                }
            }
        }
 
        System.out.println("University Violation Summary");
        System.out.println("-----------------------------------------");
        System.out.println("Total Violations : " + totalViolations);
        System.out.println("Major Violations : " + majorCount);
        System.out.println("Minor Violations : " + minorCount);
 
        System.out.println();
        System.out.println("Most Reported Violations");
        System.out.println("-----------------------------------------");
        if (totalViolations == 0) {
            System.out.println("No violations recorded yet.");
        } else {
            for (int i = 0; i < violationNames.length; i++) {
                if (violationCounts[i] > 0) {
                    System.out.println(violationNames[i] + ": " + violationCounts[i]);
                }
            }
        }
 
        System.out.println();
        System.out.println("Violation Categories");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < categoryNames.length; i++) {
            System.out.println(categoryNames[i] + ": " + categoryCounts[i]);
        }
        System.out.println("=========================================");
    }
 
    private static String readStudentId() {
        while (true) {
            System.out.print("Student ID: ");
            String value = input.nextLine().trim();
 
            if (value.isEmpty()) {
                System.out.println("Student ID cannot be blank.");
            }
            else if (!value.matches("[A-Za-z0-9-]+") || !value.matches(".*[0-9].*")) {
                System.out.println("Student ID can only contain letters, numbers and dashes, and must have at least one number.\n");
            }
            else {
                return value;
            }
        }
    }

    private static String readLettersOnly(String prompt, String fieldName) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();
 
            if (value.isEmpty()) {
                System.out.println(fieldName + " cannot be blank.");
            }
            else if (!value.matches("[A-Za-z][A-Za-z .,&()'/-]+")) {
                System.out.println(fieldName + " must be written in letters (at least 2) and cannot contain numbers.\n");
            }
            else {
                return value;
            }
        }
    }
 
    private static int readYearLevel() {
        while (true) {
            System.out.print("Year Level (1-" + MAX_YEAR_LEVEL + "): ");
            String line = input.nextLine().trim();
            try {
                return parseYearLevel(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a whole number.\n");
            } catch (AIVTException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
 
    private static int parseYearLevel(String text) throws AIVTException {
        int year = Integer.parseInt(text);
        if (year < 1 || year > MAX_YEAR_LEVEL) {
            throw new AIVTException("Year level must be between 1 and " + MAX_YEAR_LEVEL + ".");
        }
        return year;
    }
 
    private static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = input.nextLine().trim();
            try {
                if (answer.equalsIgnoreCase("Y")) {
                    return true;
                }
                if (answer.equalsIgnoreCase("N")) {
                    return false;
                }
                throw new AIVTException("Invalid input. Please enter Y or N.\n");
            } catch (AIVTException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
}