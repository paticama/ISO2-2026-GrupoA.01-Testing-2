package es.universidad.scholarship;

/**
 * Executable entry point for demonstrating scholarship calculation.
 */
public final class ScholarshipApplication {

    private ScholarshipApplication() {
    }

    public static void main(String[] args) {
        StudentProfile student = new StudentProfile(
                9.0,
                25000.0,
                60.0,
                0,
                1
        );

        ScholarshipService service = new ScholarshipService();
        ScholarshipType scholarship = service.determineScholarship(student);

        System.out.println("Assigned scholarship: " + scholarship);
    }
}
