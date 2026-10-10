/*
OBJECT ORIENDTED PROGRAMMING
AIVT PROGRAM
Group 10
- Manaloto, Manzo Adiel D.
- Manansala, Justine Rouie M.
- Mangaliman, Rolaigne E.
- Mendoza, Gabriel Peter B.
*/

abstract class Violation {

    private String category;
    private String violationName;
    private String violationLevel;
    private String status;
    private String consequences;
    private String description;

    public Violation(String category, String violationName, String violationLevel, String description) {
        this.category = category;
        this.violationName = violationName;
        this.violationLevel = violationLevel;
        this.description = description;
        this.status = "Pending";
        this.consequences = "";
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getViolationName() {
        return violationName;
    }

    public void setViolationName(String violationName) {
        this.violationName = violationName;
    }

    public String getViolationLevel() {
        return violationLevel;
    }

    public void setViolationLevel(String violationLevel) {
        this.violationLevel = violationLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConsequences() {
        return consequences;
    }

    public void setConsequences(String consequences) {
        this.consequences = consequences;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void displayViolation() {
        System.out.printf("Category: %s\n", category);
        System.out.printf("Violation: %s\n", violationName);
        System.out.printf("Violation Level: %s\n", violationLevel);
        System.out.printf("Status: %s\n", status);
        System.out.printf("Action Taken: %s\n", consequences);
        System.out.printf("Description: %s\n", description);

        String note = getCategoryNote();
        if (note != null && !note.isEmpty()) {
            System.out.println(note);
        }
    }

    public abstract String getCategoryNote();
    
}