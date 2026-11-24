# Testing Units

| Método | Partición equivalente | Valores límite | Conjetura de errores | Total |
|---|---|---|---|---|
| `student.getGPA()` | -1; 2; 6; 7; 10 | -0'999; 0; 0'001; 4'999; 5; 5'001; 6'499; 6'5; 6'501; 8'499; 8'5; 8'501; 9'999; 10; 10'001 | | |
| `student.getFamilyIncome()` | -1; 8.000; 17.000; 25.000; 40.000 | -0'999; 0; 0'999; 14.999'999; 15.000; 15.000'001; 19.999'999; 20.000; 20.000'001; 29.999'999; 30.000; 30.000'001 | | |
| `student.getDistanceFromCampus()` | -1; 25; 100; 22.000 | | | |
| `student.getDisabilityDegree()` | -1; 25; 66; 101 | | | |
| `student.getNumberOfSiblings()` | -1; 2; 4 | -1; 0; 1; 2; 3; 4; 5 | | |
