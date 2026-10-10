public class AssessmentViolation extends Violation{

    public AssessmentViolation(String violationName, String violationLevel, String description) {
        super("Assessment", violationName, violationLevel, description);
    }

    @Override
    public String getCategoryNote() {
        return "NOTE: Assessment violations affect the integrity of graded work and are reviewed by the instructor.";
    }   
}
