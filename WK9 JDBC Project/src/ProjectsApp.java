import projects.entity.Project;
import projects.service.ProjectService;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.math.BigDecimal;

public class ProjectsApp {
    private List<String> operations = List.of(
            "1) Add a project"
    );

    private Scanner scanner = new Scanner(System.in);

    private ProjectService projectService = new ProjectService();

    public void processUserSelections() {
        boolean done = false;

        while (!done) {
            try {
                int selection = getUserSelection();
                switch (selection) {
                    case -1 -> done = exitMenu();
                    case 1 -> {
                        System.out.println("Adding a project");
                        createProject();
                    }
                    default -> System.out.println("\n" + selection + " is not a valid selection. Try again.");
                }
            } catch (Exception e) {
                System.out.println("\nError: " + e + " Try Again.");
            }
        }
    }

    private void createProject() {
        String projectName = getStringInput("Enter Project Name:");
        BigDecimal estimatedHours = getDecimalInput("Enter Estimated Hours:");
        BigDecimal actualHours = getDecimalInput("Enter Actual Hours:");
        Integer difficulty = getIntInput("Enter Difficulty, on a scale of 1(low) to 5(high):");
        String notes = getStringInput("Enter Notes(optional):");
        Project project = new Project();

        project.setProjectName(projectName);
        project.setEstimatedHours(estimatedHours);
        project.setActualHours(actualHours);
        project.setDifficulty(difficulty);
        project.setNotes(notes);

        Project dbProject = projectService.addProject(project);
        System.out.println(dbProject + " successfully created");
    }

    private BigDecimal getDecimalInput(String prompt) {
        String input = getStringInput(prompt);

        if (Objects.isNull(input)) {
            return null;
        }

        try {
            BigDecimal decimal = new BigDecimal(input).setScale(2);
            return decimal;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(input + " is not a valid decimal number.");
        }
    }

    private boolean exitMenu() {
        System.out.println("\nExiting program...");
        return true;
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

        if (Objects.isNull(input)) {
            return null;
        }

        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(input + " is not a valid number.");
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine();

        return input.isBlank() ? null : input.trim();
    }

    public static void main(String[] args) {
        new ProjectsApp().processUserSelections();
    }
}