import java.io.*;
import java.util.ArrayList;

public class ContactManager {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private static final String FILE_PATH = "data/contacts.txt";

    public ContactManager() {
        loadFromFile();
    }

    public String addContact(String name, String phone, String email) {
        String nameError = validateName(name);
        if (nameError != null) return nameError;

        String phoneError = validatePhone(phone);
        if (phoneError != null) return phoneError;

        String emailError = validateEmail(email);
        if (emailError != null) return emailError;

        if (findByPhone(phone) != null) {
            return "✗ A contact with this phone number already exists.";
        }

        contacts.add(new Contact(name, phone, email));
        saveToFile();
        return "✓ Contact added successfully.";
    }

    public ArrayList<Contact> getAllContacts() {
        return contacts;
    }

    public ArrayList<Contact> search(String query) {
        ArrayList<Contact> results = new ArrayList<>();
        String q = query.toLowerCase();
        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(q) || c.getPhone().contains(query)) {
                results.add(c);
            }
        }
        return results;
    }

    public String updateContact(String phone, String newName, String newEmail) {
        Contact c = findByPhone(phone);
        if (c == null) {
            return "✗ Contact not found.";
        }

        String nameError = validateName(newName);
        if (nameError != null) return nameError;

        String emailError = validateEmail(newEmail);
        if (emailError != null) return emailError;

        c.setName(newName);
        c.setEmail(newEmail);
        saveToFile();
        return "✓ Contact updated successfully.";
    }

    public String deleteContact(String phone) {
        Contact c = findByPhone(phone);
        if (c == null) {
            return "✗ Contact not found.";
        }
        contacts.remove(c);
        saveToFile();
        return "✓ Contact deleted successfully.";
    }

    public Contact findByPhone(String phone) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                return c;
            }
        }
        return null;
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Name cannot be empty.";
        }
        return null;
    }

    private String validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return "Phone number cannot be empty.";
        }
        if (!phone.matches("\\d+")) {
            return "Phone number should contain digits only.";
        }
        if (phone.length() < 7 || phone.length() > 15) {
            return "Phone number should be between 7 and 15 digits.";
        }
        return null;
    }

    private String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Email cannot be empty.";
        }
        if (!email.contains("@") || !email.contains(".")) {
            return "Please enter a valid email address.";
        }
        return null;
    }

    private void saveToFile() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Contact c : contacts) {
                writer.write(c.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Warning: Could not save contacts to file.");
        }
    }

    private void loadFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Warning: Could not load contacts from file.");
        }
    }
}
