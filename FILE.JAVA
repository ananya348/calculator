import java.io.*;
import java.util.*;

public class NotesManager {
    private static final String NOTES_DIR = "notes";

    public static void main(String[] args) {
        File dir = new File(NOTES_DIR);
        if (!dir.exists()) dir.mkdir();

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n==== Notes Manager ====");
            System.out.println("1. Add Note");
            System.out.println("2. View Note");
            System.out.println("3. Edit Note");
            System.out.println("4. Delete Note");
            System.out.println("5. List Notes");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> addNote(sc);
                case 2 -> viewNote(sc);
                case 3 -> editNote(sc);
                case 4 -> deleteNote(sc);
                case 5 -> listNotes();
                case 0 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }

    private static void addNote(Scanner sc) {
        System.out.print("Enter note title: ");
        String title = sc.nextLine().trim();
        File file = new File(NOTES_DIR, title + ".txt");

        if (file.exists()) {
            System.out.println("Note already exists. Use Edit instead.");
            return;
        }

        System.out.println("Enter note content (type 'END' on a new line to finish):");
        StringBuilder content = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("END")) break;
            content.append(line).append(System.lineSeparator());
        }

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content.toString());
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    private static void viewNote(Scanner sc) {
        System.out.print("Enter note title to view: ");
        String title = sc.nextLine().trim();
        File file = new File(NOTES_DIR, title + ".txt");

        if (!file.exists()) {
            System.out.println("Note not found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            System.out.println("\n--- " + title + " ---");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("---------------");
        } catch (IOException e) {
            System.out.println("Error reading note: " + e.getMessage());
        }
    }

    private static void editNote(Scanner sc) {
        System.out.print("Enter note title to edit: ");
        String title = sc.nextLine().trim();
        File file = new File(NOTES_DIR, title + ".txt");

        if (!file.exists()) {
            System.out.println("Note not found.");
            return;
        }

        System.out.println("Enter new content (type 'END' on a new line to finish):");
        StringBuilder content = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("END")) break;
            content.append(line).append(System.lineSeparator());
        }

        try (FileWriter fw = new FileWriter(file, false)) {
            fw.write(content.toString());
            System.out.println("Note updated successfully!");
        } catch (IOException e) {
            System.out.println("Error editing note: " + e.getMessage());
        }
    }

    private static void deleteNote(Scanner sc) {
        System.out.print("Enter note title to delete: ");
        String title = sc.nextLine().trim();
        File file = new File(NOTES_DIR, title + ".txt");

        if (!file.exists()) {
            System.out.println("Note not found.");
            return;
        }

        if (file.delete()) {
            System.out.println("Note deleted successfully!");
        } else {
            System.out.println("Error deleting note.");
        }
    }

    private static void listNotes() {
        File dir = new File(NOTES_DIR);
        String[] files = dir.list((d, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("No notes available.");
            return;
        }

        System.out.println("\n--- Notes List ---");
        for (String f : files) {
            System.out.println(f.replace(".txt", ""));
        }
    }
}
