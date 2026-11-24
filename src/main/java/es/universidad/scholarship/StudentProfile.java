package es.universidad.scholarship;

/**
 * Immutable domain model representing a university student's profile data.
 */
public final class StudentProfile {

    private final double gpa;
    private final double familyIncome;
    private final double distanceFromCampus;
    private final int disabilityDegree;
    private final int numberOfSiblings;

    /**
     * Creates a new student profile.
     *
     * @param gpa                grade point average (0.0 to 10.0)
     * @param familyIncome       annual family income in euros (&gt;= 0)
     * @param distanceFromCampus distance from home to campus in km (&gt;= 0)
     * @param disabilityDegree   certified disability percentage (0 to 100)
     * @param numberOfSiblings   number of siblings (&gt;= 0)
     */
    public StudentProfile(double gpa,
                          double familyIncome,
                          double distanceFromCampus,
                          int disabilityDegree,
                          int numberOfSiblings) {
        this.gpa = gpa;
        this.familyIncome = familyIncome;
        this.distanceFromCampus = distanceFromCampus;
        this.disabilityDegree = disabilityDegree;
        this.numberOfSiblings = numberOfSiblings;
    }

    public double getGpa() {
        return gpa;
    }

    public double getFamilyIncome() {
        return familyIncome;
    }

    public double getDistanceFromCampus() {
        return distanceFromCampus;
    }

    public int getDisabilityDegree() {
        return disabilityDegree;
    }

    public int getNumberOfSiblings() {
        return numberOfSiblings;
    }
}
