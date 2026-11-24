package es.universidad.scholarship;

import java.util.Objects;

/**
 * Immutable domain model representing a university student's profile data
 * used to evaluate scholarship eligibility.
 */
public final class StudentProfile {

    private final double gpa;
    private final double familyIncome;
    private final double distanceFromCampus;
    private final int disabilityDegree;
    private final int numberOfSiblings;

    /**
     * Creates a new immutable student profile.
     *
     * @param gpa                student grade point average (expected range 0.0 to 10.0)
     * @param familyIncome       annual family income in euros (expected &gt;= 0.0)
     * @param distanceFromCampus distance from home to campus in km (expected &gt;= 0.0)
     * @param disabilityDegree   certified disability percentage (expected range 0 to 100)
     * @param numberOfSiblings   number of siblings (expected &gt;= 0)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentProfile that)) {
            return false;
        }
        return Double.compare(that.gpa, gpa) == 0
                && Double.compare(that.familyIncome, familyIncome) == 0
                && Double.compare(that.distanceFromCampus, distanceFromCampus) == 0
                && disabilityDegree == that.disabilityDegree
                && numberOfSiblings == that.numberOfSiblings;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gpa, familyIncome, distanceFromCampus, disabilityDegree, numberOfSiblings);
    }

    @Override
    public String toString() {
        return "StudentProfile{"
                + "gpa=" + gpa
                + ", familyIncome=" + familyIncome
                + ", distanceFromCampus=" + distanceFromCampus
                + ", disabilityDegree=" + disabilityDegree
                + ", numberOfSiblings=" + numberOfSiblings
                + '}';
    }
}
