public class AcademicRecordViolation extends Violation {
    
    public AcademicRecordViolation(String violationName, String violationLevel, String description) {
        super("Academic Record", violationName, violationLevel, description);
    }

    @Override
    public String getCategoryNote() {
        return "NOTE: Academic Record violations involve tampering with or falsifying official academic documents.";
    }
}
