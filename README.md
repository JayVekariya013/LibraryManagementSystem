### **Repository Description**  
**Library Management System**  
A Java-based Library Management System that allows users to manage books and members, issue and return books, and store data in a MySQL database. It demonstrates core Java and object-oriented programming concepts integrated with database connectivity.

---

# Library Management System

A simple Java-based Library Management System designed to manage books and members, issue and return books, and maintain records using a MySQL database. This project demonstrates core Java concepts, object-oriented programming principles, and integration with MySQL for persistent data storage.

---

## Features

- **Book Management**: Add, display, issue, and return books.
- **Member Management**: Add and manage library members.
- **Database Integration**: Uses MySQL to store and manage book and member data persistently.
- **Transaction Management**: Handles issuing and returning books as transactions.

---

## Project Structure

- **Book.java**: Represents a book with attributes like ID, title, author, and availability.
- **Member.java**: Represents a library member with ID and name.
- **Library.java**: Core class handling library operations (adding books, issuing/returning books, etc.).
- **DatabaseConnection.java**: Handles MySQL database connection.
- **LibraryManagementSystem.java**: Main class to interact with the system via a menu-driven interface.

---

## Technologies Used

- **Programming Language**: Java
- **Database**: MySQL
- **JDBC Driver**: MySQL Connector/J

---

## Setup Instructions

### Prerequisites

1. **Java Development Kit (JDK)** installed ([Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)).
2. **MySQL** installed ([Download MySQL](https://dev.mysql.com/downloads/installer/)).
3. **MySQL Connector/J** (JDBC Driver) downloaded ([Download Connector](https://dev.mysql.com/downloads/connector/j/)).

---

### Steps to Set Up and Run

1. **Clone the Repository**  
   ```bash
   git clone https://github.com/yourusername/LibraryManagementSystem.git
   cd LibraryManagementSystem
   ```

2. **Set Up the Database**  
   - Create a database named `LibraryDB`.
   - Execute the SQL script provided in `library_db_setup.sql` to create the necessary tables.

3. **Configure Database Connection**  
   - Update `DatabaseConnection.java` with your MySQL credentials:
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/LibraryDB";
     private static final String USERNAME = "your-username";
     private static final String PASSWORD = "your-password";
     ```

4. **Compile the Project**  
   ```bash
   javac -cp .:mysql-connector-j-9.1.0.jar *.java
   ```

5. **Run the Application**  
   ```bash
   java -cp .:mysql-connector-j-9.1.0.jar LibraryManagementSystem
   ```

---

## Usage

1. Add books to the library.
2. Add members to the library.
3. Issue books to members.
4. Return books.
5. View all books in the library.

---

## SQL Schema (`library_db_setup.sql`)

```sql
CREATE DATABASE LibraryDB;

USE LibraryDB;

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    is_available BOOLEAN DEFAULT TRUE
);

CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE borrowed_books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    member_id INT,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members(id)
);
```

---

## Future Enhancements

- Add a graphical user interface (GUI) for improved usability.
- Implement fine calculation for late returns.
- Add search functionality for books and members.

---

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Author

**Jay Vekariya**  
- [GitHub Profile](https://github.com/JayVekariya013)  
- [LinkedIn Profile](https://linkedin.com/in/JayVekariya013)
```
