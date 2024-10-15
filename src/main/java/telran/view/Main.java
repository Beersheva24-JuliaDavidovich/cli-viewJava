package telran.view;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

record Employee(long id, String name, String department, int salary, LocalDate birthDate) {
}

public class Main {
    static InputOutput io = new StandardInputOutput();
    /*********************** */
    // For HW #35 constants
    final static int MIN_SALARY = 5000;
    final static int MAX_SALARY = 30000;
    final static String[] DEPARTMENTS = { "QA", "Audit", "Development", "Management" };
    // name should be at least 3 English letters; first - capital, others - lower
    // case
    final static long MIN_ID = 100000;
    final static long MAX_ID = 999999;
    final static int MIN_AGE = 18;
    final static int MAX_AGE = 70;

    /*********************************** */
    public static void main(String[] args) {
        readEmployeeAsObject();
    }

    static void readEmployeeAsObject() {
        Employee empl = io.readObject("Enter employee data in the format:" +
                " <id>#<name>#<department>#<salary>#<yyyy-MM-DD> ",
                "Wrong format for Employee data", str -> {
                    String[] tokens = str.split("#");
                    return new Employee(Long.parseLong(tokens[0]), tokens[1], tokens[2],
                            Integer.parseInt(tokens[3]), LocalDate.parse(tokens[4]));

                });
        io.writeLine("You are entered the following Employee data");
        io.writeLine(empl);
    }
       static  void readEmployeeBySeparateFields() {
        //TODO
        //Enter ID, Enter name, Enter department, Enter salary, Enter birthdate
        long id = io.readNumberRange("Enter ID:", "Wrong format for ID", MIN_ID, MAX_ID).longValue();
        String name = io.readStringPredicate("Enter name:", "Wrong format for name", i -> i.matches("^[A-Z][a-z]{2,}$"));
        String department = io.readStringOptions("Enter department:", "Wrong format for department", new HashSet<String>(Arrays.asList(DEPARTMENTS)));
        int salary = io.readNumberRange("Enter salary:", "Wrong format for salary", MIN_SALARY, MAX_SALARY).intValue();
        LocalDate birthDate = io.readIsoDateRange("Enter birth date:", "Wrong format for burth date", LocalDate.now().minusYears(MAX_AGE), LocalDate.now().minusYears(MIN_AGE));
        io.writeLine("Entered employee data");
        io.writeLine(new Employee(id, name, department, salary, birthDate));
     }
}