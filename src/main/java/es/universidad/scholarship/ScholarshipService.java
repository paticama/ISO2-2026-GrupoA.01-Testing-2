package es.universidad.scholarship;

/**
 * Service responsible for validating student profiles and determining
 * the most appropriate scholarship category according to business rules.
 *
 * <p>Ambiguity resolution assumption: a student profile may satisfy the
 * criteria of more than one scholarship simultaneously (for instance,
 * Excellence and Inclusion, or Residence and Large Family). When multiple
 * categories apply, this service evaluates all eligibility rules and selects
 * the highest-priority category in the following deterministic order:
 * {@link ScholarshipType#EXCELLENCE}, {@link ScholarshipType#INCLUSION},
 * {@link ScholarshipType#RESIDENCE}, {@link ScholarshipType#LARGE_FAMILY},
 * and {@link ScholarshipType#NONE} when none apply.</p>
 */
public class ScholarshipService {

    private static final double MIN_GPA = 0.0;
    private static final double MAX_GPA = 10.0;
    private static final int MIN_DISABILITY_PERCENTAGE = 0;
    private static final int MAX_DISABILITY_PERCENTAGE = 100;

    private static final double EXCELLENCE_MIN_GPA = 8.5;
    private static final double EXCELLENCE_MAX_INCOME_EXCLUSIVE = 30000.0;

    private static final double RESIDENCE_MIN_GPA = 6.5;
    private static final double RESIDENCE_MAX_GPA = 8.4;
    private static final double RESIDENCE_MAX_INCOME_EXCLUSIVE = 20000.0;
    private static final double RESIDENCE_MIN_DISTANCE_EXCLUSIVE = 50.0;

    private static final double LARGE_FAMILY_MIN_GPA = 5.0;
    private static final double LARGE_FAMILY_MAX_INCOME_EXCLUSIVE = 15000.0;
    private static final int LARGE_FAMILY_MIN_SIBLINGS = 3;

    private static final int INCLUSION_MIN_DISABILITY = 33;
    private static final double INCLUSION_MIN_GPA = 5.0;

    /**
     * Determines the single most appropriate scholarship category for the given student.
     *
     * @param student the student profile to evaluate
     * @return the assigned {@link ScholarshipType}, or {@link ScholarshipType#NONE} if ineligible
     * @throws IllegalArgumentException if {@code student} is {@code null} or has invalid field values
     */
    public ScholarshipType determineScholarship(StudentProfile student) {
        isValidProfile(student);

        boolean eligibleExcellence = meetsExcellenceRequirements(student);
        boolean eligibleInclusion = meetsInclusionRequirements(student);
        boolean eligibleResidence = meetsResidenceRequirements(student);
        boolean eligibleLargeFamily = meetsLargeFamilyRequirements(student);

        if (eligibleExcellence) {
            return ScholarshipType.EXCELLENCE;
        }
        if (eligibleInclusion) {
            return ScholarshipType.INCLUSION;
        }
        if (eligibleResidence) {
            return ScholarshipType.RESIDENCE;
        }
        if (eligibleLargeFamily) {
            return ScholarshipType.LARGE_FAMILY;
        }
        return ScholarshipType.NONE;
    }

    /**
     * Checks whether the student meets the Excellence Scholarship requirements:
     * GPA &gt;= 8.5 and family income &lt; 30,000.
     *
     * @param student the student profile to evaluate
     * @return {@code true} if eligible for Excellence Scholarship
     * @throws IllegalArgumentException if {@code student} is {@code null} or invalid
     */
    public boolean meetsExcellenceRequirements(StudentProfile student) {
        isValidProfile(student);
        return student.getGpa() >= EXCELLENCE_MIN_GPA
                && student.getFamilyIncome() < EXCELLENCE_MAX_INCOME_EXCLUSIVE;
    }

    /**
     * Checks whether the student meets the Residence Scholarship requirements:
     * 6.5 &lt;= GPA &lt;= 8.4, family income &lt; 20,000, and distance &gt; 50 km.
     *
     * @param student the student profile to evaluate
     * @return {@code true} if eligible for Residence Scholarship
     * @throws IllegalArgumentException if {@code student} is {@code null} or invalid
     */
    public boolean meetsResidenceRequirements(StudentProfile student) {
        isValidProfile(student);
        return student.getGpa() >= RESIDENCE_MIN_GPA
                && student.getGpa() <= RESIDENCE_MAX_GPA
                && student.getFamilyIncome() < RESIDENCE_MAX_INCOME_EXCLUSIVE
                && student.getDistanceFromCampus() > RESIDENCE_MIN_DISTANCE_EXCLUSIVE;
    }

    /**
     * Checks whether the student meets the Large Family Scholarship requirements:
     * GPA &gt;= 5.0, family income &lt; 15,000, and siblings &gt;= 3.
     *
     * @param student the student profile to evaluate
     * @return {@code true} if eligible for Large Family Scholarship
     * @throws IllegalArgumentException if {@code student} is {@code null} or invalid
     */
    public boolean meetsLargeFamilyRequirements(StudentProfile student) {
        isValidProfile(student);
        return student.getGpa() >= LARGE_FAMILY_MIN_GPA
                && student.getFamilyIncome() < LARGE_FAMILY_MAX_INCOME_EXCLUSIVE
                && student.getNumberOfSiblings() >= LARGE_FAMILY_MIN_SIBLINGS;
    }

    /**
     * Checks whether the student meets the Inclusion Scholarship requirements:
     * disability degree &gt;= 33% and GPA &gt;= 5.0.
     *
     * @param student the student profile to evaluate
     * @return {@code true} if eligible for Inclusion Scholarship
     * @throws IllegalArgumentException if {@code student} is {@code null} or invalid
     */
    public boolean meetsInclusionRequirements(StudentProfile student) {
        isValidProfile(student);
        return student.getDisabilityDegree() >= INCLUSION_MIN_DISABILITY
                && student.getGpa() >= INCLUSION_MIN_GPA;
    }

    /**
     * Validates that the student profile is non-null and all attributes fall
     * within valid domain bounds.
     *
     * @param student the student profile to validate
     * @return {@code true} if the profile is valid
     * @throws IllegalArgumentException if {@code student} is {@code null} or any field is invalid
     */
    public boolean isValidProfile(StudentProfile student) {
        if (student == null) {
            throw new IllegalArgumentException("Student profile must not be null.");
        }
        double gpa = student.getGpa();
        if (Double.isNaN(gpa) || gpa < MIN_GPA || gpa > MAX_GPA) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 10.0.");
        }
        double income = student.getFamilyIncome();
        if (Double.isNaN(income) || Double.isInfinite(income) || income < 0.0) {
            throw new IllegalArgumentException("Family income must be a finite value >= 0.");
        }
        double distance = student.getDistanceFromCampus();
        if (Double.isNaN(distance) || Double.isInfinite(distance) || distance < 0.0) {
            throw new IllegalArgumentException("Distance from campus must be a finite value >= 0.");
        }
        int disability = student.getDisabilityDegree();
        if (disability < MIN_DISABILITY_PERCENTAGE || disability > MAX_DISABILITY_PERCENTAGE) {
            throw new IllegalArgumentException("Disability degree must be between 0 and 100.");
        }
        int siblings = student.getNumberOfSiblings();
        if (siblings < 0) {
            throw new IllegalArgumentException("Number of siblings must be >= 0.");
        }
        return true;
    }
}
