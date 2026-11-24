# ISO2-2026-GrupoA.01-Testing-2

Pair 2: Åsta & Victoria - Maven implementation of **Problem 2 (ET.02.03 - Second Problem: Scholarship Calculation)** for the Software Engineering 2 assignment (Step 1: *Implement the Program*).

## Project Structure

Base package: `es.universidad.scholarship`

- `StudentProfile`: immutable domain model holding student profile data (`gpa`, `familyIncome`, `distanceFromCampus`, `disabilityDegree`, `numberOfSiblings`) and its getters.
- `ScholarshipType`: enum defining the scholarship categories (`EXCELLENCE`, `RESIDENCE`, `LARGE_FAMILY`, `INCLUSION`, `NONE`).
- `ScholarshipService`: service implementing profile validation (`isValidProfile`) and scholarship evaluation (`determineScholarship`, `meetsExcellenceRequirements`, `meetsResidenceRequirements`, `meetsLargeFamilyRequirements`, `meetsInclusionRequirements`).

## Design Assumptions and Ambiguity Resolution

1. **Overlapping Scholarship Eligibility**:
   The problem statement notes that a student may simultaneously meet the criteria for multiple scholarships (for example, `EXCELLENCE` and `INCLUSION`, or `RESIDENCE` and `LARGE_FAMILY`). To ensure `determineScholarship(StudentProfile)` returns exactly one category, all eligibility rules are evaluated and a deterministic priority order is applied:
   1. `EXCELLENCE` (GPA >= 8.5 and family income < €30,000)
   2. `INCLUSION` (disability degree >= 33% and GPA >= 5.0)
   3. `RESIDENCE` (6.5 <= GPA <= 8.4, family income < €20,000, and distance > 50 km)
   4. `LARGE_FAMILY` (GPA >= 5.0, family income < €15,000, and >= 3 siblings)
   5. `NONE` (when none of the above apply)

2. **GPA Gap Between 8.4 and 8.5**:
   `RESIDENCE` requires `6.5 <= GPA <= 8.4` and `EXCELLENCE` requires `GPA >= 8.5`. Consequently, a student with `8.4 < GPA < 8.5` is not eligible for either of those two scholarships (though they may still qualify for `INCLUSION` or `LARGE_FAMILY` if they meet their respective criteria).

3. **Validation and Exceptions**:
   - `StudentProfile` does not validate in its constructor so that invalid profiles can be instantiated when testing `ScholarshipService.isValidProfile(StudentProfile)`.
   - `isValidProfile(StudentProfile)` (also invoked by `determineScholarship` and all `meets*Requirements` methods) throws `IllegalArgumentException` if the profile is `null` or any attribute is out of bounds (`gpa` outside `[0.0, 10.0]`, `familyIncome < 0`, `distanceFromCampus < 0`, `disabilityDegree` outside `[0, 100]`, or `numberOfSiblings < 0`).

4. **Return Type of `determineScholarship`**:
   The shared specification listed `ScholarshipCategory` as the return type while defining the enum as `ScholarshipType`; `ScholarshipType` is used for type consistency.

## Building with Maven

```bash
mvn clean compile
```

The `pom.xml` includes `junit-jupiter`, `maven-surefire-plugin`, and `jacoco-maven-plugin` preconfigured for the subsequent test implementation phase (Step 3).
