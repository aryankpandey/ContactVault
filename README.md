# ContactVault

A lightweight command-line contact management application built with Core Java.

## Features

- Add contacts
- View contacts
- Search contacts (by name or phone, case-insensitive)
- Update contacts
- Delete contacts with confirmation
- Duplicate phone-number prevention
- Input validation (name, phone, email)
- File-based persistence (`data/contacts.txt`)

## Tech Stack

- Java (no frameworks, no external libraries)
- OOP
- ArrayList
- File I/O (BufferedReader / BufferedWriter)
- Exception Handling

## Project Structure

```
ContactVault/
│
├── src/
│   ├── Main.java           ← CLI loop, user input, menu
│   ├── Contact.java        ← Contact model (name, phone, email)
│   └── ContactManager.java ← CRUD logic, validation, file I/O
│
├── data/
│   └── contacts.txt        ← Flat-file storage (auto-created)
│
└── README.md
```

## How to Run

Make sure you have Java installed:

```bash
java -version
```

Compile all source files from the project root:

```bash
javac src/*.java
```

Run the program:

```bash
java -cp src Main
```

> The `data/` folder and `contacts.txt` are created automatically if they don't exist.

## Example

```
========================================
              CONTACT VAULT
========================================

1. Add Contact
2. View Contacts
3. Search Contact
4. Update Contact
5. Delete Contact
6. Exit

Choose an option: 1

------------ ADD CONTACT ------------

Name: Aryan Pandey
Phone: 9876543210
Email: aryan@example.com

✓ Contact added successfully.

Choose an option: 2

------------ YOUR CONTACTS ------------

1. Aryan Pandey
   Phone: 9876543210
   Email: aryan@example.com

Choose an option: 3

------------ SEARCH ------------

Enter name or phone: aryan

Found:

Aryan Pandey
9876543210
aryan@example.com

Choose an option: 6

Thank you for using ContactVault.
Goodbye!
```

## Future Improvements

- SQLite/JDBC for proper database storage
- GUI with JavaFX or Swing
- REST API with Spring Boot
- Web frontend (React or plain HTML)
- Mobile client
- Export contacts to CSV or vCard
