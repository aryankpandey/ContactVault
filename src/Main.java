import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ContactManager manager = new ContactManager();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid option. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: addContact(); break;
                case 2: viewContacts(); break;
                case 3: searchContact(); break;
                case 4: updateContact(); break;
                case 5: deleteContact(); break;
                case 6:
                    System.out.println("\nThank you for using ContactVault.");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    static void printMenu() {
        System.out.println("\n========================================");
        System.out.println("              CONTACT VAULT             ");
        System.out.println("========================================\n");
        System.out.println("1. Add Contact");
        System.out.println("2. View Contacts");
        System.out.println("3. Search Contact");
        System.out.println("4. Update Contact");
        System.out.println("5. Delete Contact");
        System.out.println("6. Exit");
        System.out.print("\nChoose an option: ");
    }

    static void addContact() {
        System.out.println("\n------------ ADD CONTACT ------------\n");

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        String result = manager.addContact(name, phone, email);
        System.out.println("\n" + result);
    }

    static void viewContacts() {
        System.out.println("\n------------ YOUR CONTACTS ------------\n");

        ArrayList<Contact> contacts = manager.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            System.out.println((i + 1) + ". " + c.getName());
            System.out.println("   Phone: " + c.getPhone());
            System.out.println("   Email: " + c.getEmail());
            System.out.println();
        }
    }

    static void searchContact() {
        System.out.println("\n------------ SEARCH ------------\n");

        System.out.print("Enter name or phone: ");
        String query = scanner.nextLine().trim();

        ArrayList<Contact> results = manager.search(query);

        if (results.isEmpty()) {
            System.out.println("\n✗ Contact not found.");
            return;
        }

        System.out.println("\nFound:\n");
        for (Contact c : results) {
            System.out.println(c.getName());
            System.out.println(c.getPhone());
            System.out.println(c.getEmail());
            System.out.println();
        }
    }

    static void updateContact() {
        System.out.println("\n------------ UPDATE CONTACT ------------\n");

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();

        Contact existing = manager.findByPhone(phone);
        if (existing == null) {
            System.out.println("\n✗ Contact not found.");
            return;
        }

        System.out.println("\nCurrent: " + existing.getName() + " | " + existing.getEmail());
        System.out.print("New name: ");
        String newName = scanner.nextLine().trim();

        System.out.print("New email: ");
        String newEmail = scanner.nextLine().trim();

        String result = manager.updateContact(phone, newName, newEmail);
        System.out.println("\n" + result);
    }

    static void deleteContact() {
        System.out.println("\n------------ DELETE CONTACT ------------\n");

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();

        Contact existing = manager.findByPhone(phone);
        if (existing == null) {
            System.out.println("\n✗ Contact not found.");
            return;
        }

        System.out.println("\nContact: " + existing.getName() + " (" + existing.getPhone() + ")");
        System.out.print("Are you sure you want to delete this contact? (y/n): ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("y")) {
            String result = manager.deleteContact(phone);
            System.out.println("\n" + result);
        } else {
            System.out.println("\nDelete cancelled.");
        }
    }
}
