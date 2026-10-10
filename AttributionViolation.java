public class AttributionViolation extends Violation{
     
    public AttributionViolation(String violationName, String violationLevel, String description) {
        super("Attribution", violationName, violationLevel, description);
    }

    @Override
    public String getCategoryNote() {
        return "NOTE: Attribution violations involve failing to properly credit the original source of work or ideas.";
    }   
}