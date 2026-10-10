public class CollaborationViolation extends Violation {
        
    public CollaborationViolation(String violationName, String violationLevel, String description) {
        super("Collaboration", violationName, violationLevel, description);
    }

    @Override
    public String getCategoryNote() {
        return "NOTE: Collaboration violations involve improper teamwork on work meant to be done independently.";
    }
}
