import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ProjectsApp {
    private List<String> operations = List.of(
            "1) Add a project"
    );

    private Scanner scanner = new Scanner(System.in);

    private void processUserSelections() {
        boolean done = false;

        while(!done) {
            try {
                int selection = getUserSelection();
                // Add logic to process the selection
            } catch(Exception e) {
                System.out.println("\nError: " + e + " Try Again.");
            }
        }
    }

    private int getUserSelection() {
        printOperations();

        Integer input = getIntInput("Enter a menu selection");

        return Objects.isNull(input) ? -1 : input;
    }

    private void printOperations() {
        System.out.println("\nThese are the available selections. Press the Enter key to quit");

        operations.forEach(line -> System.out.println(" " + line));
    }

    private Integer getIntInput(String prompt) {
        String input = getStringInput(prompt);

        if(Objects.isNull(input)) {
            return null;
        }

        try {
            return Integer.valueOf(input);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException(input + " is not a valid number.");
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine();

        return input.isBlank() ? null : input.trim();
    }
}