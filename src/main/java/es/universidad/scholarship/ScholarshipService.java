package es.universidad.scholarship;

/**
 * Service for validating student profiles and determining scholarship eligibility.
 *
 * <p>When a student qualifies for multiple scholarships, all rules are evaluated
 * and the highest-priority category is returned in the following order:
 * {@code EXCELLENCE}, {@code INCLUSION}, {@code RESIDENCE}, {@code LARGE_FAMILY},
 * or {@code NONE} if no category applies.</p>
 */
public class ScholarshipService {

    /**
     * Determines the single most appropriate scholarship category for the student.
     *
     * @param student the student profile to evaluate
     * @return the assigned {@link ScholarshipType}
     * @throws IllegalArgumentException if the profile is null or invalid
     */
    public ScholarshipType determineScholarship(StudentProfile student) {
        isValidProfile(student);

        boolean excellence = meetsExcellenceRequirements(student);
        boolean inclusion = meetsInclusionRequirements(student);
        boolean residence = meetsResidenceRequirements(student);
        boolean largeFamily = meetsLargeFamilyRequirements(student);

        if (excellence) {
            return ScholarshipType.EXCELLENCE;
        }
        if (inclusion) {
            return ScholarshipType.INCLUSION;
        }
        if (residence) {
            return ScholarshipType.RESIDENCE;
        }
        if (largeFamily) {
            return ScholarshipType.LARGE_FAMILY;
        }
        return ScholarshipType.NONE;
    }

    /**
     * Checks Excellence Scholarship eligibility: GPA &gt;= 8.5 and family income &lt; 30,000.
     */
    public boolean meetsExcellenceRequirements(StudentProfile student) {
        isValidProfile(student);
        return student.getGpa() >= 8.5 && student.getFamilyIncome() < 30000.0;
    }

    /**
     * Checks Residence Scholarship eligibility: 6.5 &lt;= GPA &lt;= 8.4,
     * family income &lt; 20,000, and distance &gt; 50 km.
     */
    public boolean meetsResidenceRequirements(StudentProfile student) {
        isValidProfile(student);
        return student.getGpa() >= 6.5
                && student.getGpa() <= 8.4
                && student.getFamilyIncome() < 20000.0
                && student.getDistanceFromCampus() > 50.0;
    }

    /**
     * Checks Large Family Scholarship eligibility: GPA &gt;= 5.0,
     * family income &lt; 15,000, and siblings &gt;= 3.
     */
    public boolean meetsLargeFamilyRequirements(StudentProfile student) {
        isValidProfile(student);
        return student.getGpa() >= 5.0
                && student.getFamilyIncome() < 15000.0
                && student.getNumberOfSiblings() >= 3;
    }

    /**
     * Checks Inclusion Scholarship eligibility: disability degree &gt;= 33% and GPA &gt;= 5.0.
     */
    public boolean meetsInclusionRequirements(StudentProfile student) {
        isValidProfile(student);
        return student.getDisabilityDegree() >= 33 && student.getGpa() >= 5.0;
    }

    /**
     * Validates that the student profile is non-null and all attributes are within bounds.
     *
     * @param student the student profile to validate
     * @return {@code true} if the profile is valid
     * @throws IllegalArgumentException if the profile is null or any attribute is out of range
     */
    public boolean isValidProfile(StudentProfile student) {
        if (student == null) {
            throw new IllegalArgumentException("Student profile must not be null.");
        }
        if (student.getGpa() < 0.0 || student.getGpa() > 10.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 10.0.");
        }
        if (student.getFamilyIncome() < 0.0) {
            throw new IllegalArgumentException("Family income must be >= 0.");
        }
        if (student.getDistanceFromCampus() < 0.0) {
            throw new IllegalArgumentException("Distance from campus must be >= 0.");
        }
        if (student.getDisabilityDegree() < 0 || student.getDisabilityDegree() > 100) {
            throw new IllegalArgumentException("Disability degree must be between 0 and 100.");
        }
        if (student.getNumberOfSiblings() < 0) {
            throw new IllegalArgumentException("Number of siblings must be >= 0.");
        }
        return true;
    }
}
