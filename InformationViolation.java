public class InformationViolation extends Violation {
        
    public InformationViolation(String violationName, String violationLevel, String description) {
        super("Information", violationName, violationLevel, description);
    }

    @Override
    public String getCategoryNote() {
        return "NOTE: Information violations involve inventing or altering data, facts, or sources.";
    }
}
