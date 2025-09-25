import java.util.ArrayList;
import java.util.Scanner;

// Book Class
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }
    public void setIssued(boolean issued) { this.isIssued = issued; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + (isIssued ? "Issued" : "Available");
    }
}

// User Class
class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return id + " | " + name;
    }
}

// Library Class
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
        System.out.println("Book added: " + b.getTitle());
    }

    public void addUser(User u) {
        users.add(u);
        System.out.println("User added: " + u.getName());
    }

    public void displayBooks() {
        System.out.println("\n--- Books in Library ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void issueBook(int bookId, int userId) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                if (!b.isIssued()) {
                    b.setIssued(true);
                    System.out.println("Book '" + b.getTitle() + "' issued to User ID: " + userId);
                    return;
                } else {
                    System.out.println("Book already issued!");
                    return;
                }
            }
        }
        System.out.println("Book not found!");
    }

    public void returnBook(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                if (b.isIssued()) {
                    b.setIssued(false);
                    System.out.println("Book '" + b.getTitle() + "' returned.");
                    return;
                } else {
                    System.out.println("Book was not issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found!");
    }
}

// Main Class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Sample Data
        library.addBook(new Book(1, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book(2, "1984", "George Orwell"));
        library.addUser(new User(1, "Ananya"));
        library.addUser(new User(2, "Rahul"));

        int choice;
        do {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Display Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID to issue: ");
                    int bId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int uId = sc.nextInt();
                    library.issueBook(bId, uId);
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    int rbId = sc.nextInt();
                    library.returnBook(rbId);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}
